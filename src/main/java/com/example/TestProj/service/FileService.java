package com.example.TestProj.service;

import com.example.TestProj.entity.DBFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    DBFile storeFile(MultipartFile file);
    DBFile loadFile(Long fileId);
}
