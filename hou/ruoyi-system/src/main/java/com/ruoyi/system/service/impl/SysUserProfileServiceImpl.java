package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysUserProfileMapper;
import com.ruoyi.system.domain.SysUserProfile;
import com.ruoyi.system.service.ISysUserProfileService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;

/**
 * 用户个人资料Service业务层处理
 */
@Service
public class SysUserProfileServiceImpl implements ISysUserProfileService
{
    @Autowired
    private SysUserProfileMapper sysUserProfileMapper;

    /**
     * 查询用户个人资料
     * 
     * @param userId 用户个人资料ID
     * @return 用户个人资料
     */
    @Override
    public SysUserProfile selectSysUserProfileById(Long userId)
    {
        SysUserProfile profile = sysUserProfileMapper.selectSysUserProfileById(userId);
        if (profile != null)
        {
            // 转换照片和标签为列表
            if (StringUtils.isNotEmpty(profile.getPhotos()))
            {
                profile.setPhotosList(Arrays.asList(profile.getPhotos().split(",")));
            }
            if (StringUtils.isNotEmpty(profile.getTags()))
            {
                profile.setTagsList(Arrays.asList(profile.getTags().split(",")));
            }
        }
        return profile;
    }

    /**
     * 查询用户个人资料列表
     * 
     * @param sysUserProfile 用户个人资料
     * @return 用户个人资料
     */
    @Override
    public List<SysUserProfile> selectSysUserProfileList(SysUserProfile sysUserProfile)
    {
        List<SysUserProfile> profiles = sysUserProfileMapper.selectSysUserProfileList(sysUserProfile);
        
        // 处理每个用户的照片和标签
        for (SysUserProfile profile : profiles)
        {
            if (StringUtils.isNotEmpty(profile.getPhotos()))
            {
                profile.setPhotosList(Arrays.asList(profile.getPhotos().split(",")));
            }
            if (StringUtils.isNotEmpty(profile.getTags()))
            {
                profile.setTagsList(Arrays.asList(profile.getTags().split(",")));
            }
        }
        
        return profiles;
    }

    /**
     * 查询推荐用户列表
     * 
     * @param userId 当前用户ID
     * @param latitude 纬度
     * @param longitude 经度
     * @return 用户列表
     */
    @Override
    public List<SysUserProfile> selectRecommendProfiles(Long userId, Double latitude, Double longitude)
    {
        List<SysUserProfile> profiles = sysUserProfileMapper.selectRecommendProfiles(userId, latitude, longitude);
        
        // 处理每个用户的照片和标签
        for (SysUserProfile profile : profiles)
        {
            if (StringUtils.isNotEmpty(profile.getPhotos()))
            {
                profile.setPhotosList(Arrays.asList(profile.getPhotos().split(",")));
            }
            if (StringUtils.isNotEmpty(profile.getTags()))
            {
                profile.setTagsList(Arrays.asList(profile.getTags().split(",")));
            }
        }
        
        return profiles;
    }

    /**
     * 新增用户个人资料
     * 
     * @param sysUserProfile 用户个人资料
     * @return 结果
     */
    @Override
    public int insertSysUserProfile(SysUserProfile sysUserProfile)
    {
        // 转换列表为字符串存储
        if (sysUserProfile.getPhotosList() != null && !sysUserProfile.getPhotosList().isEmpty())
        {
            sysUserProfile.setPhotos(String.join(",", sysUserProfile.getPhotosList()));
        }
        
        if (sysUserProfile.getTagsList() != null && !sysUserProfile.getTagsList().isEmpty())
        {
            sysUserProfile.setTags(String.join(",", sysUserProfile.getTagsList()));
        }
        
        return sysUserProfileMapper.insertSysUserProfile(sysUserProfile);
    }

    /**
     * 修改用户个人资料
     * 
     * @param sysUserProfile 用户个人资料
     * @return 结果
     */
    @Override
    public int updateSysUserProfile(SysUserProfile sysUserProfile)
    {
        // 转换列表为字符串存储
        if (sysUserProfile.getPhotosList() != null && !sysUserProfile.getPhotosList().isEmpty())
        {
            sysUserProfile.setPhotos(String.join(",", sysUserProfile.getPhotosList()));
        }
        
        if (sysUserProfile.getTagsList() != null && !sysUserProfile.getTagsList().isEmpty())
        {
            sysUserProfile.setTags(String.join(",", sysUserProfile.getTagsList()));
        }
        
        return sysUserProfileMapper.updateSysUserProfile(sysUserProfile);
    }

    /**
     * 删除用户个人资料对象
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteSysUserProfileById(Long userId)
    {
        return sysUserProfileMapper.deleteSysUserProfileById(userId);
    }

    /**
     * 批量删除用户个人资料
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysUserProfileByIds(Long[] userIds)
    {
        return sysUserProfileMapper.deleteSysUserProfileByIds(userIds);
    }
} 