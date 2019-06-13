package com.yihu.ehr.health.NewbornVisit.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.NewbornVisit.entity.NewbornVisit;
import com.yihu.ehr.health.NewbornVisit.service.NewbornVisitService;
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
@Api(value = "NewbornVisit", description = "公卫系统-家庭随访管理", tags = {"家庭随访管理"})
public class NewbornVisitEndPoint extends EnvelopRestEndPoint {
    @Autowired
    NewbornVisitService newbornVisitService;

    @RequestMapping(value = ServiceApi.NewbornVisit.NewbornVisit, method = RequestMethod.GET)
    @ApiOperation(value = "获取家庭随访列表", notes = "根据查询条件获取家庭随访列表在前端表格展示")
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
            String startTime = seachobj.get("startTime").toString();
            String endTime = seachobj.get("endTime").toString();
            String name = seachobj.get("name").toString();
            String area = seachobj.get("area").toString();
            try {
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
                name = name.replace("null","");
                area = area.replace("null","");
                String peopleId = seachobj.get("peopleId").toString();
                peopleId = peopleId.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(startTime)) {
                    stringBuffer.append( "followDate>=" + startTime + ";");
                }
                if (!StringUtils.isEmpty(endTime)) {
                    stringBuffer.append("followDate<=" + endTime + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("addr?" + area + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<NewbornVisit> userList = newbornVisitService.search(fields, filters, sorts, page, size);
            pagedResponse(request, response, newbornVisitService.getCount(filters), page, size);
            long count = newbornVisitService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
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

    @RequestMapping(value = ServiceApi.NewbornVisit.NewbornVisit, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建家庭随访", notes = "绑定家庭随访信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "家庭随访信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            NewbornVisit user = toEntity(userJsonData, NewbornVisit.class);
            user.setId(getObjectId(BizObject.User));
            user.setNumber(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = newbornVisitService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.NewbornVisit.NewbornVisit, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改家庭随访", notes = "重新绑定家庭随访信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "家庭随访Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            NewbornVisit user = toEntity(userJsonData, NewbornVisit.class);
            newbornVisitService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.NewbornVisit.NewbornVisitEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取家庭随访信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "家庭随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            NewbornVisit user = newbornVisitService.getNewbornVisit(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.NewbornVisit.NewbornVisitEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除家庭随访", notes = "根据id删除家庭随访")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "家庭随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            newbornVisitService.deleteUser(userId);
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
        List<NewbornVisit> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = newbornVisitService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(NewbornVisit item:delateList){
                newbornVisitService.deleteUser(item.getId());
            }
        }
    }


}
