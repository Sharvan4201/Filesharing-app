package com.filesharingapp.filesharingapp.repositories;

import com.filesharingapp.filesharingapp.models.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileShareRepository extends JpaRepository<FileShare, Long> {
}
