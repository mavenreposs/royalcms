package cn.royalcms.component.upload;

import cn.royalcms.component.upload.utils.Md5CaculateUtil;
import cn.royalcms.component.upload.utils.Sha1CaculateUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UploadProcess extends UploadProcessAbstract {
    public UploadProcess(Uploader uploader) {
        super(uploader);
    }

    @Override
    public UploadResult upload(MultipartFile file) throws UploadException {

        /* 文件上传检测 */
        if (! uploader.checkedUploadFile(file)) {
            return null;
        }

        String filename = uploader.getClientOriginalFileName(file);
        String extname = uploader.getClientOriginalExtension(file);
        // 生成保存文件名
        String savename = uploader.generateFilename(filename, extname);

        String subPath = uploader.generateSubDirname(filename);
        String savePath;
        if (subPath == null) {
            savePath = uploader.getUploadOption().getSavePath();
        } else {
            savePath = uploader.getUploadOption().getSavePath() + subPath;
        }

        UploadResult result = new UploadResult();
        result.setName(file.getOriginalFilename());
        result.setExtension(extname);
        result.setSaveName(savename);
        result.setSize(file.getSize());
        result.setType(file.getContentType());
        result.setSavePath(savePath);

        /* 获取文件hash */
        if (uploader.getUploadOption().isHash()) {
            try {
                result.setHashMd5(Md5CaculateUtil.getFileMD5(file.getInputStream()));
                result.setHashSha1(Sha1CaculateUtil.getFileSha1(file.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        save(file, result);

        if (uploader.getUploadSuccessCallback() != null) {
            uploader.getUploadSuccessCallback().accept(file, result);
        }

        return result;
    }

    /**
     * 保存指定文件
     *
     * @param file MultipartFile
     * @return boolean 保存状态，true-成功，false-失败
     */
    protected boolean save(MultipartFile file, UploadResult result) throws UploadException {
        return save(file, result, true);
    }

    /**
     * 保存指定文件
     *
     * @param file MultipartFile
     * @param replace 同名文件是否覆盖
     * @return boolean 保存状态，true-成功，false-失败
     */
    protected boolean save(MultipartFile file, UploadResult result, Boolean replace) throws UploadException {
        /* 不覆盖同名文件 */
        if (! replace && uploader.getDisk().exists(result.getFileName())) {
            throw new UploadException(String.format("存在同名文件%s", result.getFileName()));
        }

        /* 判断目录是否存在，不存在就创建 */
        if (! uploader.getDisk().isDirectory(result.getSavePath())) {
            uploader.getDisk().makeDirectory(result.getSavePath());
        }

        /* 移动文件 */
        if (uploader.getUploadSavingCallback() != null) {
            uploader.getUploadSavingCallback().accept(file, result);
        } else {
            if (uploader.getDisk() == null) {
                throw new UploadException("Storeage 存储对象未传入，请实例化后传入。");
            }

            try {
                uploader.getDisk().writeStream(result.getFileName(), file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new UploadException("文件上传保存错误！");
            }

        }

        return true;
    }

}
