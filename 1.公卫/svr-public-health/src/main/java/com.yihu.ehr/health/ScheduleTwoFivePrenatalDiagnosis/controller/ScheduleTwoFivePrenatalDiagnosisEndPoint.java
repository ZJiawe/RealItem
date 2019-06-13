package com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.controller;

import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity.ScheduleTwoFivePrenatalDiagnosis;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.service.ScheduleTwoFivePrenatalDiagnosisService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;

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
@Api(value = "ScheduleTwoFivePrenatalDiagnosis", description = "公卫系统-孕产妇管理", tags = {"第2-5次"})
public class ScheduleTwoFivePrenatalDiagnosisEndPoint extends EnvelopRestEndPoint {
    @Autowired
    ScheduleTwoFivePrenatalDiagnosisService scheduleTwoFivePrenatalDiagnosisService;

    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.print, method = RequestMethod.GET)
    @ApiOperation(value = "获取孕产妇列表", notes = "根据查询条件获取孕产妇列表在前端表格展示")
    public Envelop printUsers(
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
            List<ScheduleTwoFivePrenatalDiagnosis> sortList=new ArrayList<>();   //存放排序好的数据
            List<String> sort=new ArrayList<>();   //存放排序条件
            List<String> haveSort=new ArrayList<>();   //存放已经有的数据
            List<String>  noSort=new ArrayList<>();   //存放没有的数据

            sort.add("第二次");   //依次添加排序
            sort.add("第三次");
            sort.add("第四次");
            sort.add("第五次");

            List<ScheduleTwoFivePrenatalDiagnosis> userList = scheduleTwoFivePrenatalDiagnosisService.search(fields, filters, sorts, page, size);   //获取四条数据

            for (ScheduleTwoFivePrenatalDiagnosis item:userList    //获取已有的数据
            ) {
                haveSort.add(item.getNumber());
            }

            for (String item:sort              //获取不足4条数据中不足的数据
            ) {
                if(haveSort.contains(item))
                    continue;
                else
                    noSort.add(item);
            }

            for (String item:noSort               //补全四条数据后还有个问题  ：数据排序可能是乱的
            ) {
                ScheduleTwoFivePrenatalDiagnosis add=new ScheduleTwoFivePrenatalDiagnosis();
                add.setNumber(item);
                userList.add(add);
            }
            for (int i = 0; i <4 ; i++) {
                for (ScheduleTwoFivePrenatalDiagnosis item:userList                //添加排序
                ) {
                    if( item.getNumber().equals("第二次") && i==0)
                        sortList.add(item);
                    if( item.getNumber().equals("第三次") && i==1)
                        sortList.add(item);
                    if( item.getNumber().equals("第四次") && i==2)
                        sortList.add(item);
                    if( item.getNumber().equals("第五次") && i==3)
                        sortList.add(item);
                }
            }
            pagedResponse(request, response, scheduleTwoFivePrenatalDiagnosisService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(sortList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }



    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.ScheduleTwoFivePrenatalDiagnosis, method = RequestMethod.GET)
    @ApiOperation(value = "获取孕产妇列表", notes = "根据查询条件获取孕产妇列表在前端表格展示")
    public Envelop searchUsers(
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
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String peopleId = seachobj.get("peopleId").toString();
            String name = seachobj.get("name").toString();
            String date_s = seachobj.get("date_s").toString();
            String date_e = seachobj.get("date_e").toString();
            String insandDep = seachobj.get("insandDep").toString();
            String doctor = seachobj.get("doctor").toString();
            try {
                peopleId = peopleId.replace("null","");
                name = name.replace("null","");
                date_s = date_s.replace("null","");
                date_e = date_e.replace("null","");
                insandDep = insandDep.replace("null","");
                doctor = doctor.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(date_s)) {
                    stringBuffer.append( "date>" + date_s + ";");
                }
                if (!StringUtils.isEmpty(date_e)) {
                    stringBuffer.append( "date<" + date_e + ";");
                }
                if (!StringUtils.isEmpty(insandDep)) {
                    stringBuffer.append("insandDep?" + insandDep + ";");
                }
                if (!StringUtils.isEmpty(doctor)) {
                    stringBuffer.append("doctor?" + doctor + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<ScheduleTwoFivePrenatalDiagnosis> userList = scheduleTwoFivePrenatalDiagnosisService.search(fields, filters, sorts, page, size);
            long count = scheduleTwoFivePrenatalDiagnosisService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, scheduleTwoFivePrenatalDiagnosisService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.ScheduleTwoFivePrenatalDiagnosis, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建孕产妇", notes = "绑定孕产妇信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "孕产妇信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            ScheduleTwoFivePrenatalDiagnosis maternal = toEntity(userJsonData, ScheduleTwoFivePrenatalDiagnosis.class);
            maternal.setId(getObjectId(BizObject.User));
            maternal.setCreateDate(new Date());
            maternal = scheduleTwoFivePrenatalDiagnosisService.saveUser(maternal);
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.ScheduleTwoFivePrenatalDiagnosis, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改孕产妇", notes = "重新绑定孕产妇信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "孕产妇Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            ScheduleTwoFivePrenatalDiagnosis maternal = toEntity(userJsonData, ScheduleTwoFivePrenatalDiagnosis.class);
            scheduleTwoFivePrenatalDiagnosisService.saveUser(maternal);
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.ScheduleTwoFivePrenatalDiagnosisEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取孕产妇信息")
    public Envelop getUser(
            @ApiParam(name = "maternalId", value = "孕产妇id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            ScheduleTwoFivePrenatalDiagnosis maternal = scheduleTwoFivePrenatalDiagnosisService.getScheduleTwoFivePrenatalDiagnosis(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.ScheduleTwoFivePrenatalDiagnosis.ScheduleTwoFivePrenatalDiagnosisEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除孕产妇", notes = "根据id删除孕产妇")
    public Envelop deleteUser(
            @ApiParam(name = "maternalId", value = "孕产妇id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            scheduleTwoFivePrenatalDiagnosisService.deleteUser(userId);
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
        List<ScheduleTwoFivePrenatalDiagnosis> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = scheduleTwoFivePrenatalDiagnosisService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(ScheduleTwoFivePrenatalDiagnosis item:delateList){
                scheduleTwoFivePrenatalDiagnosisService.deleteUser(item.getId());
            }

        }
    }

}

