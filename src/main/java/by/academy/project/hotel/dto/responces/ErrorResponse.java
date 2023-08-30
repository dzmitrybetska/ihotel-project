package by.academy.project.hotel.dto.responces;

import by.academy.project.hotel.errors.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
public class ErrorResponse {

    @JsonInclude(NON_NULL)
    private final Integer errorCount;
    private final List<Error> errors;
    private final LocalDateTime time;
    private final HttpStatus status;
}
