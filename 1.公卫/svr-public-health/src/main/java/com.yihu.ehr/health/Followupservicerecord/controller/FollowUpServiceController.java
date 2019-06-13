package com.yihu.ehr.health.Followupservicerecord.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;

import com.yihu.ehr.health.Followupservicerecord.entity.FollowUpServiceRecordEntity;
import com.yihu.ehr.health.Followupservicerecord.service.FollowUpServiceRecordService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
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
@Api(value = "FollowUpServiceRecordEntity", description = "公卫系统-肺结核随访表资料管理", tags = {"肺结核随访表管理"})
public class FollowUpServiceController extends EnvelopRestEndPoint {
    @Autowired
    FollowUpServiceRecordService FollowUpServiceRecordService;

    @RequestMapping(value = ServiceApi.Followupservicerecord.Followupservicerecord, method = RequestMethod.GET)
    @ApiOperation(value = "获取肺结核随访列表", notes = "根据查询条件获取肺结核随访列表在前端表格展示")
    public Envelop searchTuberFollows(
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

            try {

                String name = seachobj.get("name").toString();
                String peopleId = seachobj.get("peopleId").toString();
                String flowUpTime_s = seachobj.get("flowUpTime_s").toString();
                String flowUpTime_e = seachobj.get("flowUpTime_e").toString();
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
               flowUpTime_s =flowUpTime_s.replace("null","");
               flowUpTime_e =flowUpTime_e.replace("null","");


                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }

                if (!StringUtils.isEmpty(flowUpTime_s)) {
                    stringBuffer.append( "flowUpTime>" +flowUpTime_s + ";");
                }

                if (!StringUtils.isEmpty(flowUpTime_e)) {
                    stringBuffer.append("flowUpTime<" +flowUpTime_e+ ";");
                }

                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }


            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<FollowUpServiceRecordEntity> userList = FollowUpServiceRecordService.search(fields, filters, sorts, page, size);
            long count = FollowUpServiceRecordService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, FollowUpServiceRecordService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.Followupservicerecord.Followupservicerecord, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建肺结核随访", notes = "绑定肺结核随访信息")
    public Envelop createTuberFollow(
            @ApiParam(name = "userJsonData", value = "肺结核随访信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            FollowUpServiceRecordEntity user = toEntity(userJsonData, FollowUpServiceRecordEntity.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = FollowUpServiceRecordService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Followupservicerecord.Followupservicerecord, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改肺结核随访", notes = "重新绑定肺结核随访信息")
    public Envelop updateTuberFollow(
            @ApiParam(name = "userJsonData", value = "肺结核随访Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            FollowUpServiceRecordEntity user = toEntity(userJsonData, FollowUpServiceRecordEntity.class);
            FollowUpServiceRecordService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Followupservicerecord.FollowupservicerecordEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取肺结核随访信息")
    public Envelop getTuberFollow(
            @ApiParam(name = "userId", value = "肺结核随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            FollowUpServiceRecordEntity user = FollowUpServiceRecordService.getFollowUpServiceRecordEntity(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Followupservicerecord.FollowupservicerecordEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除肺结核随访", notes = "根据id删除肺结核随访")
    public Envelop deleteTuberFollow(
            @ApiParam(name = "TuberFollowId", value = "肺结核随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            FollowUpServiceRecordService.deleteUser(userId);
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
        List<FollowUpServiceRecordEntity> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = FollowUpServiceRecordService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(delateList.size()>0){
            for(FollowUpServiceRecordEntity item:delateList){
                FollowUpServiceRecordService.deleteUser(item.getId());
            }

        }
    }
}
