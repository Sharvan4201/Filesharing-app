package com.filesharingapp.filesharingapp.controllers;
import com.filesharingapp.filesharingapp.dtos.FileSharingDto;
import com.filesharingapp.filesharingapp.dtos.ResponseMessage;
import com.filesharingapp.filesharingapp.models.FileShare;
import com.filesharingapp.filesharingapp.services.IFileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FileShareController {

    private IFileShareService fileShareService;
    @Autowired
    public FileShareController(IFileShareService fileShareService) {
        this.fileShareService = fileShareService;
    }

    @PostMapping("/share")
    public ResponseEntity<ResponseMessage> shareFile(@RequestBody FileSharingDto fileSharingDto) {
        String message = "";
        FileShare fileShare;
        try {
           fileShare = fileShareService.shareFile(fileSharingDto.getFileDbId(), fileSharingDto.getSenderId(), fileSharingDto.getReceiverId(), fileSharingDto.getDate());
            message = "Shared the file successfully: " + fileShare.getFileDb().getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not share the file: " + fileSharingDto.getFileDbId() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
