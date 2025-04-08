package com.ruoyi.web.controller.dating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysUserMoment;
import com.ruoyi.system.mapper.SysUserMomentMapper;
import com.ruoyi.system.mapper.SysUserLikeMapper;

/**
 * 用户动态（朋友圈）控制器
 */
@RestController
@RequestMapping("/api/dating/moments")
public class UserMomentController extends BaseController
{
    @Autowired
    private SysUserMomentMapper momentMapper;
    
    @Autowired
    private SysUserLikeMapper likeMapper;

    /**
     * 获取动态列表
     */
    @GetMapping
    public AjaxResult list()
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        
        // 获取关注的人和自己的动态
        List<SysUserMoment> moments = momentMapper.selectFollowingMoments(userId);
        
        // 检查当前用户是否对每个动态点赞
        for (SysUserMoment moment : moments)
        {
            if (likeMapper.selectMomentLike(userId, moment.getMomentId()) != null)
            {
                moment.setIsLiked(true);
            }
            else
            {
                moment.setIsLiked(false);
            }
        }
        
        return AjaxResult.success(moments);
    }
    
    /**
     * 获取用户的动态列表
     */
    @GetMapping("/user/{userId}")
    public AjaxResult userMoments(@PathVariable("userId") Long targetUserId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        
        // 获取目标用户的动态
        List<SysUserMoment> moments = momentMapper.selectUserMoments(targetUserId);
        
        // 检查当前用户是否对每个动态点赞
        for (SysUserMoment moment : moments)
        {
            if (likeMapper.selectMomentLike(userId, moment.getMomentId()) != null)
            {
                moment.setIsLiked(true);
            }
            else
            {
                moment.setIsLiked(false);
            }
        }
        
        return AjaxResult.success(moments);
    }

    /**
     * 发布动态
     */
    @PostMapping
    public AjaxResult publish(@RequestBody SysUserMoment moment)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        moment.setUserId(userId);
        moment.setLikeCount(0);
        moment.setCommentCount(0);
        
        // 转换图片列表为字符串存储
        if (moment.getImagesList() != null && !moment.getImagesList().isEmpty())
        {
            moment.setImages(String.join(",", moment.getImagesList()));
        }
        
        int rows = momentMapper.insertSysUserMoment(moment);
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
    
    /**
     * 点赞动态
     */
    @PostMapping("/like/{momentId}")
    public AjaxResult like(@PathVariable("momentId") Long momentId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        
        // 查询动态信息
        SysUserMoment moment = momentMapper.selectSysUserMomentById(momentId);
        if (moment == null)
        {
            return AjaxResult.error("动态不存在");
        }
        
        // 检查是否已点赞
        if (likeMapper.selectMomentLike(userId, momentId) != null)
        {
            return AjaxResult.error("已点赞");
        }
        
        // 创建点赞记录
        com.ruoyi.system.domain.SysUserLike like = new com.ruoyi.system.domain.SysUserLike();
        like.setUserId(userId);
        like.setMomentId(momentId);
        like.setTargetUserId(moment.getUserId());
        like.setType("1"); // 1表示动态点赞
        likeMapper.insertSysUserLike(like);
        
        // 增加点赞数
        momentMapper.increaseLikeCount(momentId);
        
        return AjaxResult.success();
    }
    
    /**
     * 取消点赞
     */
    @PostMapping("/unlike/{momentId}")
    public AjaxResult unlike(@PathVariable("momentId") Long momentId)
    {
        // 从当前登录用户获取用户ID
        Long userId = getUserId();
        
        // 检查是否已点赞
        if (likeMapper.selectMomentLike(userId, momentId) == null)
        {
            return AjaxResult.error("未点赞");
        }
        
        // 删除点赞记录
        likeMapper.deleteMomentLike(userId, momentId);
        
        // 减少点赞数
        momentMapper.decreaseLikeCount(momentId);
        
        return AjaxResult.success();
    }
    
    /**
     * 获取当前登录用户ID（实际项目中应从登录信息中获取）
     */
    public Long getUserId()
    {
        // TODO: 从登录信息中获取当前用户ID
        // 这里暂时返回1作为示例
        return 1L;
    }
} 