package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.FileDb;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface IFileStorageService {
    FileDb store(MultipartFile file) throws IOException;
    FileDb getFile(Long id);
    Stream<FileDb> getAllFiles();
    FileDb update(Long id, MultipartFile newFile) throws Exception;
}
