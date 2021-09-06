package cn.royalcms.component.upload;

import cn.royalcms.component.contracts.storage.Storage;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

import static mavenreposs.php.functions.PHPFunctions.*;


public abstract class UploaderAbstract {

    private UploadOption uploadOption = UploadOption.DefaultUploadOption;

    private String[] allowedExtensions = new String[]{};

    private String[] allowedMimes = new String[]{};

    private long allowedSize = 0;

    /**
     * 文件存储系统对象
     */
    private Storage disk;

    public UploadOption getUploadOption() {
        return uploadOption;
    }

    public void setUploadOption(UploadOption uploadOption) {
        this.uploadOption = uploadOption;
    }

    public Storage getDisk() {
        return disk;
    }

    public void setDisk(Storage disk) {
        this.disk = disk;
    }

    public String[] getAllowedExtensions() {
        return allowedExtensions;
    }

    /**
     * 设定允许添加的文件类型
     *
     * @param allowedExtensions （小写）示例：gif,jpg,jpeg,png
     */
    public void setAllowedExtensions(String... allowedExtensions) {
        this.allowedExtensions = allowedExtensions;
    }

    public String[] getAllowedMimes() {
        return allowedMimes;
    }

    /**
     * 允许的文件MIME类型
     *
     * @param allowedMimes 示例：image/gif, image/jpg, image/jpeg, image/png
     */
    public void setAllowedMimes(String... allowedMimes) {
        this.allowedMimes = allowedMimes;
    }

    public long getAllowedSize() {
        return allowedSize;
    }

    /**
     * 允许的大小
     *
     * @param allowedSize 允许上传的文件大小
     */
    public void setAllowedSize(long allowedSize) {
        this.allowedSize = allowedSize;
    }

    /**
     * 检查上传的文件后缀是否合法
     *
     * @param ext 文件后缀
     */
    public boolean checkExtension(String ext) {
        ext = ltrim(ext, ".");

        if (allowedExtensions.length == 0) {
            return true;
        }

        return ArrayUtils.contains(allowedExtensions, strtolower(ext));
    }

    /**
     * 检查上传的文件MIME类型是否合法
     *
     * @param mime 数据
     */
    public boolean checkMime(String mime) {
        if (allowedMimes.length == 0) {
            return true;
        }

        return ArrayUtils.contains(allowedMimes, strtolower(mime));
    }

    /**
     * 检查文件大小是否合法
     *
     * @param size 数据大小
     */
    public boolean checkSize(long size) {
        if (uploadOption.getMaxSize() == 0) {
            return true;
        }

        return size <= uploadOption.getMaxSize();
    }

    /**
     * 上传单个文件
     *
     * @param file 单个文件信息
     * @return array|bool 上传成功后的文件信息
     */
    abstract public UploadResult upload(MultipartFile file) throws UploadException;

    /**
     * 上传多个文件
     *
     * @param files 多个文件信息
     */
    abstract public UploadResult[] multiUpload(MultipartFile[] files) throws UploadException;

    /**
     * 获取上传文件的后缀名
     * @param file 单个文件信息
     * @return 文件的后缀名
     */
    public String getClientOriginalExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();//获取文件名称
        assert filename != null;
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 获取上传文件不带后缀名的文件名
     * @param file 单个文件信息
     * @return 文件名
     */
    public String getClientOriginalFileName(MultipartFile file) {
        String filename = file.getOriginalFilename();//获取文件名称
        String[] subjects = explode(filename, getClientOriginalExtension(file), 2);
        return subjects[0];
    }

}
