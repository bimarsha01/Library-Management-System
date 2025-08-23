package org.example.project01lms.Repo;

import org.example.project01lms.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers, Long> {
    boolean existsByLibraryId(String randomLid);
}
