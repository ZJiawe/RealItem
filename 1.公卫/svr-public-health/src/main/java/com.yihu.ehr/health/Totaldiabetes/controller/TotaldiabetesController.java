package com.yihu.ehr.health.Totaldiabetes.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Totaldiabetes.entity.Totaldiabetes;
import com.yihu.ehr.health.TwoDiabetes.entity.TwoDiabetesEntity;
import com.yihu.ehr.health.TwoDiabetes.service.TwoDiabetesService;
import com.yihu.ehr.health.Totaldiabetes.service.TotaldiabetesServices;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "Totaldiabetes", description = "公卫系统-糖尿病统计管理", tags = {"糖尿病统计管理"})
public class TotaldiabetesController extends EnvelopRestEndPoint {
    @Autowired
    TotaldiabetesServices totaldiabetesServices;
    @Autowired
    PerinforService perinforService;
    @Autowired
    TwoDiabetesService twoDiabetesService;


    @RequestMapping(value = ServiceApi.Totaldiabetes.Totaldiabetes, method = RequestMethod.GET)
    @ApiOperation(value = "获取糖尿病患者统计列表", notes = "根据查询条件获取糖尿病患者统计列表在前端表格展示")
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
            StringBuffer filterDiabetesBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String area = seachobj.get("area").toString();
            try {
                area = area.replace("null", "");
                if (!StringUtils.isEmpty(area)) {
                    filterDiabetesBuffer.append("area?" + area + ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    filterDiabetesBuffer.append("area?" + area + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = filterDiabetesBuffer.toString();
            List<Totaldiabetes> filterTwoDiabetesList = null;
            long count = 0;
            filterTwoDiabetesList = totaldiabetesServices.getTotaldiabetes(area);
            if(filterTwoDiabetesList != null)
                count = filterTwoDiabetesList.size();
            envelop.setPageSize(size);
            envelop.setTotalCount((int) count);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(filterTwoDiabetesList);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
}




