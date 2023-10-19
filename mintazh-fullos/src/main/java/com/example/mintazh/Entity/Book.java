package com.example.mintazh.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = "Hibás könyvcím: ez a mező nem lehet üres.")
    private String title;

    @Column(name = "author")
    @NotBlank(message = "Hibás szerzőnév: ez a mező nem lehet üres.")
    private String author;

    @Column(name = "release_date")
    @NotNull(message = "Hibás megjelenési dátum: ez a mező nem lehet üres.")
    private Date releaseDate;

    @Column(name = "price")
    @NotNull(message = "Hibás könyv ár: ez a mező nem lehet üres.")
    private BigDecimal price;

}
