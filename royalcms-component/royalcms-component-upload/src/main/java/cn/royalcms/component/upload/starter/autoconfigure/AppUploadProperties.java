package cn.royalcms.component.upload.starter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "app.upload")
public class AppUploadProperties {

    // 允许上传大小限制Bytes 2M
    private int maxSize = 2097152;

    // 上传使用年/月文件夹
    private boolean useYearMonthFolder = false;

    private Map<String, String> defaultFileTypes = new HashMap<>();

    public AppUploadProperties() {
        defaultFileTypes.putAll(Map.of(
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
        ));
        defaultFileTypes.putAll(Map.of(
                "jpg", "image/jpg",
                "jpeg", "image/jpeg",
                "png", "image/png",
                "gif", "image/gif",
                "bmp", "image/bmp",
                "wbmp", "image/wbmp",
                "svg", "image/svg",
                "svgz", "image/svgz"
        ));
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isUseYearMonthFolder() {
        return useYearMonthFolder;
    }

    public void setUseYearMonthFolder(boolean useYearMonthFolder) {
        this.useYearMonthFolder = useYearMonthFolder;
    }

    public Map<String, String> getDefaultFileTypes() {
        return defaultFileTypes;
    }

    public void setDefaultFileTypes(Map<String, String> defaultFileTypes) {
        this.defaultFileTypes = defaultFileTypes;
    }
}
