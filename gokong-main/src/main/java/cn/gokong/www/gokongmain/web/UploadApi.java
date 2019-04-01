package cn.gokong.www.gokongmain.web;

import cn.gokong.www.base.entity.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 说明:上传文件 前端控制器
 *
 * @author ikook
 * @CreateDate 2018/9/12 17:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/v2/upload")
@Api(tags = "上传文件", description = "前端控制器")
public class UploadApi extends BaseController{

    @RequestMapping(value = "/ftpImg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "FTP上传单张图片", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片)",paramType = "query"),
    })
    public Object ftpImg(@RequestParam("img") MultipartFile img ,@RequestParam String type) {
        String path = uploadService.ftpImg(img, type);
        return getSuccess(path);
    }

    @RequestMapping(value = "/ftpImgs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "FTP上传多张图片", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "img", value = "直接用表单提交数据，图片不需要进行转换",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "图片类型(1:头像 2:背景图 3:证件图 4：资讯图片 5：华人圈图片 6：同城活动图片 7：发布二手图片)",paramType = "query"),
    })
    public Object ftpImgs(@RequestParam("img") MultipartFile[] img ,@RequestParam String type) {
        List<String> path = uploadService.ftpImgs(img, type);
        return getSuccess(path);
    }
}
