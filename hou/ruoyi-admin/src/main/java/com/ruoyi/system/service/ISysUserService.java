package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import java.util.List;

/**
 * 用户 业务层
 */
public interface ISysUserService
{
    /**
     * 根据条件分页查询用户列表
     */
    public List<SysUser> selectUserList(SysUser user);

    /**
     * 通过用户ID查询用户
     */
    public SysUser selectUserById(Long userId);

    /**
     * 获取推荐用户列表
     */
    public List<SysUser> selectRecommendUsers(Long userId, Double latitude, Double longitude);

    /**
     * 不喜欢用户
     */
    public void dislikeUser(Long userId, Long targetUserId);
} 