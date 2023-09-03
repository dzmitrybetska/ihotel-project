package by.academy.project.hotel.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static by.academy.project.hotel.utils.Constants.*;

@Slf4j
@Aspect
@Component
public class LoggingControllerAspect {

    @Pointcut("execution(* by.academy.project.hotel.controllers..*(..)) && !@annotation(SkipLogging)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        Object[] args = joinPoint.getArgs();
        String shortString = joinPoint.getSignature().toShortString();
        log.info(REQUEST_LOG_PATTERN, method, requestURI, args, shortString);
    }

    @AfterReturning(pointcut = "pointCut()", returning = "response")
    public void logResponse(JoinPoint joinPoint, Object response) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        String shortString = joinPoint.getSignature().toShortString();
        log.info(RESPONSE_LOG_PATTERN, method, requestURI, shortString, Optional.ofNullable(response)
                .orElse(EMPTY));
    }
}
