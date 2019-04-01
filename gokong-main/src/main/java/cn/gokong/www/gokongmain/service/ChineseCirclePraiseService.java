package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.ChineseCirclePraise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 华人圈点赞表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
public interface ChineseCirclePraiseService extends IService<ChineseCirclePraise> {

    /**
     * 设置华人圈点赞
     * @param userNo    用户编码
     * @param circleNo  华人圈编码
     * @param like
     * @return
     */
    boolean setCircleLike(String userNo, String circleNo, boolean like);

    /**
     * 是否点赞
     * @param userNo    用户编码
     * @param circleNo  华人圈编码
     * @return
     */
    boolean isLike(String userNo,String circleNo);
}
