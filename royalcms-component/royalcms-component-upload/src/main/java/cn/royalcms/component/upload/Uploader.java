package cn.royalcms.component.upload;

import cn.royalcms.facades.bean.SpringBeanFactory;
import cn.royalcms.component.upload.starter.autoconfigure.AppUploadProperties;
import io.github.mavenreposs.php.functions.PHPFunctions;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Uploader extends UploaderAbstract {

    /**
     * 生成文件名回调函数
     */
    protected Function<String, String> filenameCallback;

    /**
     * 生成子目录名回调函数
     */
    protected Function<String, String> subDirnameCallback;

    /**
     * 上传成功后的回调函数
     */
    protected BiConsumer<MultipartFile, UploadResult> uploadSuccessCallback;

    /**
     * 上传后保存的回调函数
     */
    protected BiConsumer<MultipartFile, UploadResult> uploadSavingCallback;

    /**
     * 默认上传文件扩展类型
     */
    protected Map<String, String> defaultFileTypes;

    public Map<String, String> getDefaultFileTypes() {
        return defaultFileTypes;
    }

    public void setDefaultFileTypes(Map<String, String> defaultFileTypes) {
        this.defaultFileTypes = defaultFileTypes;
    }

    public Function<String, String> getFilenameCallback() {
        return filenameCallback;
    }

    public void setFilenameCallback(Function<String, String> filenameCallback) {
        this.filenameCallback = filenameCallback;
    }

    public Function<String, String> getSubDirnameCallback() {
        return subDirnameCallback;
    }

    public void setSubDirnameCallback(Function<String, String> subDirnameCallback) {
        this.subDirnameCallback = subDirnameCallback;
    }

    public BiConsumer<MultipartFile, UploadResult> getUploadSuccessCallback() {
        return uploadSuccessCallback;
    }

    public void setUploadSuccessCallback(BiConsumer<MultipartFile, UploadResult> uploadSuccessCallback) {
        this.uploadSuccessCallback = uploadSuccessCallback;
    }

    public BiConsumer<MultipartFile, UploadResult> getUploadSavingCallback() {
        return uploadSavingCallback;
    }

    public void setUploadSavingCallback(BiConsumer<MultipartFile, UploadResult> uploadSavingCallback) {
        this.uploadSavingCallback = uploadSavingCallback;
    }

    /**
     * 检查文件是否符合上传条件
     * @param uploadFile MultipartFile
     * @return boolean
     * @throws UploadException 上传异常
     */
    public boolean checkedUploadFile(MultipartFile uploadFile) throws UploadException {
        if (! checkSize(uploadFile.getSize())) {
            throw new UploadException("上传文件大小不符！");
        }

        /* 检查文件Mime类型 */
        if (! checkMime(uploadFile.getContentType())) {
            throw new UploadException("上传文件MIME类型不允许！");
        }

        /* 获取上传文件后缀，允许上传无后缀文件 */
        /* 检查文件后缀 */
        if (! checkExtension(getClientOriginalExtension(uploadFile))) {
            throw new UploadException("上传文件后缀不允许！");
        }

        return true;
    }

    /**
     * 根据指定的规则获取文件或目录名称
     * 回调函数，用于生成文件名
     * @param filename 原文件名
     * @param extname 修改件名扩展名
     * @return string 目录及文件名称
     */
    public String generateFilename(String filename, String extname)
    {
        if (filenameCallback != null) {
            filename = filenameCallback.apply(filename);
        }

        /* 文件保存后缀，支持强制更改文件后缀 */
        extname = getUploadOption().getSaveExtensionName() == null ? extname : getUploadOption().getSaveExtensionName();

        if (extname == null) {
            return filename;
        }

        return filename + extname;
    }

    /**
     * 根据指定的规则生成子目录名称
     *
     * @param filename 原文件名
     * @return string 目录及文件名称
     */
    public String generateSubDirname(String filename)
    {
        //callback 回调函数，用于生成文件名
        if (getUploadOption().isAutoSubDirs() && subDirnameCallback != null) {
            return subDirnameCallback.apply(filename);
        } else {
            return "";
        }
    }

    @Override
    public UploadResult upload(MultipartFile file) throws UploadException {
        if (file == null) {
            throw new UploadException("没有上传的文件！");
        }

        UploadProcess process = new UploadProcess(this);

        return process.upload(file);
    }

    @Override
    public UploadResult[] multiUpload(MultipartFile[] files) throws UploadException {
        if (files == null) {
            throw new UploadException("没有上传的文件！");
        }

        UploadResult[] results = new UploadResult[0];

        for (MultipartFile file : files) {
            results = ArrayUtils.add(results, upload(file));
        }

        return results;
    }

    /**
     * 设置上传配置选项
     */
    protected void settingUploadConfig() {

        Map<String, String> fileTypes = appUploadProperties().getDefaultFileTypes();

        fillUploadConfig(fileTypes);
    }

    protected void fillUploadConfig(Map<String, String> fileTypes) {
        String[] fileExts = fileTypes.keySet().stream().map(PHPFunctions::strtolower).toArray(String[]::new);
        setAllowedExtensions(fileExts);

        String[] fileMimes = fileTypes.values().stream().map(PHPFunctions::strtolower).toArray(String[]::new);
        setAllowedMimes(fileMimes);
    }

    protected AppUploadProperties appUploadProperties() {
        return SpringBeanFactory.getBean(AppUploadProperties.class);
    }

}
