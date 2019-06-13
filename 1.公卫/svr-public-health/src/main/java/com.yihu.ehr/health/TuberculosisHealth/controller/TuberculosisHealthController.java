package com.yihu.ehr.health.TuberculosisHealth.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.TuberculosisHealth.entity.TuberculosisHealthEntity;
import com.yihu.ehr.health.TuberculosisHealth.service.TuberculosisHealthService;
import com.yihu.ehr.health.FirsthomevisitRecord.entity.FirsthomevisitRecordEntity;
import com.yihu.ehr.health.FirsthomevisitRecord.service.FirsthomevisitRecordService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;

import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.id.BizObject;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.text.DecimalFormat;
@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "TuberculosisHealthEntity", description = "公卫系统-肺结核统计管理", tags = {"肺结核统计管理"})
public class TuberculosisHealthController extends EnvelopRestEndPoint {
    @Autowired
    TuberculosisHealthService tuberculosisHealthService;



    @RequestMapping(value = ServiceApi.TuberculosisHealth.TuberculosisHealth, method = RequestMethod.GET)
    @ApiOperation(value = "获取肺结核统计列表", notes = "根据查询条件获取肺结核统计列表在前端表格展示")
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
        List<TuberculosisHealthEntity> Datas = null;
        long count = 0;
        JSONObject seachobj = JSONObject.fromObject(filters);
        String area = seachobj.get("area").toString();
        area = area.replace("null", "");
        Datas = tuberculosisHealthService.getUser(area);
        if(Datas != null)
            count = Datas.size();
        envelop.setPageSize(size);
        envelop.setTotalCount((int) count);
        envelop.setSuccessFlg(true);
        envelop.setDetailModelList(Datas);
        return envelop;
    }


}
