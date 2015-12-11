package com.hj.dal.domain.dataobject;

/**
 * @author tinglang (editice@gmail.com)
 * @since 2015/2/6  1:33
 */
public class CMSBDO implements Comparable {
    String bname;

    int cnt;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(((CMSBDO)o).cnt,cnt);
    }
}
