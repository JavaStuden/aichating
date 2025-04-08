package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 用户动态对象 sys_user_moment
 */
public class SysUserMoment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 动态ID */
    private Long momentId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 动态内容 */
    @Excel(name = "动态内容")
    private String content;

    /** 图片列表，以逗号分隔 */
    private String images;

    /** 图片列表，转换后的数组形式 */
    private List<String> imagesList;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer likeCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Integer commentCount;

    /** 是否点赞，用于前端显示 */
    private Boolean isLiked;

    /** 用户昵称，用于前端显示 */
    private String nickname;

    /** 用户头像，用于前端显示 */
    private String avatar;

    public Long getMomentId() {
        return momentId;
    }

    public void setMomentId(Long momentId) {
        this.momentId = momentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("momentId", getMomentId())
                .append("userId", getUserId())
                .append("content", getContent())
                .append("images", getImages())
                .append("likeCount", getLikeCount())
                .append("commentCount", getCommentCount())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
} 