package io.github.mavenreposs.royalcms.facades;

import cn.dscmall.component.upload.*;

public class RC_Upload {

    private RC_Upload() {
    }

    public static Uploader uploader(UploadSelecter uploadSelecter) {
        return uploader(uploadSelecter.getClassInstance());
    }

    public static <T extends Uploader> Uploader uploader(T uploader) {
        return uploader(uploader, null);
    }

    public static Uploader uploader(UploadSelecter uploadSelecter, UploadOption option) {
        return uploader(uploadSelecter.getClassInstance(), option);
    }

    public static <T extends Uploader> Uploader uploader(T uploader, UploadOption option) {
        if (option != null) {
            uploader.setUploadOption(option);
        }
        uploader.setDisk(RC_Storage.disk().orElse(null));
        uploader.setFilenameCallback(new RandomFilenameRule());
        uploader.setSubDirnameCallback(new UploadSubDirRule());
        return uploader;
    }

}
