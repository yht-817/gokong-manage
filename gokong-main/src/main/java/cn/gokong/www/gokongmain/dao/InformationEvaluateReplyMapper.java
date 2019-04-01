package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.InformationEvaluateReply;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationChildEvaluateOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 咨询信息评价回复记录表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
public interface InformationEvaluateReplyMapper extends BaseMapper<InformationEvaluateReply> {

    List<PageQueryInformationChildEvaluateOutput> pageQueryInformationChildEvaluate(@Param("evaluateNo")String evaluateNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
