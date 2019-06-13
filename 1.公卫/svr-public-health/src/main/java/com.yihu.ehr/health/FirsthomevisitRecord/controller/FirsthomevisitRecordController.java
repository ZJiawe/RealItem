package com.yihu.ehr.health.FirsthomevisitRecord.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.FirsthomevisitRecord.entity.FirsthomevisitRecordEntity;
import com.yihu.ehr.health.FirsthomevisitRecord.service.FirsthomevisitRecordService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.TuberculosisHealth.entity.TuberculosisHealthEntity;
import com.yihu.ehr.health.TuberculosisHealth.service.TuberculosisHealthService;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "FirsthomevisitRecordEntity", description = "公卫系统-肺结核资料管理", tags = {"肺结核资料管理"})
public class FirsthomevisitRecordController extends EnvelopRestEndPoint {
    @Autowired
    FirsthomevisitRecordService FirsthomevisitRecordService;
    @Autowired
    PerinforService perinforService;
    @Autowired
    TuberculosisHealthService tuberculosisHealthService;


    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.check, method = RequestMethod.GET)
    @ApiOperation(value = "判断是否符合肺结核条件", notes = "判断是否符合肺结核条件")
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
                if(checkData.getPastHistory().contains("结核病")){   //满足模块要求
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


    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.FirsthomevisitRecord, method = RequestMethod.GET)
    @ApiOperation(value = "获取肺结核列表", notes = "根据查询条件获取肺结核列表在前端表格展示")
    public Envelop searchTubers(
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
                String followTime_s = seachobj.get("followTime_s").toString();
                String followTime_e = seachobj.get("followTime_e").toString();
                String inputDate_s = seachobj.get("inputDate_s").toString();
                String inputDate_e = seachobj.get("inputDate_e").toString();
                String area = seachobj.get("area").toString();
                String ethnic = seachobj.get("ethnic").toString();
                String phone = seachobj.get("phone").toString();
                String responDoctor = seachobj.get("responDoctor").toString();
                String inputPerson = seachobj.get("inputPerson").toString();
                String lifeState = seachobj.get("lifeState").toString();
                String age = seachobj.get("age").toString();

                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                followTime_s = followTime_s.replace("null","");
                followTime_e = followTime_e.replace("null","");
                inputDate_s = inputDate_s.replace("null","");
                inputDate_e = inputDate_e.replace("null","");
                area = area.replace("null","");
                ethnic = ethnic.replace("null","");
                phone = phone.replace("null","");
                responDoctor = responDoctor.replace("null","");
                inputPerson = inputPerson.replace("null","");
                lifeState = lifeState.replace("null","");
                age = age.replace("null","");

                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }

                if (!StringUtils.isEmpty(followTime_s)) {
                    stringBuffer.append( "followTime>" + followTime_s + ";");
                }

                if (!StringUtils.isEmpty(followTime_e)) {
                    stringBuffer.append("followTime<" + followTime_e+ ";");
                }

                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }

                if (!StringUtils.isEmpty(inputDate_s)) {
                    stringBuffer.append( "inputDate>" + inputDate_s + ";");
                }
                if (!StringUtils.isEmpty(inputDate_e)) {
                    stringBuffer.append("inputDate<" + inputDate_e+ ";");
                }

                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic?" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phone?" + phone + ";");
                }
                if (!StringUtils.isEmpty(responDoctor)) {
                    stringBuffer.append("responDoctor?" + responDoctor + ";");
                }
                if (!StringUtils.isEmpty(inputPerson)) {
                    stringBuffer.append("inputPerson?" + inputPerson + ";");
                }
                if (!StringUtils.isEmpty(lifeState)) {
                    stringBuffer.append("lifeState?" + lifeState + ";");
                }
                if (!StringUtils.isEmpty(age)) {
                    stringBuffer.append("age=" + age + ";");
                }

                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("area?" + area + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<FirsthomevisitRecordEntity> userList = FirsthomevisitRecordService.search(fields, filters, sorts, page, size);
            long count = FirsthomevisitRecordService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, FirsthomevisitRecordService.getCount(filters), page, size);
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



    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.FirsthomevisitRecord, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建肺结核", notes = "绑定肺结核信息")
    public Envelop createTuber(
            @ApiParam(name = "userJsonData", value = "肺结核信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            FirsthomevisitRecordEntity user = toEntity(userJsonData, FirsthomevisitRecordEntity.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = FirsthomevisitRecordService.saveUser(user);


            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }




    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.FirsthomevisitRecord, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改肺结核", notes = "重新绑定肺结核信息")
    public Envelop updateTuber(
            @ApiParam(name = "userJsonData", value = "肺结核Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            FirsthomevisitRecordEntity user = toEntity(userJsonData, FirsthomevisitRecordEntity.class);
            FirsthomevisitRecordService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.FirsthomevisitRecordEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取肺结核信息")
    public Envelop getTuber(
            @ApiParam(name = "userId", value = "肺结核id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            FirsthomevisitRecordEntity user = FirsthomevisitRecordService.getFirsthomevisitRecordEntity(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.FirsthomevisitRecord.FirsthomevisitRecordEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除肺结核", notes = "根据id删除肺结核")
    public Envelop deleteTuber(
            @ApiParam(name = "tuberId", value = "肺结核id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            FirsthomevisitRecordService.deleteUser(userId);

            FirsthomevisitRecordEntity data = FirsthomevisitRecordService.getFirsthomevisitRecordEntity(userId);


            
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
        List<FirsthomevisitRecordEntity> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = FirsthomevisitRecordService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(delateList.size()>0){
            for(FirsthomevisitRecordEntity item:delateList){
                FirsthomevisitRecordService.deleteUser(item.getId());
            }

        }
    }

}
