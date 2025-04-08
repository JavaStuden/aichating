package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ISysUserService userService;

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
        // 从token中获取用户ID
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return AjaxResult.error("未登录");
        }

        // 设置用户ID
        user.setUserId(userId);

        // 更新用户信息
        int rows = userService.updateUser(user);
        if (rows > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("更新失败");
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
    public AjaxResult likeUser(@RequestHeader("Authorization") String token, @RequestParam Long targetUserId) {
        // 从token中获取用户ID
        Long userId = getUserIdFromToken(token);
        if (userId == null) {
            return AjaxResult.error("未登录");
        }

        // 处理喜欢操作
        boolean isMatch = userService.likeUser(userId, targetUserId);
        return AjaxResult.success(isMatch);
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
     * 从token中获取用户ID
     */
    private Long getUserIdFromToken(String token) {
        // TODO: 实现从token中解析用户ID的逻辑
        // 这里需要根据实际的token格式和解析方式来实现
        return 1L; // 临时返回固定值，实际项目中需要实现token解析
    }
} 