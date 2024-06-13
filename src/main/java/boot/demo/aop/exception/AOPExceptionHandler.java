package boot.demo.aop.exception;

import boot.demo.aop.aop.annotation.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class AOPExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Loggable
    public ResponseEntity<String> invalidate(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
