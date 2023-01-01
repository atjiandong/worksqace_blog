package com.atjiandong.blog.pojo;

/**
 * @author atjiandong
 * @create 2022-09-02 13:44
 */
public class Page {

    //开头
    private Integer startRow;
    //结尾
    private Integer endRow;

    //第几页
    private int pageNum = 1;
    //每页个数
    private int pageSize=2;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "startRow=" + startRow +
                ", endRow=" + endRow +
                '}';
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Page() {
    }
}
