package com.example.TestProj.service.impl;

import com.example.TestProj.entity.DBFile;
import com.example.TestProj.exceptions.FileNotFoundException;
import com.example.TestProj.exceptions.FileStorageException;
import com.example.TestProj.repository.DBFileRepository;
import com.example.TestProj.service.FileService;
import com.example.TestProj.util.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private final Path fileStorageLocation;
    private final DBFileRepository DBFileRepository;

    public FileServiceImpl(FileStorageProperties fileStorageProperties, DBFileRepository dbFileRepository) {
        DBFileRepository = dbFileRepository;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory for file uploads.");
        }
    }

    @Override
    public DBFile storeFile(MultipartFile file) {
        log.info("Storing file: " + file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid file path: " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            return DBFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName);
        }
    }

    @Override
    public DBFile loadFile(Long fileId) {
        return DBFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + fileId));
    }
}
