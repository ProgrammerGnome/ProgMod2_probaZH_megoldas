package com.example.mintazh.Repository;

import com.example.mintazh.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Ide jöhetnek az SQL lekérdezések és módosítások.
     */

    /* PÉLDÁUL:
    @Query(value = "SELECT * FROM book WHERE book.id = :x" ,nativeQuery = true)
    public Book valami(Long x);
    */

}
