package io.github.mavenreposs.royalcms.component.upload;

import java.util.Map;

public class FileUploader extends Uploader {

    /**
     * 默认上传文件扩展类型
     */
    protected Map<String, String> defaultFileTypes = Map.of(
            "rar", "application/x-rar-compressed",
            "zip", "application/zip",
            "txt", "text/plain",
            "pdf", "application/pdf",
            "doc", "application/msword",
            "docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "xls", "application/vnd.ms-excel",
            "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "ppt", "application/vnd.ms-powerpoint",
            "pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    );

    public FileUploader() {
        settingUploadConfig();
    }

    /**
     * 设置上传配置选项
     */
    protected void settingUploadConfig() {

        Map<String, String> fileTypes = appUploadProperties().getDefaultFileTypes();

        if (fileTypes.isEmpty()) {
            fileTypes = defaultFileTypes;
        }

        fillUploadConfig(fileTypes);

    }

}
