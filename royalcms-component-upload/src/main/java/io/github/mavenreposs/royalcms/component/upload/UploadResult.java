package io.github.mavenreposs.royalcms.component.upload;

import java.io.Serializable;

public class UploadResult implements Serializable {

    private String name;

    private String type;

    private long size;

    private String extension;

    private String saveName;

    private String savePath;

    private String fileName;

    private String hashMd5;

    private String hashSha1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
        if (this.savePath != null && this.saveName != null) {
            this.fileName = savePath + saveName;
        }
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
        if (this.savePath != null && this.saveName != null) {
            this.fileName = this.savePath + this.saveName;
        }
    }

    public String getFileName() {
        if (fileName == null) {
            fileName = this.savePath + this.saveName;
        }
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHashMd5() {
        return hashMd5;
    }

    public void setHashMd5(String hashMd5) {
        this.hashMd5 = hashMd5;
    }

    public String getHashSha1() {
        return hashSha1;
    }

    public void setHashSha1(String hashSha1) {
        this.hashSha1 = hashSha1;
    }
}
