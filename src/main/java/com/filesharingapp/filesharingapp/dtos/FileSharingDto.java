package com.filesharingapp.filesharingapp.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileSharingDto {

    private Long fileDbId;
    private Long senderId;
    private Long receiverId;
    private Date date;

}
