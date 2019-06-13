package com.yihu.ehr.health.BasicFamilyInfor.controller;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.BasicFamilyInfor.service.SearchRdoctorService;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.BasicFamilyInfor.entity.SearchRdoctor;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.http.HttpResponse;
import com.yihu.ehr.util.http.HttpUtils;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "SearchRdoctor", description = "公卫系统-家庭档案查找责任医生", tags = {"家庭档案查找责任医生"})
public class SearchRdoctorEndPoint extends EnvelopRestEndPoint {
    @Autowired
    SearchRdoctorService searchRdoctorService;
    @Autowired
    PerinforService perinforService;
    @Value("${info.app.clientId}")
    private String clientId;
    @Value("${service-gateway.agZuul}")
    private String agZuul;

    @RequestMapping(value = ServiceApi.SearchRdoctor.SearchRdoctor, method = RequestMethod.GET )
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop getUser(
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
            HttpServletRequest request, HttpServletResponse response,String token){
        Envelop envelop = new Envelop();
        Map<String, Object> params = new HashMap<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String orgId = seachobj.get("orgId").toString();
            String searchParm = seachobj.get("searchParm").toString();
            try {

                orgId = orgId.replace("null","");
                searchParm = searchParm.replace("null","");

                if (!StringUtils.isEmpty(orgId)) {
                    stringBuffer.append("orgId?" + orgId + ";");
                }
                if (!StringUtils.isEmpty(searchParm)) {
                    stringBuffer.append("searchParm=" + searchParm + ";");
                }
                params.put("clientId", clientId);
                params.put("accessToken", token);
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        try {
            HttpResponse httpResponse = HttpUtils.doPost(agZuul + "authentication/oauth/validToken", params);
                Map<String, Object> map = objectMapper.readValue(httpResponse.getContent(), Map.class);
                Map<String, Object> param = new HashMap<>();
                param.put("token", token);
                param.put("orgId", 362);
                param.put("Content-Type", "application/json");
                param.put("userId", "0dae00035ae191e185e94c260d1f55ea");
                param.put("searchParm", '%');
                param.put("page", 999);
                param.put("size", 1);
                httpResponse = HttpUtils.doPost(agZuul + "/basic/api/v1.0/orgDeptMember/getOrgDeptMembers", param);
                Map userMap = this.objectMapper.readValue(httpResponse.getContent(), Map.class);
                userMap.put("Content-Type", "application/json");
                userMap.put("userId", "0dae00035ae191e185e94c260d1f55ea");
                userMap.put("token", token);
                userMap.put("orgId", 362);
                userMap.put("searchParm", '%');
                envelop.setObj(userMap);
                envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setErrorMsg("授权验证失败，请联系管理员！");
            envelop.setSuccessFlg(false);
        }

            filters = stringBuffer.toString();
            List<SearchRdoctor> userList = searchRdoctorService.search(fields, filters, sorts, page, size);
            pagedResponse(request, response, searchRdoctorService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(userList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }





}

