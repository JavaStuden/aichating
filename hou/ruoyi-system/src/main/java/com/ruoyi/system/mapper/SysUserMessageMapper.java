package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserMessage;

import java.util.List;

/**
 * 用户聊天消息Mapper接口
 */
public interface SysUserMessageMapper
{
    /**
     * 查询用户聊天消息
     * 
     * @param messageId 用户聊天消息ID
     * @return 用户聊天消息
     */
    public SysUserMessage selectSysUserMessageById(Long messageId);

    /**
     * 查询用户聊天消息列表
     * 
     * @param sysUserMessage 用户聊天消息
     * @return 用户聊天消息集合
     */
    public List<SysUserMessage> selectSysUserMessageList(SysUserMessage sysUserMessage);

    /**
     * 查询匹配关系的聊天消息
     * 
     * @param matchId 匹配ID
     * @return 聊天消息列表
     */
    public List<SysUserMessage> selectMessagesByMatchId(Long matchId);

    /**
     * 将用户的未读消息标记为已读
     * 
     * @param matchId 匹配ID
     * @param userId 用户ID
     * @return 结果
     */
    public int markMessagesAsRead(Long matchId, Long userId);

    /**
     * 新增用户聊天消息
     * 
     * @param sysUserMessage 用户聊天消息
     * @return 结果
     */
    public int insertSysUserMessage(SysUserMessage sysUserMessage);

    /**
     * 修改用户聊天消息
     * 
     * @param sysUserMessage 用户聊天消息
     * @return 结果
     */
    public int updateSysUserMessage(SysUserMessage sysUserMessage);

    /**
     * 删除用户聊天消息
     * 
     * @param messageId 用户聊天消息ID
     * @return 结果
     */
    public int deleteSysUserMessageById(Long messageId);

    /**
     * 批量删除用户聊天消息
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserMessageByIds(Long[] messageIds);
} 