package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUserProfile;

/**
 * 用户个人资料 服务层
 */
public interface ISysUserProfileService
{
    /**
     * 根据用户ID查询用户个人资料
     */
    public SysUserProfile selectSysUserProfileById(Long userId);

    /**
     * 更新用户个人资料
     */
    public int updateSysUserProfile(SysUserProfile userProfile);
} 