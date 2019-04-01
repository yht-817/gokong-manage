package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.LatLngTools;
import cn.gokong.www.gokongmain.dao.ChineseCircleMapper;
import cn.gokong.www.gokongmain.domain.ChineseCircle;
import cn.gokong.www.gokongmain.domain.UserInfo;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.chinese_circle.CircleDetails;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryCircleDetailsOutput;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.PageQueryCircleEvaluateOutput;
import cn.gokong.www.gokongmain.vo.city.HotCircle;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 华人圈 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class ChineseCircleServiceImpl extends ServiceImpl<ChineseCircleMapper, ChineseCircle> implements ChineseCircleService {

    @Autowired
    FansInfoService fansInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UploadService uploadService;

    @Autowired
    PrivilegeUserService privilegeUserService;

    @Autowired
    ChineseCircleEvaluateService chineseCircleEvaluateService;

    /**
     * 分页查询我发布的华人圈
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageQueryChineseCircleOutput> pageQueryMyChineseCircle(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryMyChineseCircle(userNo, pageNo, pageSize);
    }

    /**
     * 发布华人圈
     *
     * @param userNo       用户编码
     * @param instructions 说明
     * @param img          图片
     * @param cityName     城市名称
     * @param lat          纬度
     * @param lng          经度
     * @return
     */
    public String releaseCircle(String userNo, String instructions, MultipartFile img, String cityName, String lat, String lng) throws IOException {
        //判断当前用户是否是特权用户
        boolean hesPrivilege = privilegeUserService.hesPrivilege(userNo, "chinese_circle");
        if (hesPrivilege) {
            UserInfo randomUserInfo = userInfoService.findRandomUserInfo(SysCodeEnum.CODE_10020003.getMsg());
            if (ObjectUtil.isNotNull(randomUserInfo)) {
                userNo = randomUserInfo.getUserNo();
            }
        }
        String no = DataBaseTool.createNo("circle");
        // 图片进行上传
        String path = uploadService.ftpImg(img, "5");
        BufferedImage bufferedImage = ImageUtil.read(img.getInputStream());
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        path = path + "?w=" + width + "&h=" + height;
        if (path != null) {
            ChineseCircle chineseCircle = new ChineseCircle();
            chineseCircle.setId(DataBaseTool.createId());
            chineseCircle.setUserNo(userNo);
            chineseCircle.setInstructions(instructions);
            chineseCircle.setCircleNo(no);
            chineseCircle.setCircleImg(path);
            chineseCircle.setCreateTime(DataBaseTool.createDate());
            chineseCircle.setCommentsNum(0);
            chineseCircle.setLikeNum(0);
            chineseCircle.setShareNum(0);
            chineseCircle.setCityNo(cityName);
            chineseCircle.setLat(lat);
            chineseCircle.setLng(lng);
            boolean save = save(chineseCircle);
            if (save) {
                return no;
            }
            return null;
        }
        return null;
    }

    /**
     * 分页获取华人圈
     *
     * @param cityNo 城市编码
     * @param pageno 分页编码
     * @return
     */
    public List<HotCircle> chinesePeople(String cityNo, int pageno, String logno, String latno) {
        HotCircle hotCircle = null;
        List<HotCircle> data = new ArrayList<>();
        List<Map<String, Object>> hotdata = this.baseMapper.findHot(cityNo, pageno);
        for (int i = 0; i < hotdata.size(); i++) {
            hotCircle = new HotCircle();
            hotCircle.setCircleNo((String) hotdata.get(i).get("circle_no"));
            hotCircle.setCircleImg((String) hotdata.get(i).get("circle_img"));
            hotCircle.setLikeNum((Integer) hotdata.get(i).get("like_num"));
            hotCircle.setUserHead((String) hotdata.get(i).get("user_head"));
            hotCircle.setUserName((String) hotdata.get(i).get("nick_name"));
            String d = "0/m";
            if (ObjectUtil.isNotNull(logno) && ObjectUtil.isNotNull(latno)) {
                double kk = LatLngTools.getDistance(Double.valueOf(latno), Double.valueOf(logno), Double.parseDouble(String.valueOf(hotdata.get(i).get("lat"))), Double.parseDouble(String.valueOf(hotdata.get(i).get("lng"))));
                if (kk > 1000) {
                    kk = kk / 1000;
                    d = String.format("%.2f", kk) + "km";
                } else {
                    d = String.format("%.0f", kk) + "m";
                }
            }
            hotCircle.setDistance(d);
            data.add(hotCircle);
        }
        return data;
    }

    /**
     * 分页查询华人圈详情
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @param pageNo   开始位置
     * @param pageSize 页面长度
     * @return
     */
    @Override
    public PageQueryCircleDetailsOutput pageQueryCircleDetails(String userNo, String circleNo, Integer pageNo, Integer pageSize) {
        PageQueryCircleDetailsOutput pageQueryCircleDetailsOutput = new PageQueryCircleDetailsOutput();

        List<CircleDetails> circleDetails = baseMapper.pageQueryCircleDetails(userNo, circleNo, pageNo, pageSize);

        circleDetails.forEach(circleDetails1 -> {
            List<PageQueryCircleEvaluateOutput> pageQueryCircleEvaluateOutputs = chineseCircleEvaluateService.pageQueryCircleEvaluate(circleDetails1.getCircleNo(), userNo, 0, 2);
            circleDetails1.setEvaluates(pageQueryCircleEvaluateOutputs);
        });

        pageQueryCircleDetailsOutput.setCircleDetails(circleDetails);

        if (pageNo == 0) {
            ChineseCircle byCircleNo = findByCircleNo(circleNo);
            //查询用户信息
            UserInfo userInfo = userInfoService.findByUserNo(byCircleNo.getUserNo());
            pageQueryCircleDetailsOutput.setUserNo(userInfo.getUserNo());
            pageQueryCircleDetailsOutput.setUserHead(userInfo.getUserHead());
            pageQueryCircleDetailsOutput.setNickName(userInfo.getNickName());
            //查询是否关注
            boolean focus = fansInfoService.isFocus(byCircleNo.getUserNo(), userNo);
            if (byCircleNo.getUserNo().equals(userNo)) {
                focus = true;
            }
            pageQueryCircleDetailsOutput.setFocus(focus);
        }

        return pageQueryCircleDetailsOutput;
    }

    @Override
    public ChineseCircle findByCircleNo(String circleNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("circle_no", circleNo);
        ChineseCircle chineseCircle = getOne(wrapper);
        if (ObjectUtil.isNull(chineseCircle)) {
            throw new GrilException("未查询到华人圈信息");
        }
        return chineseCircle;
    }

    /**
     * 举报华人圈信息
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @return
     */
    public ResponseData reportCircleDetails(String userNo, String circleNo) {
        ResponseData responseData = new ResponseData();
        Date date = DataBaseTool.createDate();
        int insertv = this.baseMapper.reportCircleDetails(DataBaseTool.createId(), userNo, circleNo, date);
        if (insertv > 0) {
            // 修改 华人圈的状态然后不显示
            int updateCircleDetailsStates = baseMapper.updateCircleDetailsStates(circleNo);
            StaticLog.info("修改的状态：" + updateCircleDetailsStates);
            responseData.setCode(200);
            responseData.setMessage("举报成功，我们会做相应处理！感谢");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("举报成功，我们会做相应处理！感谢");
        return responseData;
    }

    /**
     * 删除华人圈
     *
     * @param circleNo 华人圈编码
     * @return
     */
    @Override
    public boolean delCircle(String circleNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("circle_no", circleNo);
        ChineseCircle chineseCircle = getOne(wrapper);
        return removeById(chineseCircle.getId());
    }
}
