package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.FansInfo;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 粉丝表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface FansInfoService extends IService<FansInfo> {

    /**
     * 添加关注
     *
     * @param userNo     用户编码
     * @param fansUserNo 粉丝编码
     * @param focus      状态
     * @return
     */
    boolean setFocus(String userNo, String fansUserNo, boolean focus);

    /**
     * 分页获取粉丝列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<QueryUserOutput> pageQueryUserFocus(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 是否关注
     *
     * @param userNo     用户编码
     * @param fansUserNo 粉丝编码
     * @return
     */
    boolean isFocus(String userNo, String fansUserNo);

    /**
     * 查询我关注的人
     *
     * @param userNo 用户编码
     * @return
     */
    List<FansInfo> findByFansUserNo(String userNo);

    /**
     * 查询粉丝数量
     *
     * @param userNo 用户编码
     * @return
     */
    int countByUserNo(String userNo);

    /**
     * 小程序分享添加粉丝
     *
     * @param fansUserNo
     * @param userNo
     * @return
     */
    boolean setFocuss(String fansUserNo, String userNo);
}
