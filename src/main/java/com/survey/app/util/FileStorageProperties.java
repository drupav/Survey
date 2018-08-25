package com.survey.app.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    public String getUploadDir() {
        return "";
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}