package com.yihu.ehr.health.SevereMentalFollow.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.SevereMentalFollow.service.*;
import com.yihu.ehr.health.SevereMentalFollow.entity.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = " SevereMentalFollow", description = "公卫系统-严重精神障碍患者随访服务记录表", tags = {"严重精神障碍患者随访服务记录表"})

public class SevereMentalFollowController extends EnvelopRestEndPoint {
    @Autowired
    SevereMentalFollowService severeMentalFollowService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.SevereMentalFollow.check, method = RequestMethod.GET)
    @ApiOperation(value = "判断是否符合儿童条件", notes = "判断是否符合儿童条件")
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
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
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

            filters = stringBuffer.toString();
            List<Perinfor> checkList = new ArrayList<>();
            List<Perinfor> userList = perinforService.search(fields, filters, sorts, page, size);
            if(userList.size()>0)
            {
                Perinfor checkData = userList.get(0);
                if(checkData.getPastHistory().contains("严重精神障碍")){   //满足模块要求
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

    @RequestMapping(value = ServiceApi.SevereMentalFollow.SevereMentalFollow, method = RequestMethod.GET)
    @ApiOperation(value = "获取严重精神障碍患者随访服务记录列表", notes = "根据查询条件获取严重精神障碍患者随访服务记录列表在前端表格展示")
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


            try {
                String peopleId = seachobj.get("peopleId").toString();
                String name = seachobj.get("name").toString();
                String formFollow = seachobj.get("formFollow").toString();
                String lostReason = seachobj.get("lostReason").toString();
                String riskAssess = seachobj.get("riskAssess").toString();
                String ifReferral = seachobj.get("ifReferral").toString();
                String thisClassifi = seachobj.get("thisClassifi").toString();
                String follDate_s = seachobj.get("follDate_s").toString();
                String follDate_e = seachobj.get("follDate_e").toString();

                peopleId = peopleId.replace("null","");
                name = name.replace("null","");
                formFollow = formFollow.replace("null","");
                lostReason = lostReason.replace("null","");
                riskAssess = riskAssess.replace("null","");
                ifReferral = ifReferral.replace("null","");
                thisClassifi = thisClassifi.replace("null","");
                follDate_s = follDate_s.replace("null","");
                follDate_e = follDate_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(formFollow)) {
                    stringBuffer.append("formFollow=" + formFollow + ";");
                }
                if (!StringUtils.isEmpty(lostReason)) {
                    stringBuffer.append("lostReason=" + lostReason + ";");
                }
                if (!StringUtils.isEmpty(riskAssess)) {
                    stringBuffer.append("riskAssess=" + riskAssess + ";");
                }
                if (!StringUtils.isEmpty(ifReferral)) {
                    stringBuffer.append("ifReferral?" + ifReferral + ";");
                }
                if (!StringUtils.isEmpty(thisClassifi)) {
                    stringBuffer.append("thisClassifi=" + thisClassifi + ";");
                }
                if (!StringUtils.isEmpty(follDate_s)) {
                    stringBuffer.append( "follDate>=" + follDate_s + ";");
                }
                if (!StringUtils.isEmpty(follDate_e)) {
                    stringBuffer.append("follDate<=" + follDate_e + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<SevereMentalFollow> userList = severeMentalFollowService.search(fields, filters, sorts, page, size);
            long count = severeMentalFollowService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, severeMentalFollowService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.SevereMentalFollow.SevereMentalFollow, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建严重精神障碍患者随访服务记录", notes = "绑定严重精神障碍患者随访服务记录信息")
    public Envelop create(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalFollow severeMentalFollow = toEntity(userJsonData, SevereMentalFollow.class);
            severeMentalFollow.setId(getObjectId(BizObject.User));
            severeMentalFollow.setWorkId(getObjectId(BizObject.User));
            severeMentalFollow.setCreateDate(new Date());
            severeMentalFollow = severeMentalFollowService.saveUser(severeMentalFollow);
            envelop.setSuccessFlg(true);
            envelop.setObj(severeMentalFollow);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereMentalFollow.SevereMentalFollow, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改严重精神障碍患者随访服务记录", notes = "重新绑定严重精神障碍患者随访服务记录信息")
    public Envelop update(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalFollow severeMentalFollow = toEntity(userJsonData, SevereMentalFollow.class);
            severeMentalFollowService.saveUser(severeMentalFollow);
            envelop.setSuccessFlg(true);
            envelop.setObj(severeMentalFollow);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereMentalFollow.SevereMentalFollowEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取严重精神障碍患者随访服务记录信息")
    public Envelop get(
            @ApiParam(name = "userId", value = "严重精神障碍患者随访服务记录id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalFollow user = severeMentalFollowService.getSevereMentalFollow(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereMentalFollow.SevereMentalFollowEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除严重精神障碍患者随访服务记录", notes = "根据id删除严重精神障碍患者随访服务记录")
    public Envelop delete(
            @ApiParam(name = "userId", value = "严重精神障碍患者随访服务记录id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            severeMentalFollowService.deleteUser(userId);
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
        List<SevereMentalFollow> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = severeMentalFollowService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(SevereMentalFollow item:delateList){
                severeMentalFollowService.deleteUser(item.getId());
            }

        }
    }




}
