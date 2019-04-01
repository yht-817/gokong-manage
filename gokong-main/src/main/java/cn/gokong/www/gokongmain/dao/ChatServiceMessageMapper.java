package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ChatServiceMessage;
import cn.gokong.www.gokongmain.vo.service_message.PageQueryServiceMessageOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 服务通知模板 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
public interface ChatServiceMessageMapper extends BaseMapper<ChatServiceMessage> {

    /**
     * 根据服务编码查询消息信息
     *
     * @param serviceNo 服务编码
     * @return
     */
    @Select("select * from chat_service_message where service_no = #{serviceNo}")
    ChatServiceMessage findByServiceNo(@Param("serviceNo") String serviceNo);

    /**
     * 分页查询服务消息
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select send_no,send_head,notice_type,send_title,send_date,send_text,send_project1,send_project2,send_url,service_no,msg_state " +
            "from chat_service_message " +
            "where user_no = #{userNo} " +
            "order by send_date desc " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryServiceMessageOutput> pageQueryServiceMessage(@Param("userNo") String userNo, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("SELECT COUNT(id) FROM chat_service_message WHERE service_no = #{applyNo}")
    int findByServiceNoAndChatMessage(@Param("applyNo") String applyNo);
}
