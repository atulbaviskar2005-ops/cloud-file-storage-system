package com.project.repository;

import com.project.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FileRepository extends JpaRepository<FileData, Integer> {

    List<FileData> findByUserId(int userId);
}