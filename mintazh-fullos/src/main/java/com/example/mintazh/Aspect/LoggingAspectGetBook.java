package com.example.mintazh.Aspect;

import com.example.mintazh.Dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class LoggingAspectGetBook {
    @Before("execution(* com.example.mintazh.Controller.BookController.getBook(Long)) && args(itemId)")
    public void logBeforeGetBook(JoinPoint joinPoint, Long itemId) {
        String infoMessage = "\n * Metódus meghívása: " + joinPoint.getSignature().getName() + "\n" +
                " * Az ID=" + itemId + " könyv megkeresése elkezdődött.";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.example.mintazh.Controller.BookController.getBook(Long)) && args(itemId)", returning = "response", argNames = "itemId,response")
    public void logAfterGetBook(Long itemId, ResponseEntity<BookDto> response) {
        BookDto bookDto = response.getBody();
        log.info("Az ID={} könyv lekérdezve: [könyv neve: '{}' és szerzője: '{}']", itemId, Objects.requireNonNull(bookDto).getTitle(), bookDto.getAuthor());
    }
}