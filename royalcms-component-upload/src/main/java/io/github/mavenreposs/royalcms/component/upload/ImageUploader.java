package io.github.mavenreposs.royalcms.component.upload;


import java.util.Map;

public class ImageUploader extends Uploader {

    /**
     * 默认上传文件扩展类型
     */
    protected Map<String, String> defaultFileTypes = Map.of(
            "jpg", "image/jpg",
            "jpeg", "image/jpeg",
            "png", "image/png",
            "gif", "image/gif",
            "bmp", "image/bmp",
            "wbmp", "image/wbmp",
            "svg", "image/svg",
            "svgz", "image/svgz"
    );

    public ImageUploader() {
        settingUploadConfig();
    }

    /**
     * 设置上传配置选项
     */
    protected void settingUploadConfig() {

        Map<String, String> fileTypes = defaultFileTypes;

        fillUploadConfig(fileTypes);
    }

}
