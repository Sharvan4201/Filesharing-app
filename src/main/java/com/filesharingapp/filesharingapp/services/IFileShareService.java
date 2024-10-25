package com.filesharingapp.filesharingapp.services;

import com.filesharingapp.filesharingapp.models.FileShare;

import java.util.Date;

public interface IFileShareService {
    FileShare shareFile(Long fileDbId, Long senderId, Long receiverId, Date date);

}
