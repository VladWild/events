package org.example.ws.handler;

import org.apache.commons.lang3.StringUtils;
import org.example.common.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String VALIDATION_ERROR = "validation error";

    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String ERRORS = "error";
    private static final String PATH = "path";

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> throwable(Throwable throwable) {
        return new ResponseEntity<>(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> exception(Exception ex, WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> exceptionNotFound(Exception ex, WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, HttpStatus.NOT_FOUND, request);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalState(RuntimeException ex, WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, HttpStatus.BAD_REQUEST, request);
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, status, request);
        putErrorsToBody(ex, body);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatus status,
                                                         WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, status, request);
        putErrorsToBody(ex, body);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = getGeneralErrorBody(ex, status, request);
        return new ResponseEntity<>(body, headers, status);
    }

    private void putErrorsToBody(BindException ex, Map<String, Object> body) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::getErrorString)
                .collect(Collectors.toList());
        body.put(ERRORS, errors);
        if (errors.isEmpty()) {
            body.put(MESSAGE, ex.getMessage());
        } else {
            body.put(MESSAGE, VALIDATION_ERROR);
        }
    }

    private Map<String, Object> getGeneralErrorBody(Exception ex,
                                                    HttpStatus status,
                                                    WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, OffsetDateTime.now());
        body.put(STATUS, status.value());
        body.put(ERROR, status.getReasonPhrase());
        body.put(PATH, getRequestURI(request));
        body.put(MESSAGE, ex.getMessage());
        return body;
    }

    private String getErrorString(ObjectError error) {
        if (error instanceof FieldError) {
            return ((FieldError) error).getField() + ' ' + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }

    private String getRequestURI(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        } else {
            return StringUtils.EMPTY;
        }
    }
}
