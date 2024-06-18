package boot.demo.aop.exception;

import boot.demo.aop.aop.annotation.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class AOPExceptionHandler {
    @Loggable
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorCode> invalidate() {
        return ResponseEntity.badRequest().body(ErrorCode.INVALID_ARGUMENT);
    }

    @Loggable
    @ExceptionHandler(MissingRequestCookieException.class)
    public ResponseEntity<ErrorCode> missingCookie(MissingRequestCookieException e) {
        return ResponseEntity.badRequest().body(ErrorCode.MISSING_COOKIE);
    }

}
