package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserLike;

import java.util.List;

/**
 * 用户点赞关系Mapper接口
 */
public interface SysUserLikeMapper
{
    /**
     * 查询用户点赞关系
     * 
     * @param likeId 用户点赞关系ID
     * @return 用户点赞关系
     */
    public SysUserLike selectSysUserLikeById(Long likeId);

    /**
     * 查询用户点赞关系列表
     * 
     * @param sysUserLike 用户点赞关系
     * @return 用户点赞关系集合
     */
    public List<SysUserLike> selectSysUserLikeList(SysUserLike sysUserLike);

    /**
     * 查询用户对动态的点赞状态
     * 
     * @param userId 用户ID
     * @param momentId 动态ID
     * @return 点赞关系
     */
    public SysUserLike selectMomentLike(Long userId, Long momentId);

    /**
     * 查询用户对用户的点赞状态
     * 
     * @param userId 用户ID
     * @param targetUserId 目标用户ID
     * @return 点赞关系
     */
    public SysUserLike selectUserLike(Long userId, Long targetUserId);

    /**
     * 新增用户点赞关系
     * 
     * @param sysUserLike 用户点赞关系
     * @return 结果
     */
    public int insertSysUserLike(SysUserLike sysUserLike);

    /**
     * 修改用户点赞关系
     * 
     * @param sysUserLike 用户点赞关系
     * @return 结果
     */
    public int updateSysUserLike(SysUserLike sysUserLike);

    /**
     * 删除用户点赞关系
     * 
     * @param likeId 用户点赞关系ID
     * @return 结果
     */
    public int deleteSysUserLikeById(Long likeId);

    /**
     * 取消用户对动态的点赞
     * 
     * @param userId 用户ID
     * @param momentId 动态ID
     * @return 结果
     */
    public int deleteMomentLike(Long userId, Long momentId);

    /**
     * 取消用户对用户的点赞
     * 
     * @param userId 用户ID
     * @param targetUserId 目标用户ID
     * @return 结果
     */
    public int deleteUserLike(Long userId, Long targetUserId);

    /**
     * 批量删除用户点赞关系
     * 
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserLikeByIds(Long[] likeIds);
} 