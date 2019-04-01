package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.CollectionInfoMapper;
import cn.gokong.www.gokongmain.domain.CollectionInfo;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.CollectionInfoService;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.chioce.PageQueryChoiceOutput;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@Service
public class CollectionInfoServiceImpl extends ServiceImpl<CollectionInfoMapper, CollectionInfo> implements CollectionInfoService {

    @Autowired
    CollectionInfoMapper collectionInfoMapper;

    /**
     * 判断用户是否收藏该条数据
     *
     * @param userNo    用户编码
     * @param contentNo 内容编码
     * @return
     */
    @Override
    public boolean isCollection(String userNo, String contentNo) {
        List<CollectionInfo> byUserNoAndContentNo = baseMapper.findByUserNoAndContentNo(userNo, contentNo);
        if (byUserNoAndContentNo.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户编码和收藏类型查询用户收藏列表
     * @param userNo            用户编码
     * @param collectionType    收藏类型
     * @return
     */
    @Override
    public List<CollectionInfo> findByUserNoAndCollectionType(String userNo, String collectionType) {
        return collectionInfoMapper.findByUserNoAndCollectionType(userNo, collectionType);
    }

    /**
     * 设置用户收藏
     * @param userNo            用户编码
     * @param contentNo         内容编码
     * @param collectionType    收藏类型
     * @param collection        是收藏还是取消
     * @return
     */
    @Override
    @Transactional
    public boolean setUserCollection(String userNo, String contentNo, String collectionType, boolean collection) {
        //查询数据是否有这条收藏记录
        List<CollectionInfo> byUserNoAndContentNo = baseMapper.findByUserNoAndContentNo(userNo, contentNo);
        if (collection){
            if (byUserNoAndContentNo.size()>0){
                throw new GrilException("你已收藏该条信息，无需再次收藏！");
            }

            CollectionInfo collectionInfo = new CollectionInfo();
            collectionInfo.setId(DataBaseTool.createId());
            collectionInfo.setUserNo(userNo);
            collectionInfo.setContentNo(contentNo);
            collectionInfo.setCollectionType(collectionType);
            collectionInfo.setCollectionDate(DataBaseTool.createDate());
            return save(collectionInfo);
        }else {
            //取消
            if (byUserNoAndContentNo.size()<=0){
                throw new GrilException("你未收藏该条信息，无需取消收藏！");
            }
            byUserNoAndContentNo.forEach(collectionInfo -> {
                removeById(collectionInfo.getId());
            });
        }
        return true;
    }


    @Override
    public List<PageQueryInformationCollectionOutput> pageQueryInformationCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryInformationCollection(userNo,pageNo,pageSize);
    }

    @Override
    public List<PageQueryChoiceOutput> pageQueryChoiceCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryChoiceCollection(userNo,pageNo,pageSize);
    }

    @Override
    public List<PageQueryMerchantsOutput> pageQueryMerchantsCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryMerchantsCollection(userNo,pageNo,pageSize);
    }

    @Override
    public List<PageQueryChineseCircleOutput> pageQueryChineseCircleCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryChineseCircleCollection(userNo,pageNo,pageSize);
    }

    @Override
    public List<PageQueryListOutput> pageQueryUsedDealCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryUsedDealCollection(userNo,pageNo,pageSize);
    }

    @Override
    public List<cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput> pageQueryCompanyRecruitmentCollection(String userNo, Integer pageNo, Integer pageSize) {
        return baseMapper.pageQueryCompanyRecruitmentCollection(userNo,pageNo,pageSize);
    }
}
