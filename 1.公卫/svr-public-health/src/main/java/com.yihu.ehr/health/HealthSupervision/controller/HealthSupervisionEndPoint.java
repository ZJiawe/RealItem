package com.yihu.ehr.health.HealthSupervision.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.HealthSupervision.entity.HealthSupervisionEntity;
import com.yihu.ehr.health.HealthSupervision.service.HealthSupervisionService;
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
@Api(value = "Follow", description = "公卫系统-卫生监督协管", tags = {"卫生监督协管"})
public class HealthSupervisionEndPoint extends EnvelopRestEndPoint {
    @Autowired
    HealthSupervisionService healthsupervisionService;

    @RequestMapping(value = ServiceApi.HealthSupervision.HealthSupervision, method = RequestMethod.GET)
    @ApiOperation(value = "获取卫生监督协管列表", notes = "根据查询条件获取卫生监督协管列表在前端表格展示")
    public Envelop searchhealthSupervisions(
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
            org.json.JSONObject seachobj = new JSONObject(filters);
            String findTime_s = seachobj.get("findTime_s").toString();
            String findTime_e = seachobj.get("findTime_e").toString();
            String informContent = seachobj.get("informContent").toString();
            String reporter = seachobj.get("reporter").toString();

            try {
                findTime_s = findTime_s.replace("null","");
                findTime_e = findTime_e.replace("null","");
                informContent = informContent.replace("null","");
                reporter = reporter.replace("null","");
                if (!StringUtils.isEmpty(findTime_s)) {
                    stringBuffer.append( "findTime>" + findTime_s + ";");
                }
                if (!StringUtils.isEmpty(findTime_e)) {
                    stringBuffer.append("findTime<" + findTime_e + ";");
                }
                if (!StringUtils.isEmpty(informContent)) {
                    stringBuffer.append("informContent?" + informContent + ";");
                }
                if (!StringUtils.isEmpty(reporter)) {
                    stringBuffer.append( "reporter?" + reporter + ";");
                }
                stringBuffer.append( "deleteState?" + "未删" + ";");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<HealthSupervisionEntity> healthSupervisionList = healthsupervisionService.search(fields, filters, sorts, page, size);
            long count = healthsupervisionService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, healthsupervisionService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(healthSupervisionList);
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

    @RequestMapping(value = ServiceApi.HealthSupervision.HealthSupervision, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建卫生监督协管", notes = "绑定卫生监督协管信息")
    public Envelop createhealthSupervision(
            @ApiParam(name = "healthSupervisionJsonData", value = "卫生监督协管信息json串", defaultValue = "")
            @RequestBody String healthSupervisionJsonData) {
        Envelop envelop = new Envelop();
        try {
            HealthSupervisionEntity healthSupervision = toEntity(healthSupervisionJsonData, HealthSupervisionEntity.class);
            healthSupervision.setId(getObjectId(BizObject.User));
            healthSupervision.setCreateDate(new Date());
            healthSupervision = healthsupervisionService.save(healthSupervision);
            envelop.setSuccessFlg(true);
            envelop.setObj(healthSupervision);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthSupervision.HealthSupervision, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改卫生监督协管", notes = "重新绑定卫生监督协管信息")
    public Envelop updatehealthSupervision(
            @ApiParam(name = "healthSupervisionJsonData", value = "卫生监督协管Json对象", defaultValue = "")
            @RequestBody String healthSupervisionJsonData) {
        Envelop envelop = new Envelop();
        try {
            HealthSupervisionEntity healthSupervision = toEntity(healthSupervisionJsonData, HealthSupervisionEntity.class);
            healthsupervisionService.save(healthSupervision);
            envelop.setSuccessFlg(true);
            envelop.setObj(healthSupervision);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthSupervision.HealthSupervisionEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取卫生监督协管信息")
    public Envelop gethealthSupervision(
            @ApiParam(name = "healthSupervisionId", value = "卫生监督协管id", defaultValue = "")
            @PathVariable(value = "healthSupervisionId") String healthSupervisionId) {
        Envelop envelop = new Envelop();
        try {
            HealthSupervisionEntity healthSupervision = healthsupervisionService.getHealthSupervision(healthSupervisionId);
            envelop.setSuccessFlg(true);
            envelop.setObj(healthSupervision);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.HealthSupervision.HealthSupervisionEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除卫生监督协管", notes = "根据id删除卫生监督协管")
    public Envelop deletehealthSupervision(
            @ApiParam(name = "healthSupervisionId", value = "卫生监督协管id", defaultValue = "")
            @PathVariable(value = "healthSupervisionId") String healthSupervisionId) {
        Envelop envelop = new Envelop();
        try {
            healthsupervisionService.delete(healthSupervisionId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
