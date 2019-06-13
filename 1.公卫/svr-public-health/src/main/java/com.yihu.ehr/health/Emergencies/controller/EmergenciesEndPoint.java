package com.yihu.ehr.health.Emergencies.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Emergencies.entity.EmergenciesEntity;
import com.yihu.ehr.health.Emergencies.service.EmergenciesService;
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
@Api(value = "Emergencies", description = "公卫系统-传染病与突发公卫", tags = {"传染病与突发公卫"})
public class EmergenciesEndPoint extends EnvelopRestEndPoint {
    @Autowired
    EmergenciesService emergenciesService;

    @RequestMapping(value = ServiceApi.Emergencies.Emergencies, method = RequestMethod.GET)
    @ApiOperation(value = "获取传染病与突发公卫列表", notes = "根据查询条件获取传染病与突发公卫列表在前端表格展示")
    public Envelop search(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "20")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = new JSONObject(filters);
            String reportingDate_s = seachobj.get("reportingDate_s").toString();
            String reportingDate_e = seachobj.get("reportingDate_e").toString();
            String eventTime_s = seachobj.get("eventTime_s").toString();
            String eventTime_e = seachobj.get("eventTime_e").toString();
            String messageClass = seachobj.get("messageClass").toString();
            String eventLevel = seachobj.get("eventLevel").toString();
            String eventName = seachobj.get("eventName").toString();
            String rapporteur = seachobj.get("rapporteur").toString();
            String messageSource = seachobj.get("messageSource").toString();
            String occurrenceArea = seachobj.get("occurrenceArea").toString();
            try {
                reportingDate_s = reportingDate_s.replace("null","");
                reportingDate_e = reportingDate_e.replace("null","");
                eventTime_s = eventTime_s.replace("null","");
                eventTime_e = eventTime_e.replace("null","");
                messageClass = messageClass.replace("null","");
                eventLevel = eventLevel.replace("null","");
                eventName = eventName.replace("null","");
                rapporteur = rapporteur.replace("null","");
                messageSource = messageSource.replace("null","");
                occurrenceArea = occurrenceArea.replace("null","");
                if (!StringUtils.isEmpty(reportingDate_s)) {
                    stringBuffer.append( "reportingDate>" + reportingDate_s + ";");
                }
                if (!StringUtils.isEmpty(reportingDate_e)) {
                    stringBuffer.append("reportingDate<" + reportingDate_e + ";");
                }
                if (!StringUtils.isEmpty(eventTime_s)) {
                    stringBuffer.append( "eventTime>" + eventTime_s + ";");
                }
                if (!StringUtils.isEmpty(eventTime_e)) {
                    stringBuffer.append("eventTime<" + eventTime_e + ";");
                }
                if (!StringUtils.isEmpty(messageClass)) {
                    stringBuffer.append("messageClass?" + messageClass + ";");
                }
                if (!StringUtils.isEmpty(eventLevel)) {
                    stringBuffer.append( "eventLevel?" + eventLevel + ";");
                }
                if (!StringUtils.isEmpty(eventName)) {
                    stringBuffer.append( "eventName?" + eventName + ";");
                }
                if (!StringUtils.isEmpty(rapporteur)) {
                    stringBuffer.append( "rapporteur?" + rapporteur + ";");
                }
                if (!StringUtils.isEmpty(messageSource)) {
                    stringBuffer.append( "messageSource?" + messageSource + ";");
                }
                if (!StringUtils.isEmpty(occurrenceArea)) {
                    stringBuffer.append( "occurrenceArea?" + occurrenceArea + ";");
                }
                stringBuffer.append( "deleteState?" + "未删" + ";");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<EmergenciesEntity> emergenciesList = emergenciesService.search(fields, filters, sorts, page, size);
            long count = emergenciesService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, emergenciesService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(emergenciesList);
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

    @RequestMapping(value = ServiceApi.Emergencies.Emergencies, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建传染病与突发公卫", notes = "绑定传染病与突发公卫信息")
    public Envelop addData(
            @ApiParam(name = "emergenciesJsonData", value = "传染病与突发公卫信息json串", defaultValue = "")
            @RequestBody String emergenciesJsonData) {
        Envelop envelop = new Envelop();
        try {
            EmergenciesEntity emergencies = toEntity(emergenciesJsonData, EmergenciesEntity.class);
            emergencies.setId(getObjectId(BizObject.User));
            emergencies.setCreateDate(new Date());
            emergencies = emergenciesService.save(emergencies);
            envelop.setSuccessFlg(true);
            envelop.setObj(emergencies);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Emergencies.Emergencies, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改传染病与突发公卫", notes = "重新绑定传染病与突发公卫信息")
    public Envelop updateData(
            @ApiParam(name = "emergenciesJsonData", value = "传染病与突发公卫Json对象", defaultValue = "")
            @RequestBody String emergenciesJsonData) {
        Envelop envelop = new Envelop();
        try {
            EmergenciesEntity emergencies = toEntity(emergenciesJsonData, EmergenciesEntity.class);
            emergenciesService.save(emergencies);
            envelop.setSuccessFlg(true);
            envelop.setObj(emergencies);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Emergencies.EmergenciesEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取传染病与突发公卫信息")
    public Envelop getList(
            @ApiParam(name = "emergenciesId", value = "传染病与突发公卫id", defaultValue = "")
            @PathVariable(value = "emergenciesId") String emergenciesId) {
        Envelop envelop = new Envelop();
        try {
            EmergenciesEntity emergencies = emergenciesService.getEmergencies(emergenciesId);
            envelop.setSuccessFlg(true);
            envelop.setObj(emergencies);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Emergencies.EmergenciesEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除传染病与突发公卫", notes = "根据id删除传染病与突发公卫")
    public Envelop removeData(
            @ApiParam(name = "emergenciesId", value = "传染病与突发公卫id", defaultValue = "")
            @PathVariable(value = "emergenciesId") String emergenciesId) {
        Envelop envelop = new Envelop();
        try {
            emergenciesService.delete(emergenciesId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
