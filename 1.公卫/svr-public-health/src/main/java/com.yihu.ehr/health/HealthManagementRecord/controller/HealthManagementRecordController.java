package com.yihu.ehr.health.HealthManagementRecord.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ElderlyStatistical.entity.ElderlyStatistical;
import com.yihu.ehr.health.ElderlyStatistical.service.ElderlyStatisticesService;
import com.yihu.ehr.health.HealthManagementRecord.service.*;
import com.yihu.ehr.health.HealthManagementRecord.entity.*;
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
@Api(value = " HealthManagementRecord", description = "公卫系统-老年人中医药健康管理服务记录表", tags = {"老年人中医药健康管理服务记录表"})

public class HealthManagementRecordController extends EnvelopRestEndPoint {
    @Autowired
    HealthManagementRecordService healthManagementRecordService;
    @Autowired
    ElderlyStatisticesService elderlyStatisticesService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.HealthManagementRecord.HealthManagementRecord, method = RequestMethod.GET)
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
            org.json.JSONObject seachobj = new JSONObject(filters);
            String name = seachobj.get("name").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String workid = seachobj.get("workid").toString();
            String healthDate_s = seachobj.get("healthDate_s").toString();
            String healthDate_e = seachobj.get("healthDate_e").toString();

            try {
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                healthDate_s = healthDate_s.replace("null","");
                healthDate_e = healthDate_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(workid)) {
                    stringBuffer.append("workid?" + workid + ";");
                }
                if (!StringUtils.isEmpty(healthDate_s)) {
                    stringBuffer.append( "healthDate>=" + healthDate_s + ";");
                }
                if (!StringUtils.isEmpty(healthDate_e)) {
                    stringBuffer.append("healthDate<=" + healthDate_e + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<HealthManagementRecord> userList = healthManagementRecordService.search(fields, filters, sorts, page, size);
            long count = healthManagementRecordService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, healthManagementRecordService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.HealthManagementRecord.HealthManagementRecord, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            HealthManagementRecord user = toEntity(userJsonData, HealthManagementRecord.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = healthManagementRecordService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthManagementRecord.HealthManagementRecord, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            HealthManagementRecord user = toEntity(userJsonData, HealthManagementRecord.class);
            healthManagementRecordService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthManagementRecord.HealthManagementRecordEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            HealthManagementRecord user = healthManagementRecordService.getHealthManagementRecord(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthManagementRecord.HealthManagementRecordEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {

            List<Perinfor> personDatas = null;              //个人档案信息
            List<ElderlyStatistical> datas = null;          //统计表信息
            HealthManagementRecord onedata = healthManagementRecordService.getHealthManagementRecord(userId);

            healthManagementRecordService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
    public void totalDelate (Perinfor entity){
        StringBuffer delateBuffer = new StringBuffer();
        List<HealthManagementRecord> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = healthManagementRecordService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(HealthManagementRecord item:delateList){
                healthManagementRecordService.deleteUser(item.getId());
            }
        }
    }

}
