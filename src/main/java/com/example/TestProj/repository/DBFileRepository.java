package com.example.TestProj.repository;

import com.example.TestProj.entity.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {
}
