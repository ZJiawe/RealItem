package com.yihu.ehr.health.TwoDiabetes.controller;

import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.util.DateTimeUtil;
import  com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.TwoDiabetes.entity.TwoDiabetesEntity;
import com.yihu.ehr.health.TwoDiabetes.service.TwoDiabetesService;

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
@Api(value = "TwoDiabetes", description = "公卫系统-糖尿病患者管理", tags = {"糖尿病患者管理"})
public class TwoDiabetesController extends EnvelopRestEndPoint {
    @Autowired
    TwoDiabetesService twoDiabetesService;
    @Autowired
    PerinforService perinforService;

	    @RequestMapping(value = ServiceApi.TwoDiabetes.Check, method = RequestMethod.GET)
    @ApiOperation(value = "判断是否符合儿童条件", notes = "判断是否符合儿童条件")
    public Envelop checkData(
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
                if(checkData.getPastHistory().contains("糖尿病")){   //满足模块要求
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


    @RequestMapping(value = ServiceApi.TwoDiabetes.TwoDiabetes, method = RequestMethod.GET)
    @ApiOperation(value = "获取2型糖尿病患者列表", notes = "根据查询条件获取2型糖尿病患者列表在前端表格展示")
    public Envelop searchData(
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
            String startTime = seachobj.get("startTime").toString();
            String endTime = seachobj.get("endTime").toString();
            String name = seachobj.get("name").toString();
            String doctor = seachobj.get("doctor").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String age = seachobj.get("age").toString();
            String liveStatus = seachobj.get("liveStatus").toString();
            String phone = seachobj.get("phone").toString();
            String sex = seachobj.get("sex").toString();
            String ethnic = seachobj.get("ethnic").toString();
            String area = seachobj.get("area").toString();
            String creator = seachobj.get("creator").toString();
            try {
                name = name.replace("null","");
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
               doctor = doctor.replace("null","");
               age = age.replace("null","");
               sex = sex.replace("null","");
                peopleId = peopleId.replace("null","");
                liveStatus = liveStatus.replace("null","");
                phone = phone.replace("null","");
                ethnic = ethnic.replace("null","");
                area = area.replace("null","");
                creator = creator.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(startTime)) {
                    stringBuffer.append( "followDate>" + startTime + ";");
                }
                if (!StringUtils.isEmpty(endTime)) {
                    stringBuffer.append("followDate<" + endTime + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
            if (!StringUtils.isEmpty(doctor)) {
                 stringBuffer.append( "doctor?" + doctor + ";");
              }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append( "peopleId?" + peopleId + ";");
                }
              if (!StringUtils.isEmpty(sex)) {
                   stringBuffer.append( "sex?" + sex + ";");
              }
                if (!StringUtils.isEmpty(liveStatus)) {
                    stringBuffer.append( "liveStatus?" + liveStatus + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append( "phone?" + phone + ";");
                }
                if (!StringUtils.isEmpty(age)) {
                    stringBuffer.append( "age?" + age + ";");
                }
                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append( "ethnic?" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append( "area?" + area + ";");
                }
                if (!StringUtils.isEmpty(creator)) {
                    stringBuffer.append( "creator?" + creator + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<ServiceApi.TwoDiabetes> userList = twoDiabetesService.search(fields, filters, sorts, page, size);
            long count = twoDiabetesService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, twoDiabetesService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.TwoDiabetes.TwoDiabetes, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建2型糖尿病患者", notes = "绑定2型糖尿病患者信息")
    public Envelop createData(
            @ApiParam(name = "userJsonData", value = "2型糖尿病患者信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            TwoDiabetesEntity TwoDiabeter = toEntity(userJsonData, TwoDiabetesEntity.class);
            TwoDiabeter.setId(getObjectId(BizObject.User));
            TwoDiabeter.setCreateDate(new Date());
            TwoDiabeter = twoDiabetesService.saveUser(TwoDiabeter);
            envelop.setSuccessFlg(true);
            envelop.setObj(TwoDiabeter);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwoDiabetes.TwoDiabetes, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改2型糖尿病患者信息", notes = "重新绑定2型糖尿病患者信息")
    public Envelop updateData(
            @ApiParam(name = "userJsonData", value = "2型糖尿病患者Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            TwoDiabetesEntity TwoDiabeter = toEntity(userJsonData, TwoDiabetesEntity.class);
            twoDiabetesService.saveUser(TwoDiabeter);
            envelop.setSuccessFlg(true);
            envelop.setObj(TwoDiabeter);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwoDiabetes.TwoDiabetesEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取2型糖尿病患者信息")
    public Envelop getList(
            @ApiParam(name = "TwoDiabeterId", value = "2型糖尿病患者id", defaultValue = "")
            @PathVariable(value = "TwoDiabeterId") String userId) {
        Envelop envelop = new Envelop();
        try {
            TwoDiabetesEntity TwoDiabeter = twoDiabetesService.getTwoDiabetesEntity(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(TwoDiabeter);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
    @RequestMapping(value = ServiceApi.TwoDiabetes.printData, method = RequestMethod.GET)
    @ApiOperation(value = "获取打印数据", notes = "根据身份证号获取2型糖尿病患者个人信息")
    public Envelop printData(
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
            List<TwoDiabetesEntity> sortList = new ArrayList<>(); //获取排序后的List
            List<String> sort = new ArrayList<>(); //存放排序条件
            List<String> haveSort = new ArrayList<>(); //存放已经有的数据
            List<String> noSort = new ArrayList<>(); //存放未有的数据

            sort.add("第一列");   //依次添加排序
            sort.add("第二列");
            sort.add("第三列");
            sort.add("第四列");

            List<TwoDiabetesEntity> userList = twoDiabetesService.search(fields, filters, sorts, page, size);   //获取四条数据

            for (TwoDiabetesEntity item:userList    //获取已有的数据
            ) {
                haveSort.add(item.getFollowCount());
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
                TwoDiabetesEntity add=new TwoDiabetesEntity();
                add.setFollowCount(item);
                userList.add(add);
            }

            for (int i = 0; i <4 ; i++) {
                for (TwoDiabetesEntity item:userList                //添加排序
                ) {
                    if( item.getFollowCount().equals("第一列") && i==0)
                        sortList.add(item);
                    if( item.getFollowCount().equals("第二列") && i==1)
                        sortList.add(item);
                    if( item.getFollowCount().equals("第三列") && i==2)
                        sortList.add(item);
                    if( item.getFollowCount().equals("第四列") && i==3)
                        sortList.add(item);
                }
            }

            pagedResponse(request, response, twoDiabetesService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(sortList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


    @RequestMapping(value = ServiceApi.TwoDiabetes.TwoDiabetesEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除2型糖尿病患者", notes = "根据id删除2型糖尿病患者")
    public Envelop deleteData(
            @ApiParam(name = "userId", value = "2型糖尿病患者id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            twoDiabetesService.deleteUser(userId);
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
        List<TwoDiabetesEntity> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = twoDiabetesService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(TwoDiabetesEntity item:delateList){
                twoDiabetesService.deleteUser(item.getId());
            }

        }
    }

}
