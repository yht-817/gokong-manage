package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.vo.user.PageSearchUserOutput;
import cn.gokong.www.gokongmain.vo.user.UserInfoOutput;
import cn.gokong.www.gokongmain.vo.user.UserNoLoginOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户基础信息表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-08
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据用户编码查询用户登录信息
     *
     * @param userNo 用户编码
     * @return
     */
    @Select("select * from user_info where user_no = #{userNo}")
    UserInfoOutput findByUserNoInfo(@Param("userNo") String userNo);

    /**
     * 根据电话号码查询用户登录信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    @Select("select *,if((select count(1) from cattle_people cp where cp.user_no = ui.user_no),'true','false') as cattlePeople from user_info ui where ui.phone_no = #{phoneNo}")
    UserInfoOutput findByPhoneNoInfo(@Param("phoneNo") String phoneNo);

    /**
     * 根据邮箱和密码查询用户登录信息
     *
     * @param mailboxNo 邮箱
     * @param passWord  密码
     * @return
     */
    @Select("select * from user_info where mailbox_no = #{mailboxNo} and password = #{password}")
    UserInfoOutput findByMailBoxNoAndPassword(@Param("mailboxNo") String mailboxNo, @Param("passWord") String passWord);

    /**
     * 根据电话号码和密码查询用户登录信息
     *
     * @param phoneNo  电话号码
     * @param passWord 密码
     * @return
     */
    @Select("select * from user_info where phone_no = #{phoneNo} and password = #{passWord}")
    UserInfoOutput findByPhoneNoAndPassword(@Param("phoneNo") String phoneNo, @Param("passWord") String passWord);

    /**
     * 根据用户名和密码查询用户登录信息
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @Select("select * from user_info where user_name = #{userName} and password = #{password}")
    UserInfoOutput findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 采集用户经纬度信息
     *
     * @param userNo 用户编码
     * @param date   日期
     * @param log    经度
     * @param lat    纬度
     * @return
     */
    int addUserStates(@Param("userNo") String userNo, @Param("date") Date date, @Param("log") String log, @Param("lat") String lat);

    /**
     * 根据用户电话查询用户信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    @Select("select * from user_info where phone_no = #{phoneNo}")
    UserInfo findByPhoneNo(@Param("phoneNo") String phoneNo);

    /**
     * 分页检索用户列表
     *
     * @param userNo   用户编码
     * @param keyword  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageSearchUserOutput> pageSearchUser(@Param("userNo") String userNo, @Param("keyword") String keyword, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("select user_no,user_head,nick_name,sex_no,if((select count(1) from cattle_people where user_no = #{userNo}),'true','false') as cattlePeople from user_info where user_no = #{userNo}")
    UserNoLoginOutput userNoLogin(String userNo);

    /**
     * 随机查询用户
     *
     * @param userType 用户类型
     * @return
     */
    @Select("select * from user_info where user_type = #{userType} ORDER BY RAND() limit 1")
    UserInfo findRandomUserInfo(String userType);

    @Select("SELECT account_amount FROM user_account where user_no = #{userNo}")
    BigDecimal queryAccountAmount(@Param("userNo") String userNo);

    /**
     * 更改当前用户编码
     *
     * @param userNo
     * @param userPhoneNo
     * @return
     */
    @Update("UPDATE user_auths set user_no = #{userPhoneNo} WHERE user_no = #{userNo}")
    int updateInfo(@Param("userNo") String userNo, @Param("userPhoneNo") String userPhoneNo);

    /**
     * 补全手机号码
     *
     * @param phoneNo
     * @param phoneCode
     * @param userNo
     * @return
     */
    @Update("UPDATE user_info set phone_no = #{phoneNo},phone_code = #{phoneCode} WHERE user_no = #{userNo}")
    int updateInfoPhone(@Param("phoneNo") String phoneNo, @Param("phoneCode") String phoneCode, @Param("userNo") String userNo);

    @Delete("DELETE FROM user_info WHERE user_no = #{userNo}")
    int deleteInfo(@Param("userNo") String userNo);
}
