package cn.gokong.www.gokongmain.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 说明:上传文件服务接口
 *
 * @author ikook
 * @CreateDate 2018/9/12 17:12
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface UploadService {

    /**
     * FTP上传单张图片
     * @param img   原生图片类型
     * @param type  图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片 8:公司logo)
     * @return
     */
    String ftpImg(MultipartFile img, String type);

    /**
     * FTP上传多张图片
     * @param img   图片数据
     * @param type  图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片 8:公司logo)
     * @return
     */
    List<String> ftpImgs(MultipartFile[] img, String type);
}
