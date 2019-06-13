package com.yihu.ehr.health.Perinfor.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.id.BizObject;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
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
@Api(value = "Perinfor", description = "公卫系统-个人资料管理", tags = {"个人资料管理"})
public class PerinforEndPoint extends EnvelopRestEndPoint {
    @Autowired
    PerinforService PerinforService;

    @RequestMapping(value = ServiceApi.Perinfor.Perinfor, method = RequestMethod.GET)
    @ApiOperation(value = "身份证验证", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop checkUsers(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "20")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page,
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            String peopleId = "";
            try {
                org.json.JSONObject seachobj = new JSONObject(filters);
                String name = seachobj.get("name").toString();
                peopleId = seachobj.get("peopleId").toString();
                String cardNumber = seachobj.get("cardNumber").toString();
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                cardNumber = cardNumber.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(cardNumber)) {
                    stringBuffer.append("cardNumber=" + cardNumber + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<Perinfor> userList = PerinforService.search(fields, filters, sorts, page, size);
            if( StringUtils.isEmpty(peopleId) ){
                userList = null;
            }
            envelop.setPageSize(size);
            long count = PerinforService.getCount(filters);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, PerinforService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(userList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Perinfor.PerinforPage, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop searchUsers(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "20")
            @RequestParam(value = "size", required = false) Integer size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) Integer page,
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            try {
                org.json.JSONObject seachobj = new JSONObject(filters);
                String name = seachobj.get("name").toString();
                String peopleId = seachobj.get("peopleId").toString();
                String cardNumber = seachobj.get("cardNumber").toString();
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                cardNumber = cardNumber.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(cardNumber)) {
                    stringBuffer.append("cardNumber?" + cardNumber + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<Perinfor> userList = PerinforService.search(fields, filters, sorts, page, size);
            envelop.setPageSize(size);
            long count = PerinforService.getCount(filters);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, PerinforService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(userList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    //yyyy-MM-dd HH:mm:ss 转换为yyyy-MM-dd'T'HH:mm:ss'Z 格式
    public String changeToUtc(String datetime) throws Exception{
        Date date = DateTimeUtil.simpleDateTimeParse(datetime);
        return DateTimeUtil.utcDateTimeFormat(date);
    }

    @RequestMapping(value = ServiceApi.Perinfor.Perinfor, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            Perinfor user = toEntity(userJsonData, Perinfor.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = PerinforService.saveUser(null,user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Perinfor.Perinfor, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            Perinfor user = toEntity(userJsonData, Perinfor.class);
            Perinfor beforeUser = PerinforService.getPerinfor(user.getId());
            PerinforService.saveUser(beforeUser,user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Perinfor.PerinforEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            Perinfor user = PerinforService.getPerinfor(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Perinfor.PerinforEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            PerinforService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
}
