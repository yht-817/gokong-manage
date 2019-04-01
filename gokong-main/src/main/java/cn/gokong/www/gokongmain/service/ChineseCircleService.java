package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.ChineseCircle;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryCircleDetailsOutput;
import cn.gokong.www.gokongmain.vo.city.HotCircle;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 华人圈 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface ChineseCircleService extends IService<ChineseCircle> {

    /**
     * 分页查询我发布的华人圈
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageQueryChineseCircleOutput> pageQueryMyChineseCircle(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 发布华人圈
     *
     * @param userNo       用户编码
     * @param instructions 说明
     * @param img          图片
     * @param cityName     城市编码
     * @param lat          纬度
     * @param lng          经度
     * @return
     */
    String releaseCircle(String userNo, String instructions, MultipartFile img, String cityName, String lat, String lng) throws IOException;

    List<HotCircle> chinesePeople(String cityNo, int pageno, String logno, String latno);

    /**
     * 分页查询华人圈详情
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @param pageNo   开始位置
     * @param pageSize 页面长度
     * @return
     */
    PageQueryCircleDetailsOutput pageQueryCircleDetails(String userNo, String circleNo, Integer pageNo, Integer pageSize);

    ChineseCircle findByCircleNo(String circleNo);


    ResponseData reportCircleDetails(String userNo, String circleNo);

    /**
     * 删除华人圈
     * @param circleNo  华人圈编码
     * @return
     */
    boolean delCircle(String circleNo);
}
