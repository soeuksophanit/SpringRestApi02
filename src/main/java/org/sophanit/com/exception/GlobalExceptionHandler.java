package org.sophanit.com.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    ProblemDetail handleExceptions(ApiException exception) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                exception.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setProperty("title",exception.getMessage());
        problemDetail.setProperty("detail",exception.getDetail());
        problemDetail.setProperty("instance",URI.create("/api/v1/"+ exception.getPath()));
        problemDetail.setProperty("timestamp",new Date(System.currentTimeMillis()).toString());

        return problemDetail;
    }
}
