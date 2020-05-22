package co.gov.ids.stationerycontrol.delivery.framework.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import co.gov.ids.stationerycontrol.delivery.application.exceptions.NotFoundException;
import co.gov.ids.stationerycontrol.delivery.application.exceptions.BadRequestException;
import co.gov.ids.stationerycontrol.delivery.framework.resources.exceptions.FeignException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to handle internal exceptions.
 *
 * @author Sergio Rodríguez
 * @version 0.0.1
 * @since 2020
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Function to handle BadRequest exception.
     *
     * @param ex exception.
     * @return status 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), "Bad request");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Function to handle NotFound exception.
     *
     * @param ex exception.
     * @return status 402.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "Not found");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    /**
     * Function to handle Feign exception.
     *
     * @param ex exception.
     * @return status 500.
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeign(FeignException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Internal server error");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}