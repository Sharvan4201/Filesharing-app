package com.filesharingapp.filesharingapp;

import com.filesharingapp.filesharingapp.controllers.FileShareController;
import com.filesharingapp.filesharingapp.dtos.FileSharingDto;
import com.filesharingapp.filesharingapp.dtos.ResponseMessage;
import com.filesharingapp.filesharingapp.models.FileShare;
import com.filesharingapp.filesharingapp.services.IFileShareService;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileShareControllerTest {

    @InjectMocks
    FileShareController fileShareController;

    @Mock
    IFileShareService fileShareService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fileShareController).build();
    }



    @Test
    @DisplayName("Should fail to share file")
    public void shouldFailToShareFile() throws Exception {
        doThrow(new RuntimeException()).when(fileShareService).shareFile(any(), any(), any(), any());

        mockMvc.perform(post("/share")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fileDbId\": 1, \"senderId\": 1, \"receiverId\": 2, \"date\": \"2022-01-01\"}"))
                .andExpect(status().isExpectationFailed());
    }
}