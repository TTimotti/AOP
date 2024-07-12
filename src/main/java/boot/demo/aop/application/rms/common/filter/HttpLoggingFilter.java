package boot.demo.aop.application.rms.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class HttpLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("/favicon.ico")
                || request.getRequestURI().contains("/h2-console")
                || request.getRequestURI().endsWith(".css")
                || request.getRequestURI().endsWith(".js")
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        logRequest(request);

        try {

            filterChain.doFilter(request, response);
        } catch (Exception e) {

            log.error("Error occurred while processing request [{}]: {}", request.getRequestURI(), e.getMessage());
        } finally {

            logResponse(response);
        }
    }

    private void logRequest(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String clientIP = getClientIP(request);

        log.info("######################### HTTP PROTOCOL #########################");
        log.info(">>> REQUEST");
        log.info("URI: {}", requestURI);
        log.info("CLIENT IP: {}", clientIP);
    }

    private void logResponse(HttpServletResponse response) {
        log.info("<<< RESPONSE");
        log.info("STATUS: {}", response.getStatus());
        log.info("#################################################################");
    }

    private String getClientIP(HttpServletRequest request) {
        final String UNKNOWN = "unknown";
        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty() || UNKNOWN.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || UNKNOWN.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIP == null || clientIP.isEmpty() || UNKNOWN.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIP == null || clientIP.isEmpty() || UNKNOWN.equalsIgnoreCase(clientIP)) {
            clientIP = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIP == null || clientIP.isEmpty() || UNKNOWN.equalsIgnoreCase(clientIP)) {
            clientIP = request.getRemoteAddr();
        }
        return clientIP;
    }
}
