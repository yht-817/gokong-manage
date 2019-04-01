package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.CattlePeopleMapper;
import cn.gokong.www.gokongmain.domain.CattlePeople;
import cn.gokong.www.gokongmain.service.CattlePeopleService;
import cn.gokong.www.gokongmain.service.FriendInviteService;
import cn.gokong.www.gokongmain.service.UploadService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@Service
public class CattlePeopleServiceImpl extends ServiceImpl<CattlePeopleMapper, CattlePeople> implements CattlePeopleService {

    @Autowired
    UploadService uploadService;

    @Autowired
    FriendInviteService friendInviteService;

    /**
     * 查询牛人用户详情
     *
     * @param userno
     * @return
     */
    public CattlePeople findCattlePeopleDetail(String userno) {
        CattlePeople cattlePeople = this.baseMapper.findCattlePeopleDetail(userno);
        return cattlePeople;
    }

    /**
     * 修改牛人的部分信息
     */
    public ResponseData updateCattlePeople(CattlePeople cattlePeople) {
        ResponseData responseData = new ResponseData();
        Integer update = this.baseMapper.updateCattlePeople(cattlePeople.getUserNo(), cattlePeople.getCattlePeoplePhoto(), cattlePeople.getCattleWorth(), cattlePeople.getCattlePeopleAbstract());
        if (update > 0) {
            responseData.setCode(200);
            responseData.setMessage("修改个人信息成功");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("修改个人信息失败");
        return responseData;
    }

    /**
     * 添加牛人为好友
     *
     * @param userno   用户编码
     * @param friendno 牛人用户编码
     * @return
     */
    public ResponseData addCattleFriend(String userno, String friendno) {
        ResponseData responseData = new ResponseData();
        // 生成订单号
        String orderno = DataBaseTool.createNo("JHY");
        // 现查询当前两个用户编码是否是好友
        boolean data = friendInviteService.isFriends(userno, friendno);
        if (!data) {
            // 说明扣款成功然后自动加为好友
            boolean userfrind = friendInviteService.applyFriend(userno, friendno, orderno);
            if (userfrind) {
                responseData.setMessage(orderno);
                responseData.setCode(200);
                responseData.setData(orderno);
                return responseData;
            } else {
                responseData.setMessage("添加失败！");
                responseData.setCode(500);
                responseData.setData(orderno);
                return responseData;
            }
        } else {
            responseData.setMessage("您已经添加TA为好友了,等待对方同意！");
            responseData.setCode(500);
            responseData.setData(orderno);
            return responseData;
        }
    }

    /**
     * 添加牛人信息
     *
     * @param userno               牛人编码
     * @param cattlePeopleName     牛人姓名
     * @param cattlePeopleAbstract 牛人简介
     * @param cattlePeopleLabel    牛人标签
     * @param cattlePeopleAddress  牛人地址
     * @param cattlePeoplePhoto    牛人头像
     * @param cattleWorth          牛人价值
     * @return
     */
    public Object addcattlePeople(String userno, String cattlePeopleName, String cattlePeopleAbstract, String cattlePeopleLabel, String cattlePeopleAddress, String cattlePeoplePhoto, String cattleWorth) {
        ResponseData responseData = new ResponseData();
        if (ObjectUtil.isNotNull(userno) && ObjectUtil.isNotNull(cattlePeopleName)) {
            String cattlePeopleWork = cattlePeopleLabel.indexOf("|")!=-1?cattlePeopleLabel.substring(0, cattlePeopleLabel.indexOf("|")):cattlePeopleLabel;
            int data = this.baseMapper.insertCattlePeople(DataBaseTool.createId(), userno, cattlePeopleName, cattlePeopleWork, cattlePeopleAbstract, cattlePeopleLabel, cattlePeopleAddress, cattlePeoplePhoto, cattleWorth);
            if (data > 0) {
                responseData.setCode(200);
                responseData.setMessage("添加信息成功");
                return responseData;
            }
        }
        responseData.setCode(500);
        responseData.setMessage("关键信息不能为空");
        return responseData;
    }

    /**
     * 根据用户编码然后修改当前信息（查询用户得详细信息）
     *
     * @param userno 用户编码
     * @return
     */
    public CattlePeople querycattlePeopleInfo(String userno) {
        // 查询当前用户编码数据的信息
        CattlePeople cattlePeople = this.baseMapper.querycattlePeopleInfo(userno);
        return cattlePeople;
    }
}
