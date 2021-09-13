package cn.royalcms.contracts.storage;

public interface Cloud extends Storage {

    /**
     * Get the URL for the file at the given path.
     *
     * @param  path url path
     * @return string
     */
    String url(String path);

}
