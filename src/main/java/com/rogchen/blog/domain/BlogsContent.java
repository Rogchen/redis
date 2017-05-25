package com.rogchen.blog.domain;

import com.rogchen.common.BaseEntity;

import java.util.Date;

/**
 * @Author Rogchen{cbs@xmyr.cn}
 * Created by chbs on 2017/4/10.
 * {tags}
 */
public class BlogsContent extends BaseEntity<BlogsContent>{

    private Long id;
    private String blogTitle;  //外部标题
    private String blogContentTitle;
    private String blogContent;
    private Date createTime;
    private Long adminUserId;
    private Date lastUpdateTime;
    private Long lastUpdateUserId;
    private Long blogPicId;
    private Long statusCd;
    private Long isHotCd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContentTitle() {
        return blogContentTitle;
    }

    public void setBlogContentTitle(String blogContentTitle) {
        this.blogContentTitle = blogContentTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(Long lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Long getBlogPicId() {
        return blogPicId;
    }

    public void setBlogPicId(Long blogPicId) {
        this.blogPicId = blogPicId;
    }

    public Long getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Long statusCd) {
        this.statusCd = statusCd;
    }

    public Long getIsHotCd() {
        return isHotCd;
    }

    public void setIsHotCd(Long isHotCd) {
        this.isHotCd = isHotCd;
    }
}
