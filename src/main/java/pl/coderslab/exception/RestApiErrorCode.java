package pl.coderslab.exception;

import org.springframework.http.HttpStatus;

/**
 * Error Codes and statuses for API errors - feel free to add another custom errors.
 * @author dell
 *
 */
public enum RestApiErrorCode {

    UNKNOWN(100, HttpStatus.INTERNAL_SERVER_ERROR, "Some internal error message."),
    DUPLICATE(201, HttpStatus.CONFLICT, "Duplicate unique value"),
    ENTITY_NOT_FOUND(211, HttpStatus.NOT_FOUND, "Not found exception"),
    SOME_OTHER_ERROR(666, HttpStatus.OK, "Other error- created with love - coderslab.pl");

    private final int code;
    private final String description;
    private final HttpStatus status;
    

    RestApiErrorCode(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public int code() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
