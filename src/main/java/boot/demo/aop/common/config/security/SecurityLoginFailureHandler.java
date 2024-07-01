package boot.demo.aop.common.config.security;

import boot.demo.aop.common.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class SecurityLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = ErrorCode.INVALID_LOGIN_FORM.getMessage();
        if (exception instanceof InsufficientAuthenticationException) {
            errorMessage = ErrorCode.INVALID_SECRET_KEY.getMessage();
        }
        String encodedErrorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        final String LOGIN_FAILURE_URL = request.getContextPath() + "/sign/login?errorMessage=" + encodedErrorMessage;
        response.sendRedirect(LOGIN_FAILURE_URL);
    }
}
