package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.ApiTool;
import cn.gokong.www.gokongmain.dao.MobileVersionMapper;
import cn.gokong.www.gokongmain.domain.MobileVersion;
import cn.gokong.www.gokongmain.service.MobileVersionService;
import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionOutput;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * APP版本跟新控制 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-26
 */
@Service
public class MobileVersionServiceImpl extends ServiceImpl<MobileVersionMapper, MobileVersion> implements MobileVersionService {

    /**
     * 获取APP版本信息
     * @param appName   APP名称
     * @param appType   APP类型
     * @param version   当前版本信息
     * @return
     */
    @Override
    public QueryMobileVersionOutput queryMobileVersion(String appName, int appType,String version) throws Exception {
        QueryMobileVersionOutput output = new QueryMobileVersionOutput();
        MobileVersion mobileVersion = baseMapper.queryMobileVersion(appName, appType);
        //如果版本信息为NULL者不更新
        if (mobileVersion==null){
            output.setUpdate(false);
        }else {
            int compareVersion = ApiTool.compareVersion(version, mobileVersion.getAppVersion());
            //如果版本号相同则不更新
            if (compareVersion==0) {//版本号相等不需要更新版本
                output.setUpdate(false);
            } else if (compareVersion>0){//当前版本大于手机中的版本号，那么就不升级
                output.setUpdate(false);
            }else if (compareVersion<0){//当前版本低于数据库中的版本 ，需要升级提示
                output.setUpdate(true);
                output.setUpdateLog(mobileVersion.getAppLog());
                output.setApkFileUrl(mobileVersion.getAppFilePath());
                output.setConstraints(mobileVersion.getConstraints().equalsIgnoreCase("1")?true:false);
                output.setNewVersion(mobileVersion.getAppVersion());
                output.setTargetSize(mobileVersion.getTargetSize());
                Map<String, Object> beanToMap = BeanUtil.beanToMap(output, true, true);
                output.setSign(SecureUtil.signParams(DigestAlgorithm.MD5, beanToMap, "&", "=", true));
            }
        }
        return output;
    }
}
