package com.yihu.ehr.health.TuberculosisPatient.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.TuberculosisPatient.entity.*;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.FirsthomevisitRecord.service.*;
import com.yihu.ehr.health.FirsthomevisitRecord.entity.*;
import com.yihu.ehr.health.Followupservicerecord.service.FollowUpServiceRecordService;
import com.yihu.ehr.health.Followupservicerecord.entity.FollowUpServiceRecordEntity;
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
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "TuberculosisPatient", description = "公卫系统-肺结核患者查询", tags = {"肺结核患者查询"})
public class TuberculosisPatientController extends EnvelopRestEndPoint {


    @Autowired
    PerinforService perinforService;
    @Autowired
    FirsthomevisitRecordService firsthomevisitRecordService;
    @Autowired
    FollowUpServiceRecordService followUpServiceRecordService;


    @RequestMapping(value = ServiceApi.TuberculosisPatient.TuberculosisPatient, method = RequestMethod.GET)
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
        List<TuberEntity> userList = new ArrayList<>();
        List<TuberEntity> noList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer mentalStringBuffer = new StringBuffer();
            StringBuffer followStringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());


            String inputDate_s = seachobj.get("inputDate_s").toString();
            String inputDate_e = seachobj.get("inputDate_e").toString();

            String inputPerson = seachobj.get("inputPerson").toString();

            String area = seachobj.get("area").toString();
            String name = seachobj.get("name").toString();

            String ethnic = seachobj.get("ethnic").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String phone = seachobj.get("phone").toString();
            String responDoctor = seachobj.get("responDoctor").toString();

            String lifeState = seachobj.get("lifeState").toString();


            try {

                inputDate_s = inputDate_s.replace("null","");
                inputDate_e = inputDate_e.replace("null","");
                inputPerson = inputPerson.replace("null","");
                area = area.replace("null","");
                name = name.replace("null","");

                ethnic = ethnic.replace("null","");
                peopleId = peopleId.replace("null","");
                phone = phone.replace("null","");
                responDoctor = responDoctor.replace("null","");

                lifeState = lifeState.replace("null","");
                stringBuffer.append( "pastHistory?" + "结核病" + ";");    //筛选肺结核
                stringBuffer.append( "deleteState=" + "未删" + ";");    //伪删除需要的

                if (!StringUtils.isEmpty(inputPerson)) {
                    stringBuffer.append("creator=" + inputPerson + ";");
                }
                if (!StringUtils.isEmpty(inputDate_s)) {
                    stringBuffer.append( "createDate>" + inputDate_s + ";");
                }
                if (!StringUtils.isEmpty(inputDate_e)) {
                    stringBuffer.append("createDate<" + inputDate_e+ ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress=" + area + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name=" + name + ";");
                }



                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic=" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phonerNum=" + phone + ";");
                }
                if (!StringUtils.isEmpty(responDoctor)) {
                    stringBuffer.append("responDoctor=" + responDoctor + ";");
                }

                if (!StringUtils.isEmpty(lifeState)) {
                    stringBuffer.append("livestate=" + lifeState + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }


            filters = stringBuffer.toString();
            List<Perinfor> personList = perinforService.search(fields, filters, sorts, page, size);

            if( personList !=null  &&  personList.size()>0) {
                for (Perinfor item : personList
                ) {
                    //清口筛选条件
                    int  mentalLength = mentalStringBuffer.length();
                    mentalStringBuffer.delete(0,mentalLength);
                    int followLength =followStringBuffer.length();
                    followStringBuffer.delete(0,followLength);


                    mentalStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    mentalStringBuffer.append( "deleteState=" + "未删" + ";");
                    String mentalFilters=mentalStringBuffer.toString();
                    followStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    followStringBuffer.append( "deleteState=" + "未删" + ";");
                    String followFilters=followStringBuffer.toString();
                    List<FirsthomevisitRecordEntity> mentalList = firsthomevisitRecordService.search(fields, mentalFilters, sorts, page, size);
                    List<FollowUpServiceRecordEntity> followList = followUpServiceRecordService.search(fields, followFilters, sorts, page, size);
                    Date nowDate = new Date();
                    Integer diff = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    TuberEntity data = new TuberEntity();
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setName(item.getName());
                        data.setSex(item.getSex());                       //性别
                        data.setAge(Integer.toString(diff));             //年龄我是字符串形 Integer型去掉转换
                        data.setPeopleId(item.getPeopleId());               //身份证
                        data.setPhone(item.getPhonerNum());                  //联系方式
                        data.setEthnic(item.getEthnic());                    //民族
                        data.setInputPerson(item.getCreator());//建档
                        data.setInputDate(item.getCreateDate());
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setLifeState(item.getLivestate());             //添加生存状态
                    data.setResponDoctor(item.getResponDoctor());//添加责任医生
                    data.setfNumber(mentalList.size()+followList.size());     //随访记录



                    if(mentalList.size()>0){
                        data.setRecentFollow(mentalList.get(mentalList.size()-1).getFollowTime());       //添加最近随访时间
                        data.setResponDoctor(mentalList.get(mentalList.size()-1).getResponDoctor());

                    }
                    if(followList.size()>0){
                        data.setRecentFollow(followList.get(followList.size()-1).getFlowUpTime());       //添加最近随访时间

                    }
                    userList.add(data);

                }
            }

            pagedResponse(request, response, perinforService.getCount(filters), page, size);
            long count = userList.size();
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






    public Integer getYear(Date date )     //获取时间年份
    {
        SimpleDateFormat ft  = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = ft.format(date);
        String test = dateString.substring(0,4);
        Integer year = Integer.parseInt(test);
        return year;
    }


    //yyyy-MM-dd HH:mm:ss 转换为yyyy-MM-dd'T'HH:mm:ss'Z 格式
    public String changeToUtc(String datetime) throws Exception{
        Date date = DateTimeUtil.simpleDateTimeParse(datetime);
        return DateTimeUtil.utcDateTimeFormat(date);
    }







}
