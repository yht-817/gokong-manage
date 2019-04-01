package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.BrushSingle;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-25
 */
public interface BrushSingleService extends IService<BrushSingle> {

    ResponseData findBrushSingle(String keyword, int pageno, String phoneNo);

    ResponseData addPlaceOrder(String productNo, String phoneNo, int highPraise);

    ResponseData addOrderUrl(MultipartFile img, String orderNo, String payNo, String payType);

    Object queryOrder(String phoneNo);

    Object queryOffline(String phoneNo, String offphoneNo);

    Object registerRelation(String phoneNo, String partNo);

    Object queryTransactionAmount(String userNo);

    Object withdrawal(String userNo, String payNo, String amount, String payType, String phoneNo);

    Map<String, Object> queryOnOnUserInfo(String phoneNo);

    Object registerOnRelation(String phoneNo, String partNo);

    Map<String, String> queryCode(String phoneNo);

    Map<String, String> queryMps(String phoneNo);

    int insertMurl(String id, String phoneNo, String imgCode, String imgCode1);

    Object queryPayAccount(String phoneNo);

    Object changePayAccount(String phoneNo, String payType, String payNo);
}
