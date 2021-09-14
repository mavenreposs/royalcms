package cn.royalcms.contracts.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Storage {

    /**
     * The public visibility setting.
     */
    public static final String VISIBILITY_PUBLIC = "public";

    /**
     * The private visibility setting.
     */
    public static final String VISIBILITY_PRIVATE = "private";

//    /**
//     * Put object.
//     *
//     * @param bucketName  the bucket name
//     * @param objectName  the object name
//     * @param inputStream the input stream
//     * @param contentType the content type
//     * @throws Exception the exception
//     */
//    void putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;
//
//    /**
//     * Gets object.
//     *
//     * @param bucketName the bucket name
//     * @param objectName the object name
//     * @return the object
//     */
//    InputStream getObject(String bucketName, String objectName) throws Exception;
//
//    /**
//     * Gets object url.
//     *
//     * @param bucketName the bucket name
//     * @param objectName the object name
//     * @return the object url
//     */
//    String getObjectUrl(String bucketName, String objectName) throws Exception;
//
//    /**
//     * Remove object.
//     *
//     * @param bucketName the bucket name
//     * @param objectName the object name
//     */
//    void removeObject(String bucketName, String objectName) throws Exception;

    /**
     * Determine if a file exists.
     *
     * @param  path String
     * @return bool
     */
    boolean exists(String path);

    /**
     * Get the contents of a file.
     *
     * @param  path String
     * @return string
     * @throws IOException IOException
     */
    String get(String path) throws IOException;

    /**
     * Get a resource to read the file.
     *
     * @param  path String
     * @return resource|null The path resource or null on failure.
     * @throws IOException IOException
     */
    byte[] readStream(String path) throws IOException;

    /**
     * Write the contents of a file.
     *
     * @param  path String
     * @param  contents String
     * @return bool
     */
    boolean put(String path, String contents);

    /**
     * Write the contents of a file.
     *
     * @param  path String
     * @param  contents byte[]
     * @return bool
     */
    boolean put(String path, byte[] contents);

    /**
     * Write the contents of a file.
     *
     * @param  path byte[]
     * @param  contents InputStream
     * @param  options Map
     * @return bool
     */
    boolean put(String path, InputStream contents, Map<String, Object> options);

    /**
     * Create a file or update if exists.
     *
     * @param path     The path to the file.
     * @param resource The file handle.
     * @param options   An optional configuration array.
     *
     * @return bool True on success, false on failure.
     */
    boolean putStream(String path, InputStream resource, Map<String, Object> options);

    /**
     * Write a new file using a stream.
     *
     * @param  path String
     * @param  resource InputStream
     * @return bool
     */
    boolean writeStream(String path, InputStream resource);

    /**
     * Write a new file using a stream.
     *
     * @param  path String
     * @param  resource InputStream
     * @param  options Map
     * @return bool
     */
    boolean writeStream(String path, InputStream resource, Map<String, Object> options);

    /**
     * Get the visibility for the given path.
     *
     * @param  path String
     * @return string
     */
    String getVisibility(String path);

    /**
     * Set the visibility for the given path.
     *
     * @param  path String
     * @param  visibility String
     * @return bool
     */
    boolean setVisibility(String path, String visibility);

    /**
     * Prepend to a file.
     *
     * @param  path String
     * @param  data String
     * @return bool
     */
    boolean prepend(String path, String data);

    /**
     * Append to a file.
     *
     * @param  path String
     * @param  data String
     * @return bool
     */
    boolean append(String path, String data);

    /**
     * Delete the file at a given path.
     *
     * @param  paths String
     * @return bool
     */
    boolean delete(String paths);

    /**
     * Delete the file at a given path.
     *
     * @param  paths String
     * @return bool
     */
    boolean delete(String... paths);

    /**
     * Copy a file to a new location.
     *
     * @param  from String
     * @param  to String
     * @return bool
     */
    boolean copy(String from, String to);

    /**
     * Move a file to a new location.
     *
     * @param  from String
     * @param  to String
     * @return bool
     */
    boolean move(String from, String to);

    /**
     * Get the file size of a given file.
     *
     * @param  path String
     * @return int
     */
    long size(String path);

    /**
     * Get the file's last modification time.
     *
     * @param  path String
     * @return int
     */
    long lastModified(String path);

    /**
     * Get an array of all files in a directory.
     *
     * @return array
     */
    Collection<File> files();

    /**
     * Get an array of all files in a directory.
     *
     * @param  directory default null
     * @return array
     */
    Collection<File> files(String directory);

    /**
     * Get an array of all files in a directory.
     *
     * @param  directory default null
     * @param  recursive default false
     * @return array
     */
    Collection<File> files(String directory, boolean recursive);

    /**
     * Get all of the files from the given directory (recursive).
     *
     * @return array
     */
    Collection<File> allFiles();

    /**
     * Get all of the files from the given directory (recursive).
     *
     * @param  directory String
     * @return array
     */
    Collection<File> allFiles(String directory);

    /**
     * Get all of the directories within a given directory.
     *
     * @return array
     */
    Collection<File> directories();

    /**
     * Get all of the directories within a given directory.
     *
     * @param  directory String
     * @return array
     */
    Collection<File> directories(String directory);

    /**
     * Get all of the directories within a given directory.
     *
     * @param  directory default null
     * @param  recursive default false
     * @return array
     */
    Collection<File> directories(String directory, boolean recursive);

    /**
     * Get all (recursive) of the directories within a given directory.
     *
     * @return array
     */
    Collection<File> allDirectories();

    /**
     * Get all (recursive) of the directories within a given directory.
     *
     * @param  directory String
     * @return array
     */
    Collection<File> allDirectories(String directory);

    /**
     * Create a directory.
     *
     * @param  path String
     * @return bool
     */
    boolean makeDirectory(String path);

    /**
     * Recursively delete a directory.
     *
     * @param  directory String
     * @return bool
     */
    boolean deleteDirectory(String directory);

    boolean isDirectory(String directory);

    boolean isFile(String filePath);

}
