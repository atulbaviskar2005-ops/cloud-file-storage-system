package com.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "files")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    private String fileType;

    private long fileSize;

    @Column(length = 1000)
    private String fileUrl;

    private String publicId;

    private String resourceType;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public FileData() {
    }

    public FileData(String fileName, String fileType, long fileSize, String fileUrl,
                    String publicId, String resourceType, LocalDateTime uploadedAt, User user) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileUrl = fileUrl;
        this.publicId = publicId;
        this.resourceType = resourceType;
        this.uploadedAt = uploadedAt;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}