package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UserHomePageMapper;
import cn.gokong.www.gokongmain.domain.UserHomePage;
import cn.gokong.www.gokongmain.domain.UserHomePageVisit;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.user.QueryUserHomeOutput;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户主页 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class UserHomePageServiceImpl extends ServiceImpl<UserHomePageMapper, UserHomePage> implements UserHomePageService {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserHomePageVisitService userHomePageVisitService;

    @Autowired
    FriendInviteService friendInviteService;

    @Autowired
    FansInfoService fansInfoService;

    @Autowired
    UploadService uploadService;

    /**
     * 查询个人主页信息
     *
     * @param userNo    被访问者编码
     * @param visitorNo 访问者编码
     * @return
     */
    @Override
    public QueryUserHomeOutput queryUserHome(String userNo, String visitorNo) {
        //判断是否是好友
        boolean friend = friendInviteService.isFriend(userNo, visitorNo);

        if (!userNo.equals(visitorNo)) {
            UserHomePageVisit userHomePageVisit = new UserHomePageVisit();
            userHomePageVisit.setId(DataBaseTool.createId());
            userHomePageVisit.setUserNo(userNo);
            userHomePageVisit.setVisitNo(visitorNo);
            userHomePageVisit.setVisitDate(DataBaseTool.createDate());
            userHomePageVisitService.save(userHomePageVisit);
        }else {
            //如果访问者和被访问者编码相同就不能加好友
            friend = true;
        }

        //查询个人主页信息
        QueryUserHomeOutput queryUserHomeOutput = baseMapper.findByUserNo(userNo);

        //查询用户资料
        UserInfo userInfo = userInfoService.findByUserNo(userNo);

        //查询访问量
        Map<String, Long> queryUserHomeVisit = userHomePageVisitService.queryUserHomeVisit(userNo, new Date());




        //查询粉丝数量
        int fansNo = fansInfoService.countByUserNo(userNo);

        queryUserHomeOutput.setFansNo(fansNo);
        queryUserHomeOutput.setNickName(userInfo.getNickName());
        queryUserHomeOutput.setUserHead(userInfo.getUserHead());
        queryUserHomeOutput.setUserSign(userInfo.getUserSign());
        queryUserHomeOutput.setUserGrade(userInfo.getUserGrade());
        queryUserHomeOutput.setTotal(queryUserHomeVisit.get("total").intValue());
        queryUserHomeOutput.setToday(queryUserHomeVisit.get("today").intValue());
        queryUserHomeOutput.setFriend(friend);
        return queryUserHomeOutput;
    }

    /**
     * 添加主页图片
     *
     * @param userNo    用户编码
     * @param imgNo     图片下标
     * @param homePhoto 图片地址
     * @return
     */
    public String addUserHomeImg(String userNo, int imgNo, MultipartFile homePhoto) {
        String homephoto = uploadService.ftpImg(homePhoto, "1");
        if (homephoto != null) {
            int updateByUserNoFieldVal = baseMapper.updateByUserNoFieldVal(userNo, "home_photo" + imgNo, homephoto);
            if (updateByUserNoFieldVal > 0) {
                return homephoto;
            }
        }
        return null;
    }

    /**
     * 删除个人主页图片
     *
     * @param userNo 用户编码
     * @param imgNo  图片下标
     * @return
     */
    @Override
    public boolean delUserHomeImg(String userNo, String imgNo) {
        int updateByUserNoFieldVal = baseMapper.updateByUserNoFieldVal(userNo, "home_photo" + imgNo, "");
        if (updateByUserNoFieldVal <= 0) {
            throw new GrilException("删除用户主页图片失败");
        }
        return true;
    }

    /**
     * 设置主页封面图片
     *
     * @param userNo 用户编码
     * @param imgNo  图片下标
     * @return
     */
    @Override
    public boolean setUserHomeCover(String userNo, String imgNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        UserHomePage userHomePage = getOne(wrapper);
        userHomePage.setCoverNo(imgNo);

        return saveOrUpdate(userHomePage);
    }

    /**
     * 查询用户图片的上传情况
     *
     * @param userNo 用户编码
     * @return
     */
    public Map<String, String> findImgIndex(String userNo) {
        return this.baseMapper.findImgIndex(userNo);
    }
}
