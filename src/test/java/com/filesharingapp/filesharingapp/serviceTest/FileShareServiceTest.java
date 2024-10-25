package com.filesharingapp.filesharingapp.serviceTest;

import com.filesharingapp.filesharingapp.models.FileDb;
import com.filesharingapp.filesharingapp.models.FileShare;
import com.filesharingapp.filesharingapp.models.User;
import com.filesharingapp.filesharingapp.repositories.FileShareRepository;
import com.filesharingapp.filesharingapp.services.FileShareService;
import com.filesharingapp.filesharingapp.services.IFileStorageService;
import com.filesharingapp.filesharingapp.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileShareServiceTest {

    @InjectMocks
    FileShareService fileShareService;

    @Mock
    IFileStorageService storageService;

    @Mock
    IUserService userService;

    @Mock
    FileShareRepository fileShareRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should share file successfully")
    public void shouldShareFileSuccessfully() {
        FileDb fileDb = new FileDb();
        User sender = new User();
        User receiver = new User();
        Date date = new Date();
        FileShare fileShare = new FileShare();

        when(storageService.getFile(any())).thenReturn(fileDb);
        when(userService.getUser(any())).thenReturn(sender).thenReturn(receiver);
        when(fileShareRepository.save(any())).thenReturn(fileShare);

        FileShare result = fileShareService.shareFile(1L, 1L, 2L, date);

        assertEquals(fileShare, result);
    }

    @Test
    @DisplayName("Should fail to share file when file not found")
    public void shouldFailToShareFileWhenFileNotFound() {
        User sender = new User();
        User receiver = new User();
        Date date = new Date();

        when(storageService.getFile(any())).thenReturn(null);
        when(userService.getUser(any())).thenReturn(sender).thenReturn(receiver);

        FileShare result = fileShareService.shareFile(1L, 1L, 2L, date);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("Should fail to share file when sender not found")
    public void shouldFailToShareFileWhenSenderNotFound() {
        FileDb fileDb = new FileDb();
        User receiver = new User();
        Date date = new Date();

        when(storageService.getFile(any())).thenReturn(fileDb);
        when(userService.getUser(any())).thenReturn(null).thenReturn(receiver);

        FileShare result = fileShareService.shareFile(1L, 1L, 2L, date);

        assertEquals(null, result);
    }

    @Test
    @DisplayName("Should fail to share file when receiver not found")
    public void shouldFailToShareFileWhenReceiverNotFound() {
        FileDb fileDb = new FileDb();
        User sender = new User();
        Date date = new Date();

        when(storageService.getFile(any())).thenReturn(fileDb);
        when(userService.getUser(any())).thenReturn(sender).thenReturn(null);

        FileShare result = fileShareService.shareFile(1L, 1L, 2L, date);

        assertEquals(null, result);
    }
}