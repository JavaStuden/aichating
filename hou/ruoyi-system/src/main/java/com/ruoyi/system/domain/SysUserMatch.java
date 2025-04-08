package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户匹配关系对象 sys_user_match
 */
public class SysUserMatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 匹配ID */
    private Long matchId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 匹配用户ID */
    @Excel(name = "匹配用户ID")
    private Long matchedUserId;

    /** 匹配状态（0未匹配 1已匹配） */
    @Excel(name = "匹配状态", readConverterExp = "0=未匹配,1=已匹配")
    private String status;

    /** 最后消息内容 */
    private String lastMessage;

    /** 最后消息时间 */
    private String lastMessageTime;

    /** 未读消息数 */
    private Integer unreadCount;

    /** 匹配用户的昵称，用于前端显示 */
    private String matchedNickname;

    /** 匹配用户的头像，用于前端显示 */
    private String matchedAvatar;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMatchedUserId() {
        return matchedUserId;
    }

    public void setMatchedUserId(Long matchedUserId) {
        this.matchedUserId = matchedUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getMatchedNickname() {
        return matchedNickname;
    }

    public void setMatchedNickname(String matchedNickname) {
        this.matchedNickname = matchedNickname;
    }

    public String getMatchedAvatar() {
        return matchedAvatar;
    }

    public void setMatchedAvatar(String matchedAvatar) {
        this.matchedAvatar = matchedAvatar;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matchId", getMatchId())
                .append("userId", getUserId())
                .append("matchedUserId", getMatchedUserId())
                .append("status", getStatus())
                .append("lastMessage", getLastMessage())
                .append("lastMessageTime", getLastMessageTime())
                .append("unreadCount", getUnreadCount())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
} 