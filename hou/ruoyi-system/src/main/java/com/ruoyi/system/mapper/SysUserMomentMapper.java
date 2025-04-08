package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserMoment;

import java.util.List;

/**
 * 用户动态Mapper接口
 */
public interface SysUserMomentMapper
{
    /**
     * 查询用户动态
     * 
     * @param momentId 用户动态ID
     * @return 用户动态
     */
    public SysUserMoment selectSysUserMomentById(Long momentId);

    /**
     * 查询用户动态列表
     * 
     * @param sysUserMoment 用户动态
     * @return 用户动态集合
     */
    public List<SysUserMoment> selectSysUserMomentList(SysUserMoment sysUserMoment);

    /**
     * 查询用户关注的人和自己的动态
     * 
     * @param userId 用户ID
     * @return 动态列表
     */
    public List<SysUserMoment> selectFollowingMoments(Long userId);

    /**
     * 查询用户自己的动态
     * 
     * @param userId 用户ID
     * @return 动态列表
     */
    public List<SysUserMoment> selectUserMoments(Long userId);

    /**
     * 增加点赞数
     * 
     * @param momentId 动态ID
     * @return 结果
     */
    public int increaseLikeCount(Long momentId);

    /**
     * 减少点赞数
     * 
     * @param momentId 动态ID
     * @return 结果
     */
    public int decreaseLikeCount(Long momentId);

    /**
     * 增加评论数
     * 
     * @param momentId 动态ID
     * @return 结果
     */
    public int increaseCommentCount(Long momentId);

    /**
     * 新增用户动态
     * 
     * @param sysUserMoment 用户动态
     * @return 结果
     */
    public int insertSysUserMoment(SysUserMoment sysUserMoment);

    /**
     * 修改用户动态
     * 
     * @param sysUserMoment 用户动态
     * @return 结果
     */
    public int updateSysUserMoment(SysUserMoment sysUserMoment);

    /**
     * 删除用户动态
     * 
     * @param momentId 用户动态ID
     * @return 结果
     */
    public int deleteSysUserMomentById(Long momentId);

    /**
     * 批量删除用户动态
     * 
     * @param momentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserMomentByIds(Long[] momentIds);
} 