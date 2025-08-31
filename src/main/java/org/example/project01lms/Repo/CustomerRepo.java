package org.example.project01lms.Repo;

import org.example.project01lms.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepo extends JpaRepository<Customers, Long> {
    boolean existsByLibraryId(String randomLid);
    Optional<Customers> findByLibraryId(String lid);
}
