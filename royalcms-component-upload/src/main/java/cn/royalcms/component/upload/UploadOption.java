package cn.royalcms.component.upload;

import java.io.Serializable;

import static io.github.mavenreposs.php.functions.PHPFunctions.rtrim;

public enum UploadOption implements Serializable {

    DefaultUploadOption,
    ;

    // 文件保存后缀，空则使用原后缀
    private String saveExtensionName;

    // 存在同名是否覆盖
    private boolean isReplace = false;

    // 是否生成hash编码
    private boolean isHash = true;

    // 自动子目录保存文件
    private boolean isAutoSubDirs = true;

    // 上传的文件大小限制 (0:不做限制)
    private int maxSize = 0;

    // 上传目录根路径
    private String rootPath;

    // 保存的路径，相对于上传目录的相对路径
    private String savePath;

    // 允许上传的文件后缀
    private String[] extensions;

    // 允许上传的文件MiMe类型
    private String[] mimes;

    public UploadOption setReplace(boolean replace) {
        isReplace = replace;
        return this;
    }

    public UploadOption setHash(boolean hash) {
        isHash = hash;
        return this;
    }

    public UploadOption setAutoSubDirs(boolean autoSubDirs) {
        isAutoSubDirs = autoSubDirs;
        return this;
    }

    public UploadOption setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public String getSaveExtensionName() {
        return saveExtensionName;
    }

    public UploadOption setSaveExtensionName(String saveExtensionName) {
        this.saveExtensionName = saveExtensionName;
        return this;
    }

    public boolean isReplace() {
        return isReplace;
    }

    public boolean isHash() {
        return isHash;
    }

    public boolean isAutoSubDirs() {
        return isAutoSubDirs;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getSavePath() {
        return savePath;
    }

    public UploadOption setSavePath(String savePath) {
        this.savePath = rtrim(savePath, "/") + "/";
        return this;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public UploadOption setExtensions(String[] extensions) {
        this.extensions = extensions;
        return this;
    }

    public String[] getMimes() {
        return mimes;
    }

    public UploadOption setMimes(String[] mimes) {
        this.mimes = mimes;
        return this;
    }
}
