package com.filesharingapp.filesharingapp;

import com.filesharingapp.filesharingapp.controllers.FileController;
import com.filesharingapp.filesharingapp.services.IFileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileControllerTest {

    @InjectMocks
    FileController fileController;

    @Mock
    IFileStorageService storageService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    @DisplayName("Should upload file successfully")
    public void shouldUploadFileSuccessfully() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        mockMvc.perform(multipart("/files/upload").file(file))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should fail to upload file")
    public void shouldFailToUploadFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());
        doThrow(new RuntimeException()).when(storageService).store(any());
        mockMvc.perform(multipart("/files/upload").file(file))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    @DisplayName("Should get list of files successfully")
    public void shouldGetListOfFilesSuccessfully() throws Exception {
        mockMvc.perform(get("/files"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should fail to get list of files")
    public void shouldFailToGetListOfFiles() throws Exception {
        doThrow(new RuntimeException()).when(storageService).getAllFiles();
        mockMvc.perform(get("/files"))
                .andExpect(status().isExpectationFailed());
    }


}