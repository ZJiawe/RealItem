package com.yihu.ehr.health.SevereMentalSupplement.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.SevereMentalSupplement.service.*;
import com.yihu.ehr.health.SevereMentalSupplement.entity.*;
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
@Api(value = "SevereMentalSupplement", description = "公卫系统-严重精神障碍患者个人信息补充表", tags = {"严重精神障碍患者个人信息补充表"})
public class SevereMentalSupplementController extends EnvelopRestEndPoint {
    @Autowired
    SevereMentalSupplementService severeMentalSupplementService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.SevereMentalSupplement.check, method = RequestMethod.GET)
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

//            Date dNow = new Date( );
//            SimpleDateFormat  ft  = new SimpleDateFormat("yyyy-MM-dd");
//            String nowDateString = ft.format(dNow);
//            Date nowDate = ft.parse(nowDateString);
//            GregorianCalendar gc =new GregorianCalendar();
//            gc.setTime(nowDate);
//            gc.add(1,-6);         //获取六年前日期
//            gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
//            Date childDate = gc.getTime();

            filters = stringBuffer.toString();
            List<Perinfor> checkList = new ArrayList<>();
            List<Perinfor> userList = perinforService.search(fields, filters, sorts, page, size);
            if(userList.size()>0)
            {
                Perinfor checkData = userList.get(0);
                if(checkData.getPastHistory().contains("精神")){   //满足模块要求
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





    @RequestMapping(value = ServiceApi.SevereMentalSupplement.SevereMentalSupplement, method = RequestMethod.GET)
    @ApiOperation(value = "获取严重精神障碍患者个人信息补充列表", notes = "根据查询条件获取严重精神障碍患者个人信息补充列表在前端表格展示")
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
                String guardianName = seachobj.get("guardianName").toString();
                String commitPersonName = seachobj.get("commitPersonName").toString();
                String accountType = seachobj.get("accountType").toString();
                String firstOnsetTime_s = seachobj.get("firstOnsetTime_s").toString();
                String firstOnsetTime_e = seachobj.get("firstOnsetTime_e").toString();

                peopleId = peopleId.replace("null","");
                name = name.replace("null","");
                guardianName = guardianName.replace("null","");
                commitPersonName = commitPersonName.replace("null","");
                accountType = accountType.replace("null","");
                firstOnsetTime_s = firstOnsetTime_s.replace("null","");
                firstOnsetTime_e = firstOnsetTime_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(guardianName)) {
                    stringBuffer.append("guardianName?" + guardianName + ";");
                }
                if (!StringUtils.isEmpty(commitPersonName)) {
                    stringBuffer.append( "commitPersonName?" + commitPersonName + ";");
                }
                if (!StringUtils.isEmpty(accountType)) {
                    stringBuffer.append( "accountType?" + accountType + ";");
                }
                if (!StringUtils.isEmpty(firstOnsetTime_s)) {
                    stringBuffer.append( "firstOnsetTime>=" + firstOnsetTime_s + ";");
                }
                if (!StringUtils.isEmpty(firstOnsetTime_e)) {
                    stringBuffer.append("firstOnsetTime<=" + firstOnsetTime_e + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<SevereMentalSupplement> userList = severeMentalSupplementService.search(fields, filters, sorts, page, size);
            long count = severeMentalSupplementService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, severeMentalSupplementService.getCount(filters), page, size);
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


    @RequestMapping(value = ServiceApi.SevereMentalSupplement.SevereMentalSupplement, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建严重精神障碍患者个人信息补充", notes = "绑定严重精神障碍患者个人信息补充信息")
    public Envelop create(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalSupplement severeMentalSupplement = toEntity(userJsonData, SevereMentalSupplement.class);
            severeMentalSupplement.setId(getObjectId(BizObject.User));
            severeMentalSupplement.setWorkId(getObjectId(BizObject.User));
            severeMentalSupplement.setCreateDate(new Date());

            SevereMentalSupplement emptyEntity = null;

            severeMentalSupplement = severeMentalSupplementService.saveUser(emptyEntity,severeMentalSupplement);
            envelop.setSuccessFlg(true);
            envelop.setObj(severeMentalSupplement);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


    @RequestMapping(value = ServiceApi.SevereMentalSupplement.SevereMentalSupplement, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改严重精神障碍患者个人信息补充", notes = "重新绑定严重精神障碍患者个人信息补充信息")
    public Envelop update(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalSupplement severeMentalSupplement = toEntity(userJsonData, SevereMentalSupplement.class);
            SevereMentalSupplement beforeUser = severeMentalSupplementService.getSevereMentalSupplement(severeMentalSupplement.getId());
            severeMentalSupplementService.saveUser(beforeUser,severeMentalSupplement);
            envelop.setSuccessFlg(true);
            envelop.setObj(severeMentalSupplement);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


    @RequestMapping(value = ServiceApi.SevereMentalSupplement.SevereMentalSupplementEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取严重精神障碍患者个人信息补充信息")
    public Envelop get(
            @ApiParam(name = "userId", value = "严重精神障碍患者个人信息补充信息id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            SevereMentalSupplement severeMentalSupplement = severeMentalSupplementService.getSevereMentalSupplement(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(severeMentalSupplement);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereMentalSupplement.SevereMentalSupplementEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除严重精神障碍患者个人信息补充", notes = "根据id删除严重精神障碍患者个人信息补充")
    public Envelop delete(
            @ApiParam(name = "userId", value = "严重精神障碍患者个人信息补充信息id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            severeMentalSupplementService.deleteUser(userId);
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
        List<SevereMentalSupplement> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = severeMentalSupplementService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(SevereMentalSupplement item:delateList){
                severeMentalSupplementService.deleteUser(item.getId());
            }

        }
    }




}
