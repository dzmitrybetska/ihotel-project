package by.academy.project.hotel.controllers;

import by.academy.project.hotel.dto.responces.ErrorResponse;
import by.academy.project.hotel.errors.Error;
import by.academy.project.hotel.errors.ValidationError;
import by.academy.project.hotel.exceptions.BookingNotCreatedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static by.academy.project.hotel.utils.Constants.EXCEPTION;

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
                .countOfErrors(1)
                .errors(buildErrors(exception))
                .status(HttpStatus.BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private ErrorResponse buildErrorResponse(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .countOfErrors(exception.getFieldErrorCount())
                .time(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .errors(List.copyOf(buildValidationErrors(exception)))
                .build();
    }

    private ErrorResponse buildErrorResponse(ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .countOfErrors(exception.getConstraintViolations().size())
                .errors(List.copyOf(buildValidationErrors(exception)))
                .status(HttpStatus.BAD_REQUEST)
                .time(LocalDateTime.now())
                .build();
    }

    private List<Error> buildErrors(RuntimeException exception) {
        return List.of(new Error(exception.getMessage()));
    }

    private List<ValidationError> buildValidationErrors(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
    }

    private List<ValidationError> buildValidationErrors(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(violation -> new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();
    }
}
