package com.yihu.ehr.health.Transtreatin.controller;
import com.yihu.ehr.health.util.DateTimeUtil;
import  com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Transtreatin.entity.Transtreatin;
import com.yihu.ehr.health.Transtreatin.service.TranstreatinService;
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
@Api(value = "Transtreatin", description = "公卫系统-转入", tags = {"转入"})
public class TranstreatinEndPoint extends EnvelopRestEndPoint {
    @Autowired
    TranstreatinService transtreatinService;

    @RequestMapping(value = ServiceApi.Transtreatin.Transtreatin, method = RequestMethod.GET)
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
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String startTime = seachobj.get("transtreatinTime_s").toString();
            String endTime = seachobj.get("transtreatinTime_e").toString();
            String id = seachobj.get("id").toString();
            String name = seachobj.get("name").toString();
            String idNumber = seachobj.get("idNumber").toString();
            String contact = seachobj.get("contact").toString();

            try {
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
                id = id.replace("null","");
                name = name.replace("null","");
                idNumber = idNumber.replace("null","");
                contact = contact.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");
                if (!StringUtils.isEmpty(startTime)) {
                    stringBuffer.append( "transTime>=" + startTime + ";");
                }
                if (!StringUtils.isEmpty(endTime)) {
                    stringBuffer.append("transTime<=" + endTime + ";");
                }
                if (!StringUtils.isEmpty(id)) {
                    stringBuffer.append("id?" + id + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(idNumber)) {
                    stringBuffer.append( "idNumber?" + idNumber + ";");
                }
                if (!StringUtils.isEmpty(contact)) {
                    stringBuffer.append( "contact?" + contact + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<Transtreatin> userList = transtreatinService.search(fields, filters, sorts, page, size);
            long count = transtreatinService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, transtreatinService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.Transtreatin.Transtreatin, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            Transtreatin user = toEntity(userJsonData, Transtreatin.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = transtreatinService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Transtreatin.Transtreatin, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            Transtreatin user = toEntity(userJsonData, Transtreatin.class);
            transtreatinService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Transtreatin.TranstreatinEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            Transtreatin user = transtreatinService.getTranstreatin(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Transtreatin.TranstreatinEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            transtreatinService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
