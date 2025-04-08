package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUserProfile;

import java.util.List;

/**
 * 用户个人资料Service接口
 */
public interface ISysUserProfileService
{
    /**
     * 查询用户个人资料
     * 
     * @param userId 用户个人资料ID
     * @return 用户个人资料
     */
    public SysUserProfile selectSysUserProfileById(Long userId);

    /**
     * 查询用户个人资料列表
     * 
     * @param sysUserProfile 用户个人资料
     * @return 用户个人资料集合
     */
    public List<SysUserProfile> selectSysUserProfileList(SysUserProfile sysUserProfile);

    /**
     * 查询推荐用户列表
     * 
     * @param userId 当前用户ID
     * @param latitude 纬度
     * @param longitude 经度
     * @return 用户列表
     */
    public List<SysUserProfile> selectRecommendProfiles(Long userId, Double latitude, Double longitude);

    /**
     * 新增用户个人资料
     * 
     * @param sysUserProfile 用户个人资料
     * @return 结果
     */
    public int insertSysUserProfile(SysUserProfile sysUserProfile);

    /**
     * 修改用户个人资料
     * 
     * @param sysUserProfile 用户个人资料
     * @return 结果
     */
    public int updateSysUserProfile(SysUserProfile sysUserProfile);

    /**
     * 批量删除用户个人资料
     * 
     * @param userIds 需要删除的用户个人资料ID
     * @return 结果
     */
    public int deleteSysUserProfileByIds(Long[] userIds);

    /**
     * 删除用户个人资料信息
     * 
     * @param userId 用户个人资料ID
     * @return 结果
     */
    public int deleteSysUserProfileById(Long userId);
} 