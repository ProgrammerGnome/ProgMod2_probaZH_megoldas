package com.example.mintazh.Aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ServerLog {

    @Before("execution(* com.example.mintazh.Controller.BookController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Metódus futtatása a(z) " + methodName + " előtt a(z) " + className + " osztályban.");
    }

    @AfterReturning("execution(* com.example.mintazh.Controller.BookController.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Metódus futtatása befejeződött a(z) " + methodName + " metódus után");
    }

    @AfterThrowing(pointcut = "execution(* com.example.mintazh.Controller.BookController.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        log.error("Kivétel dobása a(z) " + methodName + " metódusban. Kivétel: " + ex.getMessage());
    }

}
