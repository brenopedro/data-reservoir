package com.reservoir.datareservoir.api.v1.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible message"),
    INTERNAL_SERVER_ERROR("/internal-server-error", "Internal server error"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    ACCESS_DENIED("/access-denied", "Access denied"),
    INVALID_DATA("/invalid-data", "Invalid data");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://datareservoir.com.br" + path;
        this.title = title;
    }
}
