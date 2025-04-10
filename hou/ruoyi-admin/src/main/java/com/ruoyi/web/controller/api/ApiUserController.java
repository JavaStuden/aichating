package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysUserProfile;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ISysUserProfileService;
import com.ruoyi.web.controller.dating.UserMatchController;

import java.util.List;

/**
 * 用户信息API接口
 */
public class ApiUserController {

    private ISysUserService userService;
    private ISysUserProfileService userProfileService;
    private UserMatchController userMatchController;

    /**
     * 获取用户资料
     */
    public AjaxResult getProfile(String token) {
        // 从token中获取用户ID
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return AjaxResult.error("未登录");
        }

        // 获取用户信息
        SysUser user = userService.selectUserById(userId);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        // 构建返回数据
        return AjaxResult.success(user);
    }

    /**
     * 更新用户资料
     */
    public AjaxResult updateProfile(String token, SysUser user) {
        try {
            // 从token中获取用户ID
            Long userId = getUserIdFromToken(token);
            if (userId == null) {
                return AjaxResult.error("未登录");
            }

            // 获取现有的用户资料
            SysUserProfile existingProfile = userProfileService.selectSysUserProfileById(userId);
            if (existingProfile == null) {
                // 如果用户资料不存在，创建新的
                existingProfile = new SysUserProfile();
                existingProfile.setUserId(userId);
            }

            // 更新用户资料
            existingProfile.setNickname(user.getUserName());
            existingProfile.setGender(user.getSex());
            existingProfile.setBio(user.getBio());
            existingProfile.setAvatar(user.getAvatar());
            
            // 直接调用userProfileService更新个人资料
            int rows = userProfileService.updateSysUserProfile(existingProfile);
            return rows > 0 ? AjaxResult.success() : AjaxResult.error("更新失败");
        } catch (Exception e) {
            return AjaxResult.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 获取推荐用户列表
     */
    public AjaxResult getRecommendUsers(String token, Double latitude, Double longitude) {
        // 从token中获取用户ID
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return AjaxResult.error("未登录");
        }

        // 获取推荐用户列表
        List<SysUser> users = userService.selectRecommendUsers(userId, latitude, longitude);
        return AjaxResult.success(users);
    }

    /**
     * 喜欢用户
     */
    public AjaxResult likeUser(Long targetUserId) {
        return userMatchController.likeUser(targetUserId);
    }

    /**
     * 不喜欢用户
     */
    public AjaxResult dislikeUser(String token, Long targetUserId) {
        // 从token中获取用户ID
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return AjaxResult.error("未登录");
        }

        // 处理不喜欢操作
        userService.dislikeUser(userId, targetUserId);
        return AjaxResult.success();
    }

    /**
     * 从token中获取用户ID（实际项目中需要实现）
     */
    private Long getUserIdFromToken(String token) {
        // 简化实现，返回固定值
        return 1L;
    }

    private Long getUserIdFromToken() {
        // 简化实现，返回固定值
        return 1L;
    }
} 