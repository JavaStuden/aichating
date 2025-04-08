package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 用户个人资料对象 sys_user_profile
 */
public class SysUserProfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 年龄 */
    @Excel(name = "年龄")
    private Integer age;

    /** 性别（0男 1女） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女")
    private String gender;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 照片列表，以逗号分隔 */
    private String photos;

    /** 照片列表，转换后的数组形式 */
    private List<String> photosList;

    /** 标签，以逗号分隔 */
    private String tags;

    /** 标签列表，转换后的数组形式 */
    private List<String> tagsList;

    /** 个人简介 */
    @Excel(name = "个人简介")
    private String bio;

    /** 纬度 */
    private Double latitude;

    /** 经度 */
    private Double longitude;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public List<String> getPhotosList() {
        return photosList;
    }

    public void setPhotosList(List<String> photosList) {
        this.photosList = photosList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("nickname", getNickname())
                .append("age", getAge())
                .append("gender", getGender())
                .append("avatar", getAvatar())
                .append("photos", getPhotos())
                .append("tags", getTags())
                .append("bio", getBio())
                .append("latitude", getLatitude())
                .append("longitude", getLongitude())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
} 