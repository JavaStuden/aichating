package com.ruoyi.web.controller.dating;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysUserProfile;
import com.ruoyi.system.domain.SysUserMatch;
import com.ruoyi.system.domain.SysUserMessage;
import com.ruoyi.system.service.ISysUserProfileService;
import com.ruoyi.system.service.ISysUserMatchService;

/**
 * 交友匹配控制器
 */
@RestController
@RequestMapping("/api/dating")
public class UserMatchController extends BaseController
{
    @Autowired
    private ISysUserProfileService userProfileService;

    @Autowired
    private ISysUserMatchService userMatchService;

    /**
     * 获取推荐用户列表
     */
    @GetMapping("/recommend")
    public AjaxResult recommend(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        List<SysUserProfile> profiles = userProfileService.selectRecommendProfiles(userId, latitude, longitude);
        return AjaxResult.success(profiles);
    }

    /**
     * 获取用户详细信息
     */
    @GetMapping("/profile/{userId}")
    public AjaxResult getProfile(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(userProfileService.selectSysUserProfileById(userId));
    }

    /**
     * 更新用户个人资料
     */
    @PostMapping("/profile")
    public AjaxResult updateProfile(@RequestBody SysUserProfile profile)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        profile.setUserId(userId);
        return toAjax(userProfileService.updateSysUserProfile(profile));
    }

    /**
     * 喜欢用户
     */
    @PostMapping("/like")
    public AjaxResult likeUser(@RequestParam Long targetUserId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        Map<String, Object> result = userMatchService.handleUserLike(userId, targetUserId);
        return AjaxResult.success(result);
    }

    /**
     * 不喜欢用户
     */
    @PostMapping("/dislike")
    public AjaxResult dislikeUser(@RequestParam Long targetUserId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        userMatchService.handleUserDislike(userId, targetUserId);
        return AjaxResult.success();
    }

    /**
     * 获取匹配列表
     */
    @GetMapping("/matches")
    public AjaxResult getMatches()
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        List<SysUserMatch> matches = userMatchService.selectSysUserMatches(userId);
        return AjaxResult.success(matches);
    }

    /**
     * 获取聊天消息
     */
    @GetMapping("/messages/{matchId}")
    public AjaxResult getMessages(@PathVariable("matchId") Long matchId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        
        // 将未读消息标记为已读
        userMatchService.resetUnreadCount(matchId, userId);
        
        // 获取消息列表 - 这里需要实现MessageService
        // List<SysUserMessage> messages = userMessageService.selectMessagesByMatchId(matchId);
        return AjaxResult.success();
    }

    /**
     * 发送消息
     */
    @PostMapping("/message")
    public AjaxResult sendMessage(@RequestBody SysUserMessage message)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        message.setFromUserId(userId);
        message.setIsRead("0"); // 设置为未读
        
        // 增加接收方的未读消息数
        userMatchService.increaseUnreadCount(message.getMatchId(), message.getToUserId());
        
        // 更新最后一条消息
        String time = com.ruoyi.common.utils.DateUtils.dateTimeNow();
        userMatchService.updateLastMessage(message.getMatchId(), message.getContent(), time);
        
        // 消息服务需要实现
        // return toAjax(userMessageService.insertSysUserMessage(message));
        return AjaxResult.success();
    }

    /**
     * 获取当前登录用户ID（实际项目中应从登录信息中获取）
     */
    private Long getUserId()
    {
        // TODO: 从登录信息中获取当前用户ID
        // 这里暂时返回1作为示例
        return 1L;
    }
} 