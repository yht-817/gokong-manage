package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.UserHomePage;
import cn.gokong.www.gokongmain.vo.user.QueryUserHomeOutput;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 用户主页 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface UserHomePageService extends IService<UserHomePage> {

    /**
     * 查询个人主页信息
     *
     * @param userNo    被访问者编码
     * @param visitorNo 访问者编码
     * @return
     */
    QueryUserHomeOutput queryUserHome(String userNo, String visitorNo);

    /**
     * 添加主页图片
     *
     * @param userNo    用户编码
     * @param imgNo     图片下标
     * @param homePhoto 图片地址
     * @return
     */
    String addUserHomeImg(String userNo, int imgNo, MultipartFile homePhoto);

    /**
     * 删除个人主页图片
     *
     * @param userNo 用户编码
     * @param imgNo  图片下标
     * @return
     */
    boolean delUserHomeImg(String userNo, String imgNo);

    /**
     * 设置主页封面图片
     *
     * @param userNo 用户编码
     * @param imgNo  图片下标
     * @return
     */
    boolean setUserHomeCover(String userNo, String imgNo);

    /**
     * 查询当前用户的主页图片的下标
     *
     * @param userNo 用户编码
     * @return
     */
    Map<String, String> findImgIndex(String userNo);
}
