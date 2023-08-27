package by.academy.project.hotel.dto.responces;

import by.academy.project.hotel.errors.Error;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private final int countOfErrors;
    private final List<Error> errors;
    private final LocalDateTime time;
    private final HttpStatus status;
}
