package com.yihu.ehr.health.BasicFamilyInfor.controller;
import com.yihu.ehr.health.BasicFamilyInfor.service.BasicFamilyInforMemberService;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.util.DateTimeUtil;
import  com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.service.BasicFamilyInforService;
import com.yihu.ehr.util.id.BizObject;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "BasicFamilyInfor", description = "公卫系统-家庭资料管理", tags = {"家庭资料管理"})
public class BasicFamilyInforEndPoint extends EnvelopRestEndPoint {
    @Autowired
    BasicFamilyInforService basicfamilyinforService;
    @Autowired
    BasicFamilyInforMemberService basicFamilyInforMemberService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.BasicFamilyInfor.BasicFamilyInfor, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop searchUsers(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page,
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String fbTime_s = seachobj.get("fbTime_s").toString();
            String fbTime_e = seachobj.get("fbTime_e").toString();
            String id = seachobj.get("id").toString();
            String headeName = seachobj.get("headeName").toString();
            String responseDoctor = seachobj.get("responseDoctor").toString();
            String belongCommunity = "";
            if(seachobj.has("belongCommunity"))
            {
                belongCommunity = seachobj.get("belongCommunity").toString();
            }
            try {
                fbTime_s = fbTime_s.replace("null","");
                fbTime_e = fbTime_e.replace("null","");
                id = id.replace("null","");
                headeName = headeName.replace("null","");
                responseDoctor = responseDoctor.replace("null","");
                belongCommunity = belongCommunity.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");
                if (!StringUtils.isEmpty(fbTime_s)) {
                    stringBuffer.append( "fbTime>=" + fbTime_s + ";");
                }
                if (!StringUtils.isEmpty(fbTime_e)) {
                    stringBuffer.append("fbTime<=" +fbTime_e + ";");
                }
                if (!StringUtils.isEmpty(id)) {
                    stringBuffer.append("id?" + id + ";");
                }
                if (!StringUtils.isEmpty(headeName)) {
                    stringBuffer.append("headeName?" + headeName + ";");
                }
                if (!StringUtils.isEmpty(responseDoctor)) {
                    stringBuffer.append( "responseDoctor?" + responseDoctor + ";");
                }
                if (!StringUtils.isEmpty(belongCommunity)) {
                    stringBuffer.append( "belongCommunity?" + belongCommunity + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<BasicFamilyInfor> userList = basicfamilyinforService.search(fields, filters, sorts, page, size);
            long count = basicfamilyinforService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);

            pagedResponse(request, response, basicfamilyinforService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(userList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    public String changeToUtc(String datetime) throws Exception{
        Date date = DateTimeUtil.simpleDateTimeParse(datetime);
        return DateTimeUtil.utcDateTimeFormat(date);
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInfor.BasicFamilyInfor, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInfor user = toEntity(userJsonData, BasicFamilyInfor.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = basicfamilyinforService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInfor.BasicFamilyInfor, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInfor user = toEntity(userJsonData, BasicFamilyInfor.class);
            basicfamilyinforService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }



    @RequestMapping(value = ServiceApi.BasicFamilyInfor.BasicFamilyInforEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInfor user = basicfamilyinforService.getBasicFamilyInfor(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


    @RequestMapping(value = ServiceApi.BasicFamilyInfor.BasicFamilyInforEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            basicfamilyinforService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
