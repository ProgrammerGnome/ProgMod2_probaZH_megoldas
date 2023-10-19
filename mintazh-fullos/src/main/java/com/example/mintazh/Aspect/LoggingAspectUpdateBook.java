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
public class LoggingAspectUpdateBook {
    @Before(value = "execution(* com.example.mintazh.Controller.BookController.updateBook(..)) && args(id, bookDto)", argNames = "joinPoint,id,bookDto")
    public void logBeforeUpdateBook(JoinPoint joinPoint, Long id, BookDto bookDto) {
        String infoMessage = "\n * Metódus meghívása: " + joinPoint.getSignature().getName() + "\n" +
                " * Módosítás előtt (adatok beolvasása): [ID=" + id + ", könyv neve: '" + bookDto.getTitle() + "' és szerzője: '" + bookDto.getAuthor() + "']";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.example.mintazh.Controller.BookController.updateBook(..)) && args(itemId, ..)", returning = "response", argNames = "response,itemId")
    public void logAfterModifyBook(ResponseEntity<BookDto> response, Long itemId) {
        BookDto bookDto = response.getBody();
        log.info("Az ID=" + itemId + " könyv módosítva. Új adatok: [könyv neve: '{}' és szerzője: '{}']", Objects.requireNonNull(bookDto).getTitle(), bookDto.getAuthor());
    }
}