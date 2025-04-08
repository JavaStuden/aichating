package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserMatch;

import java.util.List;

/**
 * 用户匹配关系Mapper接口
 */
public interface SysUserMatchMapper
{
    /**
     * 查询用户匹配关系
     * 
     * @param matchId 用户匹配关系ID
     * @return 用户匹配关系
     */
    public SysUserMatch selectSysUserMatchById(Long matchId);

    /**
     * 查询两个用户之间的匹配关系
     * 
     * @param userId 用户ID
     * @param matchedUserId 匹配用户ID
     * @return 用户匹配关系
     */
    public SysUserMatch selectSysUserMatchByUsers(Long userId, Long matchedUserId);

    /**
     * 查询用户的所有匹配关系
     * 
     * @param userId 用户ID
     * @return 匹配关系列表
     */
    public List<SysUserMatch> selectSysUserMatches(Long userId);

    /**
     * 查询用户匹配关系列表
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 用户匹配关系集合
     */
    public List<SysUserMatch> selectSysUserMatchList(SysUserMatch sysUserMatch);

    /**
     * 新增用户匹配关系
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 结果
     */
    public int insertSysUserMatch(SysUserMatch sysUserMatch);

    /**
     * 修改用户匹配关系
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 结果
     */
    public int updateSysUserMatch(SysUserMatch sysUserMatch);

    /**
     * 更新最后一条消息
     * 
     * @param matchId 匹配ID
     * @param content 消息内容
     * @param time 消息时间
     * @return 结果
     */
    public int updateLastMessage(Long matchId, String content, String time);

    /**
     * 增加未读消息数
     * 
     * @param matchId 匹配ID
     * @param userId 用户ID（接收者）
     * @return 结果
     */
    public int increaseUnreadCount(Long matchId, Long userId);

    /**
     * 重置未读消息数
     * 
     * @param matchId 匹配ID
     * @param userId 用户ID
     * @return 结果
     */
    public int resetUnreadCount(Long matchId, Long userId);

    /**
     * 删除用户匹配关系
     * 
     * @param matchId 用户匹配关系ID
     * @return 结果
     */
    public int deleteSysUserMatchById(Long matchId);

    /**
     * 批量删除用户匹配关系
     * 
     * @param matchIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserMatchByIds(Long[] matchIds);
} 