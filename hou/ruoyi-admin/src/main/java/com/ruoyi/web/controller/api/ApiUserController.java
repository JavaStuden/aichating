package com.ruoyi.web.controller.api;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysUserProfile;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.ISysUserProfileService;
import com.ruoyi.web.controller.dating.UserMatchController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息API接口
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserProfileService userProfileService;

    @Autowired
    private UserMatchController userMatchController;

    /**
     * 获取用户资料
     */
    @GetMapping("/profile")
    public AjaxResult getProfile(@RequestHeader("Authorization") String token) {
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
    @PostMapping("/profile")
    public AjaxResult updateProfile(@RequestHeader("Authorization") String token, @RequestBody SysUser user) {
        try {
            // 从token中获取用户ID
            Long userId = getUserIdFromToken(token);
            if (userId == null) {
                return AjaxResult.error("未登录");
            }

            // 构建SysUserProfile对象
            SysUserProfile profile = new SysUserProfile();
            profile.setUserId(userId);
            profile.setNickname(user.getUserName());
            profile.setGender(user.getSex());
            profile.setBio(user.getRemark());
            
            // 调用UserMatchController的updateProfile方法
            return userMatchController.updateProfile(profile);
        } catch (Exception e) {
            log.error("更新用户资料时发生错误", e);
            return AjaxResult.error("更新失败：" + e.getMessage());
        }
    }

    /**
     * 获取推荐用户列表
     */
    @GetMapping("/recommend")
    public AjaxResult getRecommendUsers(@RequestHeader("Authorization") String token,
                                      @RequestParam(required = false) Double latitude,
                                      @RequestParam(required = false) Double longitude) {
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
    @PostMapping("/like")
    @ResponseBody
    public AjaxResult likeUser(@RequestParam("targetUserId") Long targetUserId) {
        Long userId = getUserIdFromToken();
        boolean isMatch = userMatchController.likeUser(userId, targetUserId);
        return AjaxResult.success().put("isMatch", isMatch);
    }

    /**
     * 不喜欢用户
     */
    @PostMapping("/dislike")
    public AjaxResult dislikeUser(@RequestHeader("Authorization") String token, @RequestParam Long targetUserId) {
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
        // TODO: 实现token解析，获取用户ID
        return 1L;
    }

    private Long getUserIdFromToken() {
        // TODO: 实现token解析，获取用户ID
        return 1L;
    }
} 