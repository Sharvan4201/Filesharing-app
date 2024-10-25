package com.filesharingapp.filesharingapp.controllers;

import com.filesharingapp.filesharingapp.Exceptions.ResourceNotFoundException;
import com.filesharingapp.filesharingapp.dtos.ResponseFile;
import com.filesharingapp.filesharingapp.dtos.ResponseMessage;
import com.filesharingapp.filesharingapp.models.FileDb;
import com.filesharingapp.filesharingapp.services.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")

public class FileController {

    @Autowired
    private IFileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        String message = "";
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping()
    public ResponseEntity<List<ResponseFile>> getListFiles() {
       try{
           List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
               String fileDownloadUri = ServletUriComponentsBuilder
                       .fromCurrentContextPath()
                       .path("/files/")
                       .path(dbFile.getId().toString())
                       .toUriString();

               return new ResponseFile(
                       dbFile.getName(),
                       fileDownloadUri,
                       dbFile.getType(),
                       dbFile.getData().length);
           }).collect(Collectors.toList());

           return ResponseEntity.status(HttpStatus.OK).body(files);

       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);

       }

    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) throws ResourceNotFoundException {
        try{
            FileDb fileDB = storageService.getFile(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                    .body(fileDB.getData());
        }catch (Exception e){
            throw new ResourceNotFoundException("File not found with id " + id);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.update(id, file);
            message = "Updated the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


}
