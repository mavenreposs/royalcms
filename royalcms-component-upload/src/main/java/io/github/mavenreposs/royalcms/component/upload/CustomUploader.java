package io.github.mavenreposs.royalcms.component.upload;

import org.springframework.web.multipart.MultipartFile;

public class CustomUploader extends Uploader {
    @Override
    public UploadResult upload(MultipartFile file) {
        return null;
    }

    @Override
    public UploadResult[] multiUpload(MultipartFile[] files) {
        return null;
    }
}
