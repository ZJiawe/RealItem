package com.yihu.ehr.health.ChildrenStatistic.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ChildrenStatistic.entity.ChildrenStatistics;
import com.yihu.ehr.health.ChildrenStatistic.service.ChildrenStatisticesService;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "ChildrenStatistics", description = "公卫系统-儿童统计管理", tags = {"儿童统计管理"})
public class ChildrenStatisticEndPoint extends EnvelopRestEndPoint {
    @Autowired
    ChildrenStatisticesService childrenStatisticesService;

    @RequestMapping(value = ServiceApi.ChildrenStatistics.ChildrenStatistics, method = RequestMethod.GET)
    @ApiOperation(value = "获取儿童统计列表", notes = "根据查询条件获取儿童统计列表在前端表格展示")
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
        List<ChildrenStatistics> Datas = null;
        long count = 0;
        JSONObject seachobj = JSONObject.fromObject(filters);
        String area = seachobj.get("area").toString();
        area = area.replace("null", "");
        Datas = childrenStatisticesService.getUser(area);
        if(Datas != null)
            count = Datas.size();
        envelop.setPageSize(size);
        envelop.setTotalCount((int) count);
        envelop.setSuccessFlg(true);
        envelop.setDetailModelList(Datas);
        return envelop;
    }
}
