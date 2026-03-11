package com.bank.credit.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  // BusinessException and its subclasses
  @GraphQlExceptionHandler(BusinessException.class)
  public GraphQLError handleBusinessException(BusinessException ex) {

     Map<String, Object> extensions = Map.of(
        "code", ex.getCode(),
        "status", ex.getStatus().value()
    );

    return GraphqlErrorBuilder.newError()
        .message(ex.getMessage())
        .errorType(ErrorType.BAD_REQUEST)
        .extensions(extensions)
        .build();
  }

  // Validation errors
  @GraphQlExceptionHandler(MethodArgumentNotValidException.class)
  public GraphQLError handleValidation(MethodArgumentNotValidException ex) {
    Map<String, Object> extensions = Map.of(
        "code", "NOT_VALID_ARGUMENT",
        "status", HttpStatus.BAD_REQUEST.value()
    );

    return GraphqlErrorBuilder.newError()
        .message("Argumento no válido")
        .errorType(ErrorType.BAD_REQUEST)
        .extensions(extensions)
        .build();
  }

  // Para ResponseStatusException (si las usas en algún lado)
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ProblemDetail> handleRSE(ResponseStatusException ex, HttpServletRequest req) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), ex.getReason());
    pd.setTitle("Request error");
    pd.setProperty("path", req.getRequestURI());
    return ResponseEntity.status(ex.getStatusCode()).body(pd);
  }

  // Fallback for unexpected exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex, HttpServletRequest req) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado");
    pd.setTitle("Internal Server Error");
    pd.setProperty("path", req.getRequestURI());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pd);
  }
}