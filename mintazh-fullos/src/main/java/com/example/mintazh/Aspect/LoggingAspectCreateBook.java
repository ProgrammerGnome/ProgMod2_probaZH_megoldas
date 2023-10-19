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
public class LoggingAspectCreateBook {
    @Before("execution(* com.example.mintazh.Controller.BookController.saveNewBook(..)) && args(bookDto)")
        public void logBeforeCreateBook(JoinPoint joinPoint, BookDto bookDto) {
        String infoMessage = "\n * Metódus meghívása: " + joinPoint.getSignature().getName() + "\n" +
                " * Könyv hozzáadása elkezdődött:\n" +
                " * [könyv neve: '" + bookDto.getTitle() + "' és szerzője: " + bookDto.getAuthor() + "]";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.example.mintazh.Controller.BookController.saveNewBook(..))", returning = "result")
    public void logAfterCreateBook(ResponseEntity<BookDto> result) {
        BookDto bookDto = result.getBody();
        log.info("Könyv hozzáadva: [ID={} | könyv neve: '{}' és szerzője: '{}']", Objects.requireNonNull(bookDto).getId(), bookDto.getTitle(), bookDto.getAuthor());
    }
}
