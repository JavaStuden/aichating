package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserMatchMapper;
import com.ruoyi.system.mapper.SysUserLikeMapper;
import com.ruoyi.system.mapper.SysUserProfileMapper;
import com.ruoyi.system.domain.SysUserMatch;
import com.ruoyi.system.domain.SysUserLike;
import com.ruoyi.system.domain.SysUserProfile;
import com.ruoyi.system.service.ISysUserMatchService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 用户匹配关系Service业务层处理
 */
@Service
public class SysUserMatchServiceImpl implements ISysUserMatchService
{
    @Autowired
    private SysUserMatchMapper sysUserMatchMapper;

    @Autowired
    private SysUserLikeMapper sysUserLikeMapper;

    @Autowired
    private SysUserProfileMapper sysUserProfileMapper;

    /**
     * 查询用户匹配关系
     * 
     * @param matchId 用户匹配关系ID
     * @return 用户匹配关系
     */
    @Override
    public SysUserMatch selectSysUserMatchById(Long matchId)
    {
        return sysUserMatchMapper.selectSysUserMatchById(matchId);
    }

    /**
     * 查询两个用户之间的匹配关系
     * 
     * @param userId 用户ID
     * @param matchedUserId 匹配用户ID
     * @return 用户匹配关系
     */
    @Override
    public SysUserMatch selectSysUserMatchByUsers(Long userId, Long matchedUserId)
    {
        return sysUserMatchMapper.selectSysUserMatchByUsers(userId, matchedUserId);
    }

    /**
     * 查询用户的所有匹配关系
     * 
     * @param userId 用户ID
     * @return 匹配关系列表
     */
    @Override
    public List<SysUserMatch> selectSysUserMatches(Long userId)
    {
        List<SysUserMatch> matches = sysUserMatchMapper.selectSysUserMatches(userId);
        
        // 获取匹配用户的个人资料信息
        for (SysUserMatch match : matches)
        {
            Long matchedUserId = match.getMatchedUserId();
            SysUserProfile matchedProfile = sysUserProfileMapper.selectSysUserProfileById(matchedUserId);
            
            if (matchedProfile != null)
            {
                match.setMatchedNickname(matchedProfile.getNickname());
                match.setMatchedAvatar(matchedProfile.getAvatar());
            }
        }
        
        return matches;
    }

    /**
     * 查询用户匹配关系列表
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 用户匹配关系
     */
    @Override
    public List<SysUserMatch> selectSysUserMatchList(SysUserMatch sysUserMatch)
    {
        return sysUserMatchMapper.selectSysUserMatchList(sysUserMatch);
    }

    /**
     * 处理用户喜欢操作
     * 
     * @param userId 用户ID
     * @param targetUserId 目标用户ID
     * @return 处理结果，包含是否匹配的信息
     */
    @Override
    public Map<String, Object> handleUserLike(Long userId, Long targetUserId)
    {
        Map<String, Object> result = new HashMap<>();
        result.put("isMatch", false);
        
        // 记录喜欢关系
        SysUserLike like = new SysUserLike();
        like.setUserId(userId);
        like.setTargetUserId(targetUserId);
        like.setType("0"); // 0表示对用户的喜欢
        like.setCreateTime(DateUtils.getNowDate());
        sysUserLikeMapper.insertSysUserLike(like);
        
        // 检查对方是否也喜欢当前用户
        SysUserLike targetLike = sysUserLikeMapper.selectUserLike(targetUserId, userId);
        
        // 如果互相喜欢，则创建匹配关系
        if (targetLike != null)
        {
            // 创建第一个匹配关系（当前用户视角）
            SysUserMatch match1 = new SysUserMatch();
            match1.setUserId(userId);
            match1.setMatchedUserId(targetUserId);
            match1.setStatus("1"); // 1表示已匹配
            match1.setCreateTime(DateUtils.getNowDate());
            sysUserMatchMapper.insertSysUserMatch(match1);
            
            // 创建第二个匹配关系（目标用户视角）
            SysUserMatch match2 = new SysUserMatch();
            match2.setUserId(targetUserId);
            match2.setMatchedUserId(userId);
            match2.setStatus("1");
            match2.setCreateTime(DateUtils.getNowDate());
            sysUserMatchMapper.insertSysUserMatch(match2);
            
            // 获取匹配用户的个人资料信息
            SysUserProfile targetProfile = sysUserProfileMapper.selectSysUserProfileById(targetUserId);
            
            result.put("isMatch", true);
            result.put("matchInfo", match1);
            
            if (targetProfile != null)
            {
                result.put("targetNickname", targetProfile.getNickname());
                result.put("targetAvatar", targetProfile.getAvatar());
            }
        }
        
        return result;
    }

    /**
     * 处理用户不喜欢操作
     * 
     * @param userId 用户ID
     * @param targetUserId 目标用户ID
     * @return 处理结果
     */
    @Override
    public int handleUserDislike(Long userId, Long targetUserId)
    {
        // 记录不喜欢关系，可以在实际业务中实现
        // 这里简单返回成功
        return 1;
    }

    /**
     * 新增用户匹配关系
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 结果
     */
    @Override
    public int insertSysUserMatch(SysUserMatch sysUserMatch)
    {
        sysUserMatch.setCreateTime(DateUtils.getNowDate());
        return sysUserMatchMapper.insertSysUserMatch(sysUserMatch);
    }

    /**
     * 修改用户匹配关系
     * 
     * @param sysUserMatch 用户匹配关系
     * @return 结果
     */
    @Override
    public int updateSysUserMatch(SysUserMatch sysUserMatch)
    {
        sysUserMatch.setUpdateTime(DateUtils.getNowDate());
        return sysUserMatchMapper.updateSysUserMatch(sysUserMatch);
    }

    /**
     * 更新最后一条消息
     * 
     * @param matchId 匹配ID
     * @param content 消息内容
     * @param time 消息时间
     * @return 结果
     */
    @Override
    public int updateLastMessage(Long matchId, String content, String time)
    {
        return sysUserMatchMapper.updateLastMessage(matchId, content, time);
    }

    /**
     * 增加未读消息数
     * 
     * @param matchId 匹配ID
     * @param userId 用户ID（接收者）
     * @return 结果
     */
    @Override
    public int increaseUnreadCount(Long matchId, Long userId)
    {
        return sysUserMatchMapper.increaseUnreadCount(matchId, userId);
    }

    /**
     * 重置未读消息数
     * 
     * @param matchId 匹配ID
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int resetUnreadCount(Long matchId, Long userId)
    {
        return sysUserMatchMapper.resetUnreadCount(matchId, userId);
    }

    /**
     * 删除用户匹配关系对象
     * 
     * @param matchId 用户匹配关系ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMatchById(Long matchId)
    {
        return sysUserMatchMapper.deleteSysUserMatchById(matchId);
    }

    /**
     * 批量删除用户匹配关系
     * 
     * @param matchIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMatchByIds(Long[] matchIds)
    {
        return sysUserMatchMapper.deleteSysUserMatchByIds(matchIds);
    }
} 