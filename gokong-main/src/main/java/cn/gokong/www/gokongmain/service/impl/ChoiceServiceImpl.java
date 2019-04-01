package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.choice.CodeImgTools;
import cn.gokong.www.gokongmain.dao.ChoiceMapper;
import cn.gokong.www.gokongmain.service.ChoiceService;
import cn.gokong.www.gokongmain.service.UserAccountService;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfo;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfoListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    ChoiceMapper choiceMapper;

    @Autowired
    UserAccountService userAccountService;

    private final Logger logger = LoggerFactory.getLogger(ChoiceServiceImpl.class);

    // 获取顶部和中部的列表
    public Map<String, Object> findTopMiddleInfo(String city) {
        List<ChoiceInfoListVo> top = new ArrayList<>();
        List<ChoiceInfoListVo> milddle = new ArrayList<>();
        ChoiceInfoListVo vo = null;
        // 获取顶部数据
        List<Map<String, Object>> listTop = choiceMapper.findTopInfo(city);
        for (int i = 0; i < listTop.size(); i++) {
            vo = new ChoiceInfoListVo();
            // ID
            vo.setId((String) listTop.get(i).get("id"));
            // 顶部的图片
            vo.setPhoto((String) listTop.get(i).get("home_top_photo"));
            // 精选类型
            vo.setChoiceType((String) listTop.get(i).get("choice_type"));
            // 资源代码
            vo.setResourceNo((String) listTop.get(i).get("resource_no"));
            // 精选名称
            vo.setResourceName((String) listTop.get(i).get("resource_name"));
            top.add(vo);
        }
        // 获取中部的列表
        List<Map<String, String>> listMilddle = choiceMapper.findMilddleInfo(city);
        for (int i = 0; i < listMilddle.size(); i++) {
            vo = new ChoiceInfoListVo();
            // ID
            vo.setId((String) listMilddle.get(i).get("id"));
            // 顶部的图片
            vo.setPhoto((String) listMilddle.get(i).get("home_middle_photo"));
            // 精选类型
            vo.setChoiceType((String) listMilddle.get(i).get("choice_type"));
            // 资源代码
            vo.setResourceNo((String) listMilddle.get(i).get("resource_no"));
            // 精选名称
            vo.setResourceName((String) listMilddle.get(i).get("resource_name"));
            milddle.add(vo);
        }
        Map<String, Object> msgdata = new HashMap<>();
        msgdata.put("top", top);
        msgdata.put("milddle", milddle);
        return msgdata;
    }

    // 获取底部列表
    public Object findBottom(Map<String, String> data) {
        String city = data.get("city");
        String userNo = data.get("userNo");
        String pageNo = data.get("pageNo");
        String pageSize = data.get("pageSize");
        String keyWord = data.get("keyWord");
        List<ChoiceInfoListVo> bottom = choiceMapper.findBottom(city, userNo, pageNo, pageSize, keyWord);
        if (bottom.size() > 0) {
            return bottom;
        } else {
            return null;
        }
    }

    /**
     * 查看精选的详情
     *
     * @param data
     * @return
     */
    public ResponseData findDetails(Map<String, String> data) {
        ResponseData responseData = new ResponseData();
        // 获取资源编码
        String collection = data.get("collectionNo");
        String resourceno = data.get("resourceNo");
        String userno = data.get("userNo");
        if (resourceno != null && resourceno != "" && userno != null && userno != "") {
            ChoiceInfo choiceInfo = choiceMapper.findDetails(resourceno);
            if (choiceInfo != null) {
                // 说明是优惠券进行分享
                if (choiceInfo.getChoiceType().equals("10350002")) {
                    /** 把分享的图片的返回**/
                    // 生成购买时间
                    Date date = DataBaseTool.createDate();
                    String url = "https://www.sunwukong.net/h5/components/global-vouchers.html?resourceNo=" + resourceno + "&userNo=" + userno + "&shareRebateAmount=" + choiceInfo.getShareRebateAmount();
                    String imgurl = CodeImgTools.backBase64(url, choiceInfo.getSharePhoto());
                    choiceInfo.setImgUrl(imgurl);
                }
                if (collection.equals("0")) {
                    choiceInfo.setCollecTion(false);
                } else {
                    choiceInfo.setCollecTion(true);
                }
                responseData.setCode(200);
                responseData.setData(choiceInfo);
                responseData.setMessage("请求成功");
                return responseData;
            }
        }
        responseData.setCode(500);
        responseData.setMessage("哎呀喂！参数出现了错误");
        return responseData;
    }
}
