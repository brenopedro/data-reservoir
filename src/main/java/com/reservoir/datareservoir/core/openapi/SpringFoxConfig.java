package com.reservoir.datareservoir.core.openapi;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.reservoir.datareservoir"))
                    .paths(PathSelectors.any())
                    .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(List.of(authenticationScheme()))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfo())
                .tags(new Tag("Cube", "Cube Data Management"),
                        new Tag("Drone","Drone Data Management"),
                        new Tag("Rocket","Rocket Data Management"));
    }
    
    
    private SecurityContext securityContext() {
    	  return SecurityContext.builder()
    	        .securityReferences(securityReference()).build();
    	}

	private List<SecurityReference> securityReference() {
	  AuthorizationScope authorizationScope = new AuthorizationScope("ADMIN", "Admin access");
	  AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	  authorizationScopes[0] = authorizationScope;
	  return List.of(new SecurityReference("Authorization", authorizationScopes));
	}

	private HttpAuthenticationScheme authenticationScheme() {
	  return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
	}

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Resource not acceptable")
                        .build()
        );
    }

    private List<Response> globalPostResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request (client error)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Resource not acceptable")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Refused request, unsupported media type")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build()
        );
    }


    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Invalid request (client error)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Internal server error")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemModelReference())
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Data Reservoir for space mission")
                .description("Private API for registered users")
                .version("1")
                .contact(new Contact("Breno Pedro", "", "brenopedro14@hotmail.com"))
                .build();
    }

    private Consumer<RepresentationBuilder> getProblemModelReference() {
        return r -> r.model(m -> m.name("Problem")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problem").namespace("com.reservoir.datareservoir.api.v1.exceptionhandler")))));
    }
}
