package org.example.project01lms.Repo;

import org.example.project01lms.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepo extends JpaRepository<Book , Integer> {
    Optional<Book> findByIsbnNumber(String isbnNumber);

    String getByIsbnNo(String isbnNumber);


    Optional<Book> findById(Long booksId);
}
