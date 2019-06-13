package com.yihu.ehr.health.SevereHealthPlanFollow.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.SevereHealthPlanFollow.entity.SevereHealthPlanFollow;
import com.yihu.ehr.health.SevereHealthPlanFollow.service.SevereHealthPlanFollowService;
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
@Api(value = "SevereHealthPlanFollow", description = "公卫系统-个人资料管理", tags = {"个人资料管理"})
public class SevereHealthPlanFollowController extends EnvelopRestEndPoint {
    @Autowired
    SevereHealthPlanFollowService severeHealthPlanFollowService;

    @RequestMapping(value = ServiceApi.SevereHealthPlanFollow.SevereHealthPlanFollow, method = RequestMethod.GET)
    @ApiOperation(value = "获取健教计划列表", notes = "根据查询条件获取健教计划列表在前端表格展示")
    public Envelop search(
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
            String name = seachobj.get("name").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String people = seachobj.get("people").toString();
            String creditId = seachobj.get("creditId").toString();
            String phone = seachobj.get("phone").toString();
            String panPeople = seachobj.get("panPeople").toString();
            String healthGoal = seachobj.get("healthGoal").toString();
            String place = seachobj.get("place").toString();
            String healthTime_s = seachobj.get("healthTime_s").toString();
            String healthTime_e = seachobj.get("healthTime_e").toString();

            try {
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                people = people.replace("null","");
                creditId = creditId.replace("null","");
                phone = phone.replace("null","");
                panPeople = panPeople.replace("null","");
                healthGoal = healthGoal.replace("null","");
                place = place.replace("null","");
                healthTime_s = healthTime_s.replace("null","");
                healthTime_e = healthTime_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");
                if (!StringUtils.isEmpty(name)) {
                   stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(people)) {
                    stringBuffer.append("people?" + people + ";");
                }
                if (!StringUtils.isEmpty(creditId)) {
                    stringBuffer.append("creditId?" + creditId + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phone?" + phone + ";");
                }
                if (!StringUtils.isEmpty(panPeople)) {
                    stringBuffer.append("panPeople?" + panPeople + ";");
                }
                if (!StringUtils.isEmpty(healthGoal)) {
                    stringBuffer.append("healthGoal?" + healthGoal + ";");
                }
                if (!StringUtils.isEmpty(place)) {
                    stringBuffer.append("place?" + place + ";");
                }
                if (!StringUtils.isEmpty(healthTime_s)) {
                    stringBuffer.append( "healthTime>" +(healthTime_s) + ";");
                }
                if (!StringUtils.isEmpty(healthTime_e)) {
                    stringBuffer.append("healthTime<" +(healthTime_e) + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<SevereHealthPlanFollow> userList = severeHealthPlanFollowService.search(fields, filters, sorts, page, size);
            long count = severeHealthPlanFollowService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, severeHealthPlanFollowService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.SevereHealthPlanFollow.SevereHealthPlanFollow, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建健教计划", notes = "绑定健教计划信息")
    public Envelop create(
            @ApiParam(name = "userJsonData", value = "健教计划信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereHealthPlanFollow user = toEntity(userJsonData, SevereHealthPlanFollow.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = severeHealthPlanFollowService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHealthPlanFollow.SevereHealthPlanFollow, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改健教计划", notes = "重新绑定健教计划信息")
    public Envelop update(
            @ApiParam(name = "userJsonData", value = "健教计划Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereHealthPlanFollow user = toEntity(userJsonData, SevereHealthPlanFollow.class);
            severeHealthPlanFollowService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHealthPlanFollow.SevereHealthPlanFollowEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取健教计划信息")
    public Envelop get(
            @ApiParam(name = "userId", value = "健教计划id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            SevereHealthPlanFollow user = severeHealthPlanFollowService.getSevereHealthPlanFollow(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHealthPlanFollow.SevereHealthPlanFollowEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除健教计划", notes = "根据id删除健教计划")
    public Envelop delete(
            @ApiParam(name = "userId", value = "健教计划id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            severeHealthPlanFollowService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
