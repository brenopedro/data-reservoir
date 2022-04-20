package com.reservoir.datareservoir.api.v1.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String GENERIC_MSG_FINAL_USER
            = "An unexpected internal error occurred in the system. " +
            "Try again and if the problem persist, get in the touch with the system administrator.";

    private final MessageSource messageSource;

    /**
     * Get the exception when the url parameter is the wrong type
     * */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {

        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource %s is nonexistent.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_MSG_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "The request body is invalid. Check for syntax error.";

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_MSG_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("The property '%s' doesn't exist. "
                + "Correct or remove it and try again.", path);

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_MSG_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    /**
     * Get the exception when the singleton resource doesn't exist
     * */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex,
                                                         WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.INTERNAL_SERVER_ERROR;
        String detail = GENERIC_MSG_FINAL_USER;

        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(
                    (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    /**
     * Treating access denied exception, when the user doesn't have permission to access the endpoint
     * */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.ACCESS_DENIED;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage("You don't have permission to execute this operation.")
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_PARAMETER;

        String detail = String.format("The URL parameter %s has the value '%s', "
                        + "which is an invalid type. Correct it and inform one with the type %s.",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_MSG_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status,
                                                       WebRequest request){
        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
        String detail = String.format("The property '%s' received the invalid type value '%s'. " +
                "Correct it and try again with the type %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(GENERIC_MSG_FINAL_USER)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status,
                                                            WebRequest request, BindingResult bindingResult) {

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "One or more fields are invalid. Please, check the body and try again.";

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError)
                        name = ((FieldError) objectError).getField();

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).collect(Collectors.toList());

        Problem problem = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

        return Problem.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
