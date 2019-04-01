package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.JSONUtil;
import cn.gokong.www.gokongmain.dao.UiControlMapper;
import cn.gokong.www.gokongmain.domain.MyUi;
import cn.gokong.www.gokongmain.domain.UiControl;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.city.HotCircle;
import cn.gokong.www.gokongmain.vo.group.PageSearchGroupOutput;
import cn.gokong.www.gokongmain.vo.local_activity.PageQueryListOutput;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@Service
public class UiControlServiceImpl extends ServiceImpl<UiControlMapper, UiControl> implements UiControlService {

    // 链接 FriendGroupContrroller 的服务层进行热门群查询
    @Autowired
    FriendGroupService friendGroupService;
    @Autowired
    FriendInviteService friendInviteService;
    @Autowired
    ChineseCircleService chineseCircleService;
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    LocalActivityService localActivityService;

    private final Logger logger = LoggerFactory.getLogger(ChoiceServiceImpl.class);

    /**
     * 获取首页数据
     *
     * @param userNo
     * @param cityNo
     * @param pageNo
     * @return
     */
    public Map<String, Object> findHomePage(String userNo, String cityNo, String pageNo, String logno, String latno) {
        Map<String, Object> resultsmap = new HashMap<>();
        Map<String, String> mapdata = null;
        List<Map<String, String>> navigation = new ArrayList<>();
        List<Map<String, String>> cityCattlePeople = new ArrayList<>();
        List<PageSearchGroupOutput> pageSearchGroupOutputs = null;
        List<HotCircle> chinesePeople = new ArrayList<>();
        int pageno = (Integer.valueOf(pageNo) - 1) * 10;
        if (pageno > 1) {
            // 大于零的时候值进行华人圈的分页查询
            chinesePeople = chineseCircleService.chinesePeople(cityNo, pageno, logno, latno);
            resultsmap.put("key", 1);
            resultsmap.put("code", 200);
            resultsmap.put("data", chinesePeople);
            resultsmap.put("message", "操作成功");
            return resultsmap;
        } else {
            List<Map<String, String>> homepage = this.baseMapper.findHomePage(cityNo);
            for (int i = 0; i < homepage.size(); i++) {
                if (homepage.get(i).get("ui_type").equals("10001")) {
                    mapdata = new HashMap<>();
                    mapdata.put("ui_name", homepage.get(i).get("ui_name"));
                    mapdata.put("icon_path", homepage.get(i).get("icon_path"));
                    mapdata.put("jump_path", homepage.get(i).get("jump_path"));
                    navigation.add(mapdata);
                } else if (homepage.get(i).get("ui_type").equals("10002")) {
                    // 同城牛人
                    cityCattlePeople = this.baseMapper.findCityCattlePeople(cityNo);
                    cityCattlePeople = JSONUtil.upsetList(cityCattlePeople);
                } else {
                    pageSearchGroupOutputs = friendGroupService.pageSearchGroup(userNo, null, cityNo, 0, 4);
                }
            }
            // 华人圈
            chinesePeople = chineseCircleService.chinesePeople(cityNo, pageno, logno, latno);
            resultsmap.put("data", chinesePeople);
            resultsmap.put("hotGroup", pageSearchGroupOutputs);
            resultsmap.put("navigation", navigation);
            resultsmap.put("cityCattlePeople", cityCattlePeople);
            resultsmap.put("key", 0);
            resultsmap.put("code", 200);
            resultsmap.put("message", "操作成功");
            return resultsmap;
        }
    }

    /**
     * 分页查询更多的牛人
     *
     * @param cityNo
     * @param pageNo
     * @param userName
     * @return
     */
    public ResponseData findCattlePeople(String cityNo, String pageNo, String userName) {
        ResponseData data = new ResponseData();
        int pageno = (Integer.valueOf(pageNo) - 1) * 10;
        List<Map<String, String>> cityCattlePeople = this.baseMapper.findCityCattlePeoples(cityNo, pageno, userName);
        data.setData(cityCattlePeople);
        data.setCode(200);
        data.setMessage("成功");
        return data;
    }

