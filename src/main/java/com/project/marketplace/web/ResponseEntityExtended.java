package com.project.marketplace.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.status;

public class ResponseEntityExtended {

    public static <T> ResponseEntity notFound(T body) {
        return status(HttpStatus.NOT_FOUND).body(body);
    }

    public static <T> ResponseEntity badRequest(T body) {
        return status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static <T> ResponseEntity created(T body) {
        return status(HttpStatus.CREATED).body(body);
    }

    public static <T> ResponseEntity ok(T body) {
        return status(HttpStatus.OK).body(body);
    }

}
