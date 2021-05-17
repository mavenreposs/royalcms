package io.github.mavenreposs.royalcms.component.upload.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Arrays;

public class Sha1CaculateUtil {

    /**
     * 获取一个文件的md5值(可处理大文件)
     * @return md5 value
     */
    public static String getFileSha1(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                SHA1.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(SHA1.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个文件的md5值(可处理大文件)
     * @return md5 value
     */
    public static String getFileSha1(FileInputStream fileInputStream) {
        try {
            MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                SHA1.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(SHA1.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个文件的md5值(可处理大文件)
     * @return md5 value
     */
    public static String getFileSha1(InputStream inputStream) {
        try {
            MessageDigest SHA1 = MessageDigest.getInstance("SHA-1");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                SHA1.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(SHA1.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 求一个字符串的md5值
     * @param target 字符串
     * @return md5 value
     */
    public static String sha1(String target) {
        return Arrays.toString(DigestUtils.sha1(target));
    }

}
