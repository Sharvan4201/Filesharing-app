package com.filesharingapp.filesharingapp.repositories;

import com.filesharingapp.filesharingapp.models.FileDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FileRepository extends JpaRepository<FileDb, Long> {

}
