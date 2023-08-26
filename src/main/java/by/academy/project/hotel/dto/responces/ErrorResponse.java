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

    private int countOfErrors;
    private List<Error> errors;
    private LocalDateTime time;
    private HttpStatus status;
}
