package boot.demo.aop.application.rms.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class SecurityLoggingFilter extends UsernamePasswordAuthenticationFilter {
    public SecurityLoggingFilter() {
    }
}
