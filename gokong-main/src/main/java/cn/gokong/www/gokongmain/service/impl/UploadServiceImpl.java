package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.ftp.FTPUtil;
import cn.gokong.www.gokongmain.ftp.FtpDirEnum;
import cn.gokong.www.gokongmain.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:上传文件服务接口实现
 *
 * @author ikook
 * @CreateDate 2018/9/12 17:13
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Service
public class UploadServiceImpl implements UploadService {

    /**
     * FTP上传单张图片
     * @param img   原生图片类型
     * @param type  图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片 8:公司logo)
     * @return
     */
    @Override
    public String ftpImg(MultipartFile img, String type) {
        String url = "";
        switch (type){
            case "1":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010003);
                break;
            case "2":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010004);
                break;
            case "3":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010005);
                break;
            case "4":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010002);
                break;
            case "5":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010007);
                break;
            case "6":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010008);
                break;
            case "7":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010009);
                break;
            case "8":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010010);
                break;
        }
        return url;
    }

    /**
     * FTP上传多张图片
     * @param img   图片数据
     * @param type  图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片 8:公司logo)
     * @return
     */
    @Override
    public List<String> ftpImgs(MultipartFile[] img, String type) {
        List<String> url = new ArrayList<>();

        switch (type){
            case "1":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010003);
                break;
            case "2":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010004);
                break;
            case "3":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010005);
                break;
            case "4":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010002);
                break;
            case "5":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010007);
                break;
            case "6":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010008);
                break;
            case "7":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010009);
                break;
            case "8":
                url = FTPUtil.uploadImage(img,FtpDirEnum.CODE_100010010);
                break;
        }
        return url;
    }
}
