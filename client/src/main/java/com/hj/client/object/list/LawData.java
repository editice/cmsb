package com.hj.client.object.list;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/3/14  20:51
 */
public class LawData {
    long id;
    String title;
    String content;
    String time;
    String level;

    @Override
    public String toString() {
        return "LawData{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", time='" + time + '\'' +
               ", level='" + level + '\'' +
               ", time_range='" + time_range + '\'' +
               ", department='" + department + '\'' +
               ", time_range_desc='" + time_range_desc + '\'' +
               ", path='" + path + '\'' +
               '}';
    }

    String time_range;
    String department;
    String time_range_desc;
    String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime_range() {
        return time_range;
    }

    public void setTime_range(String time_range) {
        this.time_range = time_range;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTime_range_desc() {
        return time_range_desc;
    }

    public void setTime_range_desc(String time_range_desc) {
        this.time_range_desc = time_range_desc;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
