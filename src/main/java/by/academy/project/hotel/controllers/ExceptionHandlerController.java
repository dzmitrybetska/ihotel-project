package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.responces.ErrorResponse;
import by.academy.project.hotel.errors.Error;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static by.academy.project.hotel.utils.Constants.EXCEPTION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityByIdNotFoundException(EntityNotFoundException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(BookingNotCreatedException.class)
    public ErrorResponse handleBookingNotCreatedException(BookingNotCreatedException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintValidationException(ConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return buildErrorResponse(exception);
    }

    private ErrorResponse buildErrorResponse(RuntimeException exception) {
        return ErrorResponse.builder()
                .errors(buildErrors(exception))
                .status(BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private ErrorResponse buildErrorResponse(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getFieldErrorCount())
                .time(LocalDateTime.now())
                .status(BAD_REQUEST)
                .errors(buildValidationErrors(exception))
                .build();
    }

    private ErrorResponse buildErrorResponse(ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getConstraintViolations().size())
                .errors(List.copyOf(buildValidationErrors(exception)))
                .status(BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private List<Error> buildErrors(RuntimeException exception) {
        return List.of(new Error(exception.getMessage()));
    }

    private List<Error> buildValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
    }

    private List<Error> buildValidationErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(violation -> new Error(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();
    }
}