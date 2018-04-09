package com.project.marketplace.web.advice;

import com.project.marketplace.message.ErrorRepresentation;
import com.project.marketplace.message.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ControllerErrorHandlers {

    @Autowired
    Messages messages;

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity handleConstraintViolationException(ConstraintViolationException ex) {

        return status(HttpStatus.BAD_REQUEST).body(new ErrorRepresentation("field.notNull", messages.get("field.notNull")));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception ex) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorRepresentation("application.error", messages.get("application.error")));
    }

}
