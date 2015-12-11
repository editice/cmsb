package com.hj.client.object.list;

import java.util.Date;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/14  22:55
 */
public class RsData implements Comparable {
    long id;
    Date upload_time;
    long file_size;
    String title;
    String path;

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(Date upload_time) {
        this.upload_time = upload_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int compareTo(Object o) {
        return ((RsData)o).upload_time.compareTo(upload_time);
    }
}
