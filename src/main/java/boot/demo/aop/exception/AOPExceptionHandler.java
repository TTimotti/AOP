package boot.demo.aop.exception;

import boot.demo.aop.aop.annotation.Loggable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class AOPExceptionHandler implements AuthenticationFailureHandler {
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

    @Loggable
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorCode> invalidQuery(SQLException e) {
        return ResponseEntity.badRequest().body(ErrorCode.INVALID_QUERY);
    }

    @Loggable
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = ErrorCode.INVALID_LOGIN_FORM.getMessage();
        if (exception instanceof InsufficientAuthenticationException) {
            errorMessage = ErrorCode.INVALID_SECRET_KEY.getMessage();
        }
        String encodedErrorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        final String LOGIN_FAIL_URL = request.getContextPath() + "/sign/login?errorMessage=" + encodedErrorMessage;
        response.sendRedirect(LOGIN_FAIL_URL);
    }
}