    /**
     * 查看牛人的详情
     */
    public ResponseData findCattlePeopleDetails(RequestData<Map<String, String>> requestData) {
        ResponseData datamsg = new ResponseData();
        String userNo = requestData.getData().get("userNo");
        String cattelUserNo = requestData.getData().get("cattelUserNo");
        Map<String, Object> data = this.baseMapper.findCattlePeopleDetails(cattelUserNo);
        // 查询是否是好友
        boolean friends = friendInviteService.isFriends(userNo, cattelUserNo);
        if (friends) {
            // 如果是好友进行判断是否是同意了
            boolean friend = friendInviteService.isFriendInfo(userNo, cattelUserNo, SysCodeEnum.CODE_10150001.getCode());
            if (friend) {
                data.put("agree", 0);// 表示还在申请中
            } else {
                // 查询是否是被拒绝了
                boolean friendI = friendInviteService.isFriendInfo(userNo, cattelUserNo, SysCodeEnum.CODE_10150003.getCode());
                if (friendI) {
                    data.put("agree", 1); // 表示已经拒绝了
                } else {
                    data.put("agree", 2); // 表示已经通过了
                }
            }
        }
        data.put("friend", friends); // 判断friend是true 是进行agree得判断
        datamsg.setMessage("成功");
        datamsg.setCode(200);
        datamsg.setData(data);
        return datamsg;
    }

    /**
     * 我的页面按钮入口
     */
    public Object myUI() {
        ResponseData datamsg = new ResponseData();
        // 查询My的数据
        List<MyUi> data = baseMapper.queryMyUi();
        StaticLog.info(data.toString());
        datamsg.setCode(200);
        datamsg.setMessage("参数返回成功");
        datamsg.setData(data);
        return datamsg;
    }

    /**
     * 获取首页数据
     *
     * @param userNo
     * @param cityNo
     * @param pageNo
     * @return
     */
    public Map<String, Object> findHomePageV3(String userNo, String cityNo, String pageNo, String logno, String latno) {
        Map<String, Object> resultsmap = new HashMap<>();
        Map<String, String> mapdata = null;
        List<Map<String, String>> navigation = new ArrayList<>();
        List<Map<String, String>> cityCattlePeople = new ArrayList<>();
        List<PageSearchGroupOutput> pageSearchGroupOutputs = null;
        List<HotCircle> chinesePeople = new ArrayList<>();
        int pageno = (Integer.valueOf(pageNo) - 1) * 10;
        if (pageno > 1) {
            // 大于零的时候值进行华人圈的分页查询
            ResponseData<List<PageQueryListOutput>> data = (ResponseData<List<PageQueryListOutput>>) localActivityService.pageQueryList(cityNo, null, pageno, 10);
            resultsmap.put("key", 1);
            resultsmap.put("code", 200);
            resultsmap.put("data", data.getData());
            resultsmap.put("message", "操作成功");
            return resultsmap;
        } else {
            List<Map<String, String>> homepage = this.baseMapper.findHomePage(cityNo);
            for (int i = 0; i < homepage.size(); i++) {
                if (homepage.get(i).get("ui_type").equals("10001")) {
                    mapdata = new HashMap<>();
                    mapdata.put("ui_name", homepage.get(i).get("ui_name"));
                    mapdata.put("icon_path", homepage.get(i).get("icon_path"));
                    mapdata.put("jump_path", homepage.get(i).get("jump_path"));
                    navigation.add(mapdata);
                } else if (homepage.get(i).get("ui_type").equals("10002")) {
                    // 同城牛人
                    cityCattlePeople = this.baseMapper.findCityCattlePeople(cityNo);
                    cityCattlePeople = JSONUtil.upsetList(cityCattlePeople);
                } else {
                    //pageSearchGroupOutputs = friendGroupService.pageSearchGroup(userNo, null, cityNo, 0, 4);
                }
            }
            // 同城活动
            ResponseData<List<PageQueryListOutput>> data = (ResponseData<List<PageQueryListOutput>>) localActivityService.pageQueryList(cityNo, null, pageno, 10);
            resultsmap.put("data", data.getData());
            resultsmap.put("hotGroup", null);
            resultsmap.put("navigation", navigation);
            resultsmap.put("cityCattlePeople", cityCattlePeople);
            resultsmap.put("key", 0);
            resultsmap.put("code", 200);
            resultsmap.put("message", "操作成功");
            return resultsmap;
        }
    }
}
