package com.yyc.dao;

import com.yyc.dto.UserDTO;
import com.yyc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;
/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 用户信息Mapper
 ***************************************
 */
public interface IUserInfoMapper {

    /**
     * 通过username查找用户角色信息
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findByUsername(@Param("username") String username);

    /**
     * 通过username查找用户名字
     * @param username 用户名
     * @return 用户姓名
     */
    String findNameByUsername(@Param("username") String username);

    /**
     * 查询所有的用户信息列表
     * @return UserDTO列表
     */
    List<UserDTO> selectAllUser();

    /**
     * 添加用户
     * @param user 需要添加的用户信息
     * @return SQL执行受影响的条数
     */
    Integer insertUser(UserInfo user);


    /**
     * 添加用户角色
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return SQL执行受影响的条数
     */
    Integer insertUserAndRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    /**
     * 删除角色
     * @param userId 用户ID
     * @return SQL执行受影响的条数
     */
    Integer deleteRoleByUserId(@Param("userId") Integer userId);


    /**
     * 删除用户
     * @param userId 用户ID
     * @return SQL执行受影响的条数
     */
    Integer deleteUserByUserId(@Param("userId") Integer userId);


    /**
     * 根据id查询用户
     * @param userId 用户ID
     * @return UserDTO对象
     */
    UserDTO selectUserByUserId(@Param("userId") Integer userId);


    /**
     * 更新用户
     * @param user 需要更新的用户信息
     * @return SQL执行受影响的条数
     */
    Integer updateUser(UserInfo user);


    /**
     * 更新角色
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return SQL执行受影响的条数
     */
    Integer updateUserAndRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 统计店长总数
     * @return 角色为店长的用户数
     */
    Integer selectCountShopKeeper();

    /**
     * 统计分销员总数
     * @return 角色为分销员的用户数
     */
    Integer selectCountDistributor();

    /**
     * 统计普通店员总数
     * @return 角色为普通店员的用户数
     */
    Integer selectCountShopBoy();
}
