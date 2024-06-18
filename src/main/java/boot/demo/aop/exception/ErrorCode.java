package boot.demo.aop.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    MISSING_HEADER("2000","MISSING_HEADER", "Required header is missing"),
    MISSING_COOKIE("2001", "MISSING_COOKIE", "Request cookie is missing"),
    MISSING_ARGUMENT("3001", "MISSING_ARGUMENT", "Request argument is missing"),
    INVALID_ARGUMENT("3002", "INVALID_ARGUMENT", "Invalid argument provided"),
    INTERNAL_SERVER_ERROR("500", "INTERNAL_SERVER_ERROR", "Internal server error occurred"),
    NOT_FOUND("400", "NOT_FOUND", "Requested resource not found");

    private final String code;
    private final String name;
    private final String message;

}
