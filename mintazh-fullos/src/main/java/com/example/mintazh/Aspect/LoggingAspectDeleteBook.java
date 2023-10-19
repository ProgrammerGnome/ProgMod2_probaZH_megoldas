package com.example.mintazh.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class LoggingAspectDeleteBook {
    @Before("execution(* com.example.mintazh.Controller.BookController.deleteBook(Long)) && args(itemId)")
    public void logBeforeDeleteBook(JoinPoint joinPoint, Long itemId) {
        String infoMessage = "\n * Metódus meghívása: " + joinPoint.getSignature().getName() + "\n" +
                " * Az ID=" + itemId + " könyv törlése elkezdődött.";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.example.mintazh.Controller.BookController.deleteBook(Long)) && args(itemId)", returning = "result", argNames = "itemId,result")
    public void logAfterDeleteBook(Long itemId, Object result) {
        if (result != null) {
            log.info("Az ID=" + itemId + " könyv eltávolítva.");
        }
    }
}