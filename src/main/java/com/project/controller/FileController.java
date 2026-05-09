package com.project.controller;

import com.project.entity.FileData;
import com.project.entity.User;
import com.project.repository.FileRepository;
import com.project.repository.UserRepository;
import com.project.service.CloudinaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload/{userId}")
    public String uploadFile(@PathVariable int userId, @RequestParam("file") MultipartFile file) {
        try {
            User user = userRepository.findById(userId).orElse(null);

            if (user == null) {
                return "User not found";
            }

            Map uploadResult = cloudinaryService.uploadFile(file);

            String fileUrl = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();
            String resourceType = uploadResult.get("resource_type").toString();

            FileData fileData = new FileData(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize(),
                    fileUrl,
                    publicId,
                    resourceType,
                    LocalDateTime.now(),
                    user
            );

            fileRepository.save(fileData);

            return "File uploaded to Cloudinary successfully";

        } catch (Exception e) {
            return "Upload failed: " + e.getMessage();
        }
    }

    @GetMapping("/list/{userId}")
    public List<FileData> getFiles(@PathVariable int userId) {
        return fileRepository.findByUserId(userId);
    }

    @GetMapping("/share/{fileId}")
    public String shareFile(@PathVariable int fileId) {
        FileData fileData = fileRepository.findById(fileId).orElse(null);

        if (fileData == null) {
            return "File not found";
        }

        return fileData.getFileUrl();
    }

    @DeleteMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId) {
        try {
            FileData fileData = fileRepository.findById(fileId).orElse(null);

            if (fileData == null) {
                return "File not found";
            }

            cloudinaryService.deleteFile(fileData.getPublicId(), fileData.getResourceType());
            fileRepository.delete(fileData);

            return "File deleted from Cloudinary successfully";

        } catch (Exception e) {
            return "Delete failed: " + e.getMessage();
        }
    }
}