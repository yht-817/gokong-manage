package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.CollectionInfo;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.chioce.PageQueryChoiceOutput;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface CollectionInfoService extends IService<CollectionInfo>{

    /**
     * 判断用户是否收藏该条信息
     * @param userNo        用户编码
     * @param contentNo     内容编码
     * @return
     */
    boolean isCollection(String userNo, String contentNo);



    /**
     * 根据用户编码和收藏类型查询用户收藏列表
     * @param userNo            用户编码
     * @param collectionType    收藏类型
     * @return
     */
	List<CollectionInfo> findByUserNoAndCollectionType(String userNo,String collectionType);

    /**
     * 设置用户收藏
     * @param userNo            用户编码
     * @param contentNo         内容编码
     * @param collectionType    收藏类型
     * @param collection        是收藏还是取消
     * @return
     */
    boolean setUserCollection(String userNo, String contentNo, String collectionType, boolean collection);


    /**
     * 分页获取资讯收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInformationCollectionOutput> pageQueryInformationCollection(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页获取精选收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryChoiceOutput> pageQueryChoiceCollection(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页获取商户收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryMerchantsOutput> pageQueryMerchantsCollection(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页获取华人圈收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryChineseCircleOutput> pageQueryChineseCircleCollection(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页获取二手交易收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryUsedDealCollection(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页获取求职招聘收藏列表
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput> pageQueryCompanyRecruitmentCollection(String userNo, Integer pageNo, Integer pageSize);
}
