package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.vo.user.PageSearchUserOutput;
import cn.gokong.www.gokongmain.vo.user.UserInfoOutput;
import cn.gokong.www.gokongmain.vo.user.UserNoLoginOutput;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户基础信息表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-08
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 根据邮箱和密码认证用户
     *
     * @param mailboxNo 邮箱
     * @param passWord  密码
     * @return
     */
    UserInfoOutput findByMailBoxNoAndPassword(String mailboxNo, String passWord);

    /**
     * 根据电话号码和密码认证用户
     *
     * @param phoneNo  电话号码
     * @param passWord 密码
     * @return
     */
    UserInfoOutput findByPhoneNoAndPassword(String phoneNo, String passWord);

    /**
     * 根据用户名和密码认证用户
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    UserInfoOutput findByUserNameAndPassword(String userName, String passWord);

    /**
     * 发送验证码
     *
     * @param phoneNo   电话号码
     * @param phoneCode 手机代码
     * @return
     */
    boolean sendSecurityCode(String phoneNo, String phoneCode) throws ClientException;

    /**
     * 校验验证码
     *
     * @param phoneNo      电话号码
     * @param phoneCode    电话代码
     * @param securityCode 验证码
     * @return
     */
    boolean checkSecurityCode(String phoneNo, String phoneCode, String securityCode);

    /**
     * 第三方登录
     *
     * @param thirdKey  第三方KEY
     * @param thirdType 第三方类型
     * @param thirdHead 头像
     * @param thirdName 名称
     * @param thirdSex  性别
     * @return
     */
    UserInfoOutput thirdLogin(String thirdKey, String thirdType, String thirdHead, String thirdName, String thirdSex);

    /**
     * 第三方登录绑定手机号码
     *
     * @param thirdKey     第三方KEY
     * @param phoneNo      电话号码
     * @param phoneCode    区号
     * @param securityCode 验证码
     * @return
     */
    UserInfoOutput thirdLoginBindPhoneNo(String thirdKey, String phoneNo, String phoneCode, String securityCode);

    /**
     * 校验电话号码是否已被绑定
     *
     * @param phoneNo 电话号码
     * @return true:已绑定 false：未绑定
     */
    boolean checkPhoneNoIsBind(String phoneNo);

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息
     * @return
     */
    boolean saveUserInfo(UserInfo userInfo);

    /**
     * 根据用户编码查询用户登录信息
     *
     * @param userNo 用户编码
     * @return
     */
    UserInfoOutput findByUserNoInfo(String userNo);

    /**
     * 根据电话号码查询用户登录信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    UserInfoOutput findByPhoneNoInfo(String phoneNo);

    /**
     * 验证码登录
     *
     * @param phoneNo      电话号码
     * @param phoneCode    区号
     * @param securityCode 验证码
     * @param userType     用户类型
     * @return
     */
    UserInfoOutput securityCodeLogin(String phoneNo, String phoneCode, String securityCode, String userType);

    /**
     * 修改用户信息
     *
     * @param userNo    用户编码
     * @param nickName  用户昵称
     * @param sexNo     用户性别
     * @param userSign  用户签名
     * @param userLocal 常住地
     * @return
     */
    boolean updateUserInfo(String userNo, String nickName, String sexNo, String userSign, String userLocal);

    /**
     * 找回密码-重置密码
     *
     * @param phoneNo      电话号码
     * @param phoneCode    国际区号
     * @param password     新密码
     * @param securityCode 验证码
     * @return
     */
    boolean resetPasswd(String phoneNo, String phoneCode, String password, String securityCode);

    /**
     * 根据用户编码查询用户信息
     *
     * @param userNo 用户编码
     * @return
     */
    UserInfo findByUserNo(String userNo);

    /**
     * 根据电话号码查询用户信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    UserInfo findByPhoneNo(String phoneNo);

    /**
     * 修改用户头像
     *
     * @param userNo   用户编码
     * @param userHead 用户头像地址
     * @return
     */
    String updateUserHead(String userNo, MultipartFile userHead);

    /**
     * 根据用户编码修改用户最后登录日期
     *
     * @param userNo 用户编码
     * @return
     */
    boolean updateLastLoginDate(String userNo);

    /**
     * 赠送蟠桃
     *
     * @param userNo       用户编码
     * @param sendType     赠送类型
     * @param changeMode   资金变动方式
     * @param isWithdrawal 是否可提现
     * @return
     */
    boolean sendPeento(String userNo, SysCodeEnum sendType, SysCodeEnum changeMode, boolean isWithdrawal);

    /**
     * 绑定手机号码
     *
     * @param userNo       用户编码
     * @param phoneNo      电话号码
     * @param phoneCode    手机代码
     * @param securityCode 验证码
     * @return
     */
    boolean bindPhoneNo(String userNo, String phoneNo, String phoneCode, String securityCode);

    /**
     * 采集用户的信息
     *
     * @param userdata
     */
    ResponseData addUserState(Map<String, String> userdata);

    /**
     * 接受邀请领取蟠桃
     *
     * @param userNo       邀请者编码
     * @param phoneNo      电话号码
     * @param phoneCode    电话代码
     * @param securityCode 验证码
     * @return
     */
    boolean acceptAnInvitation(String userNo, String phoneNo, String phoneCode, String securityCode);

    /**
     * 分页检索用户列表
     *
     * @param userNo   用户编码
     * @param keyword  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageSearchUserOutput> pageSearchUser(String userNo, String keyword, Integer pageNo, Integer pageSize);

    /**
     * 根据用户编码查询部分登录信息
     *
     * @param userNo 用户编码
     * @return
     */
    UserNoLoginOutput userNoLogin(String userNo);

    /**
     * 随机查询用户
     *
     * @param userType 用户类型
     * @return
     */
    UserInfo findRandomUserInfo(String userType);

    /**
     * 小程序登录
     *
     * @param code
     * @param rawData
     * @param encrypteData
     * @param iv
     * @param signature
     * @return
     */
    UserInfoOutput appletLogin(String code, String rawData, String encrypteData, String iv, String signature);

    /**
     * 替换用户编码
     *
     * @param userNo      第三方用户编码
     * @param userPhoneNo 电话号码得登陆
     * @return
     */
    boolean updateInfo(String userNo, String userPhoneNo);

    /**
     * 补全电话号码
     *
     * @param phoneNo   手机号码
     * @param phoneCode 国家代码
     * @param userNo    第三方用户编码
     * @return
     */
    boolean updateInfoPhone(String phoneNo, String phoneCode, String userNo);
}
