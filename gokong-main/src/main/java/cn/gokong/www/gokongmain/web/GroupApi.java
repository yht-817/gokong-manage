package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.base.GroupNoBase;
import cn.gokong.www.gokongmain.vo.group.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 好友群信息 前端控制器
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/v2/friendGroup")
@Api(tags = "群(创建/修改/解散/查询群)", description = "GroupApi")
public class GroupApi extends BaseController {

    @RequestMapping(value="/createGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "创建群聊", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "群名称",paramType = "query",defaultValue = "GoKong1群"),
            @ApiImplicitParam(name = "groupHead", value = "群头像",paramType = "query",defaultValue = "http://static.gokong.cn/img/defualt/userhead"),
            @ApiImplicitParam(name = "userNo", value = "创建人编码",paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "cityNo", value = "城市编码",paramType = "query",defaultValue = "10000101"),
            @ApiImplicitParam(name = "groupNode", value = "群公告",paramType = "query",defaultValue = "这是群公告"),
            @ApiImplicitParam(name = "ifPayGroup", value = "是否付费(公开群:10340001 收费群:10340002)",paramType = "query",defaultValue = "10340001"),
            @ApiImplicitParam(name = "payAmount", value = "收费金额",paramType = "query"),
            @ApiImplicitParam(name = "joinMark", value = "是否需要群主同意加入(true false)",paramType = "query"),
    })
    public Object createGroup(@RequestBody(required = false) @Valid RequestData<CreateGroupInput> requestData, BindingResult bindingResult){
        CreateGroupInput data = requestData.getData();
        String groupNo = friendGroupService.createGroup(data.getUserNo(), data.getGroupName(), data.getGroupHead(), data.getGroupNode(), data.getIfPayGroup(), data.getPayAmount(),data.getCityNo(),data.isJoinMark(),"1");
        return getSuccess(groupNo);
    }

    @RequestMapping(value="/dissolveGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "解散群聊", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query",defaultValue = ""),
    })
    public Object dissolveGroup(@RequestBody(required = false) @Valid RequestData<GroupNoBase> requestData, BindingResult bindingResult){
        GroupNoBase data = requestData.getData();
        boolean dissolveGroup = friendGroupService.dissolveGroup(data.getGroupNo());
        return getBoolean(dissolveGroup);
    }

    @RequestMapping(value="/updateGroupData",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "修改群资料", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "groupName", value = "群名称",paramType = "query",defaultValue = "new群名称"),
            @ApiImplicitParam(name = "groupNode", value = "群公告",paramType = "query",defaultValue = "new群公告"),
            @ApiImplicitParam(name = "groupHead", value = "群头像",paramType = "query",defaultValue = "new群头像"),
            @ApiImplicitParam(name = "joinMark", value = "是否需要群主同意加入(true false)",paramType = "query"),
    })
    public Object updateGroupData(@RequestBody(required = false) @Valid RequestData<UpdateGroupDataInput> requestData, BindingResult bindingResult){
        UpdateGroupDataInput data = requestData.getData();
        boolean updateGroupData = friendGroupService.updateGroupData(data.getGroupNo(),data.getGroupName(),data.getGroupNode(),data.getGroupHead(),data.isJoinMark());
        return getBoolean(updateGroupData);
    }

    @RequestMapping(value="/queryGroupDetails",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询群详情", httpMethod = "POST",response = QueryGroupDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = "yh15367309978639"),
    })
    public Object queryGroupDetails(@RequestBody(required = false) @Valid RequestData<QueryGroupDetailsInput> requestData, BindingResult bindingResult){
        QueryGroupDetailsInput data = requestData.getData();
        QueryGroupDetailsOutput queryGroupDetailsOutput = friendGroupService.queryGroupDetails(data.getGroupNo(), data.getUserNo());
        return getSuccess(queryGroupDetailsOutput);
    }

    /*@RequestMapping(value="/pageQueryUserGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户群列表", httpMethod = "POST",response = PageQueryUserGroupOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "keyword", value = "搜索关键词", paramType = "query"),
    })
    public Object pageQueryUserGroup(@RequestBody(required = false) @Valid RequestData<PageQueryUserGroupBase> requestData, BindingResult bindingResult){
        PageQueryUserGroupBase data = requestData.getData();
        List<PageQueryUserGroupOutput> pageQueryUserGroupOutputs = friendGroupService.pageQueryUserGroup(data.getKeyword(),data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryUserGroupOutputs);
    }*/

    @RequestMapping(value="/pageSearchGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页检索群", httpMethod = "POST",response = PageSearchGroupOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "keyWord", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "cityName", value = "城市名称",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public Object pageSearchGroup(@RequestBody(required = false) @Valid RequestData<PageSearchGroupInput> requestData, BindingResult bindingResult){
        PageSearchGroupInput data = requestData.getData();
        List<PageSearchGroupOutput> pageSearchGroupOutputs = friendGroupService.pageSearchGroup(data.getUserNo(),data.getKeyWord(),data.getCityName(),data.getPageNo(), data.getPageSize());
        return getSuccess(pageSearchGroupOutputs);
    }
}

