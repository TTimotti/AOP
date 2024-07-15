package boot.demo.aop.application.rms.common.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class MethodLoggingProxy {

    @Around("@annotation(boot.demo.aop.application.rms.common.annotation.Loggable) && @annotation(loggable)")
    public Object around(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        if (!loggable.loggable()) {
            return joinPoint.proceed(); // 로깅이 필요하지 않으면 메서드 실행만 진행
        }
        // 메서드 시그니처 정보를 가져옴
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();
        HttpServletRequest request = getCurrentHttpRequest();
        // 동적 로거 생성
        Logger dynamicLog = LoggerFactory.getLogger(className);
        dynamicLog.info("######################### SERVICE INFO #########################");
        if (request != null) {
            String clientIp = getClientIpAddress(request);
            String httpMethod = request.getMethod();
            String uri = request.getRequestURI();

            dynamicLog.info("Client IP : {}", clientIp);
            dynamicLog.info("HTTP Method : {}", httpMethod);
            dynamicLog.info("URI : {}", uri);
        }
        dynamicLog.info("Method : {}", methodName);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Exception) {
                continue;
            }
            dynamicLog.info("Request Params : {}", arg);
        }

        Object result = joinPoint.proceed(args);

        if (result != null) {
            dynamicLog.info("Result : {}", result);
        }
        dynamicLog.info("################################################################");

        return result;
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) { // JAVA 16 이후 도입된 문법
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For"); // 중개 서버가 있었을 경우 최초 요청한 클라이언트의 IP를 가져옴
        if (!StringUtils.hasText(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
