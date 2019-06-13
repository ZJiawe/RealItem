package com.yihu.ehr.health.SelfcareAbility.controller;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.SelfcareAbility.service.*;
import com.yihu.ehr.health.SelfcareAbility.entity.*;
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
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = " SelfcareAbility", description = "公卫系统-老年人生活自理评估表", tags = {"老年人生活自理评估表"})

public class SelfcareAbilityController extends EnvelopRestEndPoint {
    @Autowired
    SelfcareAbilityService selfcareAbilityService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.SelfcareAbility.SelfcareAbility, method = RequestMethod.GET)
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
            String formDate_s = seachobj.get("formDate_s").toString();
            String formDate_e = seachobj.get("formDate_e").toString();

            try {
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                formDate_s = formDate_s.replace("null","");
                formDate_e = formDate_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(formDate_s)) {
                    stringBuffer.append( "formDate>=" + formDate_s + ";");
                }
                if (!StringUtils.isEmpty(formDate_e)) {
                    stringBuffer.append("formDate<=" + formDate_e + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<SelfcareAbility> userList = selfcareAbilityService.search(fields, filters, sorts, page, size);
            long count = selfcareAbilityService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, selfcareAbilityService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.SelfcareAbility.SelfcareAbility, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SelfcareAbility user = toEntity(userJsonData, SelfcareAbility.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = selfcareAbilityService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SelfcareAbility.SelfcareAbility, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SelfcareAbility user = toEntity(userJsonData, SelfcareAbility.class);
            selfcareAbilityService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SelfcareAbility.SelfcareAbilityEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            SelfcareAbility user = selfcareAbilityService.getSelfcareAbility(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SelfcareAbility.SelfcareAbilityEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            selfcareAbilityService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SelfcareAbility.Check, method = RequestMethod.GET)
    @ApiOperation(value = "判断是否符合老人条件", notes = "判断是否符合老人条件")
    public Envelop checkDatas(
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
            net.sf.json.JSONObject seachobj = net.sf.json.JSONObject.fromObject(filters.toString());
            String peopleId = seachobj.get("peopleId").toString();
            try {
                peopleId = peopleId.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            Date dNow = new Date( );
            SimpleDateFormat ft  = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateString = ft.format(dNow);
            Date nowDate = ft.parse(nowDateString);
            GregorianCalendar gc =new GregorianCalendar();
            gc.setTime(nowDate);
            gc.add(1,-65);         //获取六年前日期
            gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
            Date childDate = gc.getTime();

            filters = stringBuffer.toString();
            List<Perinfor> checkList = new ArrayList<>();
            List<Perinfor> userList = perinforService.search(fields, filters, sorts, page, size);
            if(userList.size()>0)
            {
                Perinfor checkData = userList.get(0);
                if(checkData.getBirth().before(childDate)){   //满足模块要求
                    checkList.add(checkData);
                }
            }
            long count = perinforService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, perinforService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(checkList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
    public void totalDelate (Perinfor entity){
        StringBuffer delateBuffer = new StringBuffer();
        List<SelfcareAbility> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = selfcareAbilityService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(SelfcareAbility item:delateList){
                selfcareAbilityService.deleteUser(item.getId());
            }
        }
    }

}
