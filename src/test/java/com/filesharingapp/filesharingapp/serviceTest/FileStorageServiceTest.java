package com.filesharingapp.filesharingapp.serviceTest;

import com.filesharingapp.filesharingapp.Exceptions.FileStorageException;
import com.filesharingapp.filesharingapp.Exceptions.ResourceNotFoundException;
import com.filesharingapp.filesharingapp.models.FileDb;
import com.filesharingapp.filesharingapp.repositories.FileRepository;
import com.filesharingapp.filesharingapp.services.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FileStorageServiceTest {

    @InjectMocks
    FileStorageService fileStorageService;

    @Mock
    FileRepository fileRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should store file successfully")
    public void shouldStoreFileSuccessfully() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        when(fileRepository.save(any())).thenReturn(new FileDb());

        FileDb result = fileStorageService.store(file);

        assertNotNull(result);
    }



    @Test
    @DisplayName("Should get file successfully")
    public void shouldGetFileSuccessfully() {
        when(fileRepository.findById(any())).thenReturn(Optional.of(new FileDb()));

        FileDb result = fileStorageService.getFile(1L);

        assertNotNull(result);
    }



    @Test
    @DisplayName("Should fail to update file when file not found")
    public void shouldFailToUpdateFileWhenFileNotFound() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        when(fileRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> fileStorageService.update(1L, file));
    }


}