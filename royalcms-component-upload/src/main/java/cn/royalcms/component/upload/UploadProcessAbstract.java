package cn.royalcms.component.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件处理器
 */
public abstract class UploadProcessAbstract {

    protected final Uploader uploader;

    public UploadProcessAbstract(Uploader uploader) {
        this.uploader = uploader;
    }

    /**
     * 上传文件
     * @param file MultipartFile
     * @return boolean
     */
    public abstract UploadResult upload(MultipartFile file) throws UploadException;

}
