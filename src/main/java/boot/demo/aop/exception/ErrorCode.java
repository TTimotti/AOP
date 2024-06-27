package boot.demo.aop.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    MISSING_HEADER(         2000,       "MISSING_HEADER",          "Required header is missing"),
    MISSING_COOKIE(         2001,        "MISSING_COOKIE",         "Request cookie is missing"),
    MISSING_ARGUMENT(       2002,        "MISSING_ARGUMENT",       "Request argument is missing"),

    INVALID_SECRET_KEY(     3001,        "INVALID_SECRET_KEY",       "Invalid secret key"),
    INVALID_ARGUMENT(       3002,        "INVALID_ARGUMENT",       "Invalid argument provided"),
    INVALID_QUERY(          3003,        "INVALID_QUERY",          "Invalid SQL query provided"),
    INVALID_LOGIN_FORM(     3004,        "INVALID_LOGIN_FORM",      "Invalid Username or Password"),


    USER_NOT_FOUND(         4000,        "USER_NOT_FOUND",         "Username not found"),
    ALREADY_HAS_USER(       4001,        "ALREADY_HAS_USER",       "Username already exists"),

    NOT_FOUND(              400,        "NOT_FOUND",               "Requested resource not found"),
    INTERNAL_SERVER_ERROR(  500,        "INTERNAL_SERVER_ERROR",    "Internal server error occurred");

    private final long code;
    private final String name;
    private final String message;

}
