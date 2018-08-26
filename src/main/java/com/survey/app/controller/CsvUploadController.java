package com.survey.app.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.suervey.app.util.AppConstants;
import com.survey.app.dto.UploadData;
import com.survey.app.service.UploadService;
import com.survey.app.util.Page;


@RestController
@RequestMapping("/api/uploads")
public class CsvUploadController {

@Autowired
private UploadService uploadService;

 @GetMapping
 public Page<UploadData> getPolls(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Long page,
         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Long size ) {
    	
        return uploadService.getAllUploads(page, size);
    }
 
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("comments") String comments) throws Exception {
    	
        String fileName = uploadService.storeFile(file,comments);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return fileDownloadUri;
    }
    
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = uploadService.loadFileAsResource(fileId);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
