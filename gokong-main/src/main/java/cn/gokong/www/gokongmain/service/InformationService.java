package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.Information;
import cn.gokong.www.gokongmain.vo.information.PageQueryInformationHomeOutput;
import cn.gokong.www.gokongmain.vo.information.QueryArticleContentOuput;
import cn.gokong.www.gokongmain.vo.information.QueryInformationDetailsOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资讯信息表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface InformationService extends IService<Information> {

    /**
     * 分页获取我发布的资讯
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInformationCollectionOutput> pageQueryMyInformation(String userNo, Integer pageNo, Integer pageSize);

    /**
     * 分页查询资讯列表
     * @param keyword   关键字
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInformationHomeOutput> pageQueryHomeInformation(String keyword, Integer pageNo, Integer pageSize);

    /**
     * 查询资讯详情
     * @param userNo        用户编码
     * @param informationNo 资讯编码
     * @return
     */
    QueryInformationDetailsOutput queryInformationDetails(String userNo, String informationNo);

    /**
     * 发布资讯
     * @param userNo                用户编码
     * @param informationMode       资讯模式
     * @param informationName       资讯名称
     * @param informationPhoto      资讯图片
     * @param informationContent    资讯内容
     * @param photoSizeType         资讯布局
     * @return
     */
    Map<String,String> releaseInformation(String userNo, String informationMode, String informationName, String informationPhoto, String informationContent, String photoSizeType) throws IOException;

    /**
     * 删除资讯
     * @param informationNo 资讯编码
     * @return
     */
    boolean delInformation(String informationNo) throws IOException;

    /**
     * 根据链接获取内容
     * @param url   链接地址
     * @param type  链接类型
     * @return
     */
    QueryArticleContentOuput queryArticleContent(String url, String type) throws IOException;

    /**
     * 修改资讯
     * @param informationNo         资讯编码
     * @param informationMode       资讯模式
     * @param informationName       资讯名称
     * @param informationPhoto      资讯图片
     * @param informationContent    资讯内容
     * @param photoSizeType         资讯布局
     * @return
     */
    Map<String,String> updateInformation(String informationNo, String informationMode, String informationName, String informationPhoto, String informationContent, String photoSizeType) throws IOException;

    /**
     * 分页查询我关注的资讯
     * @param keyword   关键字
     * @param userNo    用户编码
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInformationHomeOutput> pageQueryHomeFansInformation(String keyword, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 根据资讯编码查询资讯信息
     * @param informationNo 资讯编码
     * @return
     */
    Information findByInformationNo(String informationNo);
}
