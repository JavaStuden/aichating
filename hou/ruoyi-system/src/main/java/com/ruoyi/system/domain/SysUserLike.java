package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户点赞关系对象 sys_user_like
 */
public class SysUserLike extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞ID */
    private Long likeId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 被点赞用户ID */
    @Excel(name = "被点赞用户ID")
    private Long targetUserId;

    /** 点赞类型（0用户 1动态） */
    @Excel(name = "点赞类型", readConverterExp = "0=用户,1=动态")
    private String type;

    /** 动态ID */
    private Long momentId;

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMomentId() {
        return momentId;
    }

    public void setMomentId(Long momentId) {
        this.momentId = momentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("likeId", getLikeId())
                .append("userId", getUserId())
                .append("targetUserId", getTargetUserId())
                .append("type", getType())
                .append("momentId", getMomentId())
                .append("createTime", getCreateTime())
                .toString();
    }
} 