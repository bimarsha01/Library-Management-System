package org.example.project01lms.Repo;

import org.example.project01lms.Models.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<ExcelFile , Long> {
}
