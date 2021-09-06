package cn.royalcms.component.upload;

import java.lang.reflect.InvocationTargetException;

public enum UploadSelecter {
    // 图片上传
    ImageUploader(ImageUploader.class),
    // 文件上传
    FileUploader(FileUploader.class),
    // 临时图片上传
    TempImageUploader(TempImageUploader.class);
    ;

    private final Class<? extends Uploader> clazz;

    UploadSelecter(Class<? extends Uploader> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Uploader> getClazz() {
        return clazz;
    }

    public Uploader getClassInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
