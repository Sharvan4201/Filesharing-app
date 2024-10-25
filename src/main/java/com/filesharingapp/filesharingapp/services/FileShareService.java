package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.FileDb;
import com.filesharingapp.filesharingapp.models.FileShare;
import com.filesharingapp.filesharingapp.models.User;
import com.filesharingapp.filesharingapp.repositories.FileShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class FileShareService implements IFileShareService{

    private IFileStorageService storageService;
    private IUserService userService;
    private FileShareRepository fileShareRepository;
    @Autowired
    public FileShareService(IFileStorageService storageService, IUserService userService, FileShareRepository fileShareRepository) {
        this.storageService = storageService;
        this.userService = userService;
        this.fileShareRepository = fileShareRepository;
    }

    @Override
    public FileShare shareFile(Long fileDbId, Long senderId, Long receiverId, Date date) {
        // Logic to share file
            FileDb fileDb = storageService.getFile(fileDbId);
            User sender = userService.getUser(senderId);
            User receiver = userService.getUser(receiverId);

            FileShare fileShare = new FileShare();
            fileShare.setFileDb(fileDb);
            fileShare.setSender(sender);
            fileShare.setReceiver(receiver);
            fileShare.setDate(date);

        return  fileShareRepository.save(fileShare);

    }


   }
