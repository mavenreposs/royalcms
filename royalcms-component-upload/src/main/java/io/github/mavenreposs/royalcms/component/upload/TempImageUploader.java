package io.github.mavenreposs.royalcms.component.upload;

import org.springframework.web.multipart.MultipartFile;

public class TempImageUploader extends Uploader {

    public TempImageUploader() {
        settingUploadConfig();
    }

    @Override
    public UploadResult upload(MultipartFile file) {
        return null;
    }

    @Override
    public UploadResult[] multiUpload(MultipartFile[] files) {
        return null;
    }
}
