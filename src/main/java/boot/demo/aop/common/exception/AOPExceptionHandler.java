package boot.demo.aop.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class AOPExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCode> invalidate() {
        return ResponseEntity.badRequest().body(ErrorCode.INVALID_ARGUMENT);
    }


    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<ErrorCode> missingCookie(MissingRequestCookieException e) {
        return ResponseEntity.badRequest().body(ErrorCode.MISSING_COOKIE);
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorCode> invalidQuery(SQLException e) {
        return ResponseEntity.badRequest().body(ErrorCode.INVALID_QUERY);
    }

}
