package pl.coderslab.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ExceptionResponse {

    private int errorCode;
    private String errorMessage;

}
