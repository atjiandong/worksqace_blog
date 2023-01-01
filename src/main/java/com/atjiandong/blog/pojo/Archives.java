package com.atjiandong.blog.pojo;

/**
 * @author atjiandong
 * @create 2022-10-10 23:13
 */
public class Archives {

    private  Long blogId;

    private String blogTitle;

    private  String blogUpdateTime;

    private String blogFlag;


    @Override
    public String toString() {
        return "Archives{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogUpdateTime='" + blogUpdateTime + '\'' +
                ", blogFlag='" + blogFlag + '\'' +
                '}';
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogUpdateTime() {
        return blogUpdateTime;
    }

    public void setBlogUpdateTime(String blogUpdateTime) {
        this.blogUpdateTime = blogUpdateTime;
    }

    public String getBlogFlag() {
        return blogFlag;
    }

    public void setBlogFlag(String blogFlag) {
        this.blogFlag = blogFlag;
    }
}
