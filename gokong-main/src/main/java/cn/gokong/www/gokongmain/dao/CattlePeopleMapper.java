package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.CattlePeople;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
public interface CattlePeopleMapper extends BaseMapper<CattlePeople> {


    @Select("SELECT user_no,cattle_people_abstract,cattle_people_photo,cattle_worth FROM cattle_people WHERE user_no = #{userno} LIMIT 1")
    CattlePeople findCattlePeopleDetail(@Param("userno") String userno);

    /**
     * @param userNo               用户编码
     * @param cattlePeoplePhoto    更换的图片
     * @param cattleWorth          更换的个人价值
     * @param cattlePeopleAbstract 跟换何人简介
     * @return
     */
    @Update("UPDATE cattle_people SET cattle_people_abstract = #{cattlePeopleAbstract} ,cattle_people_photo = #{cattlePeoplePhoto},cattle_worth = #{cattleWorth} WHERE user_no = #{userNo}")
    Integer updateCattlePeople(@Param("userNo") String userNo, @Param("cattlePeoplePhoto") String cattlePeoplePhoto, @Param("cattleWorth") BigDecimal cattleWorth, @Param("cattlePeopleAbstract") String cattlePeopleAbstract);

    /**
     * 更新插入数据
     *
     * @param id
     * @param userno
     * @param cattlePeopleName
     * @param cattlePeopleWork
     * @param cattlePeopleAbstract
     * @param cattlePeopleLabel
     * @param cattlePeopleAddress
     * @param cattlePeoplePhoto
     * @param cattleWorth
     * @return
     */
    @Insert("INSERT INTO cattle_people(id,user_no,cattle_people_name,cattle_people_work,cattle_people_abstract,cattle_people_address,cattle_people_label,cattle_people_photo,cattle_people_city_no,cattle_worth,audit_status)" +
            " VALUES (#{id}, #{userno}, #{cattlePeopleName}, #{cattlePeopleWork}, #{cattlePeopleAbstract}, #{cattlePeopleAddress}, #{cattlePeopleLabel}, #{cattlePeoplePhoto}, #{cattlePeopleAddress}, #{cattleWorth},'0') ON DUPLICATE KEY UPDATE " +
            "id = #{id},user_no = #{userno},cattle_people_name = #{cattlePeopleName},cattle_people_work = #{cattlePeopleWork},cattle_people_abstract = #{cattlePeopleAbstract},cattle_people_address = #{cattlePeopleAddress},cattle_people_label = #{cattlePeopleLabel}," +
            "cattle_people_photo = #{cattlePeoplePhoto},cattle_people_city_no = #{cattlePeopleAddress},cattle_worth = #{cattleWorth},audit_status = '0' ")
    int insertCattlePeople(@Param("id") String id, @Param("userno") String userno, @Param("cattlePeopleName") String cattlePeopleName,
                           @Param("cattlePeopleWork") String cattlePeopleWork, @Param("cattlePeopleAbstract") String cattlePeopleAbstract,
                           @Param("cattlePeopleLabel") String cattlePeopleLabel, @Param("cattlePeopleAddress") String cattlePeopleAddress,
                           @Param("cattlePeoplePhoto") String cattlePeoplePhoto, @Param("cattleWorth") String cattleWorth);

    CattlePeople querycattlePeopleInfo(@Param("userno") String userno);
}
