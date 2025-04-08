package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户聊天消息对象 sys_user_message
 */
public class SysUserMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private Long messageId;

    /** 匹配ID */
    @Excel(name = "匹配ID")
    private Long matchId;

    /** 发送用户ID */
    @Excel(name = "发送用户ID")
    private Long fromUserId;

    /** 接收用户ID */
    @Excel(name = "接收用户ID")
    private Long toUserId;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 消息类型（0文本 1图片 2语音） */
    @Excel(name = "消息类型", readConverterExp = "0=文本,1=图片,2=语音")
    private String type;

    /** 是否已读（0未读 1已读） */
    @Excel(name = "是否已读", readConverterExp = "0=未读,1=已读")
    private String isRead;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("messageId", getMessageId())
                .append("matchId", getMatchId())
                .append("fromUserId", getFromUserId())
                .append("toUserId", getToUserId())
                .append("content", getContent())
                .append("type", getType())
                .append("isRead", getIsRead())
                .append("createTime", getCreateTime())
                .toString();
    }
} 