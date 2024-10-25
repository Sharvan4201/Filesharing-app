package com.filesharingapp.filesharingapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "files")
public class FileDb extends BaseModel{

    private String name;

    private String type;
    private Long version;//added version
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    public FileDb(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public FileDb() {
    }
}
