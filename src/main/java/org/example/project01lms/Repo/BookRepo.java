package org.example.project01lms.Repo;

import org.example.project01lms.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book , Integer> {
    Optional<Book> findByIsbnNumber(String isbnNumber);

    String isbnNumber(String isbnNumber);
}
