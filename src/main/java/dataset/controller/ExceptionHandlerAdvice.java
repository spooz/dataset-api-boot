package dataset.controller;

import dataset.helpers.DataSetNotFoundException;
import dataset.helpers.DataSetUnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by programowanie on 27.11.2015.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataSetNotFoundException.class)
    private void handleNotFound() {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    private void handleInternalError() {

    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    private void handleUnsupportedMediaTypeError() {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleBadRequestError() {

    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(DataSetUnauthorizedAccessException.class)
    public void handleAccessDeniedError() {

    }

}
