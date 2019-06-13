package com.yihu.ehr.health.MaternalManagement.controller;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.entity.FirstPrenatalDiagnosis;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.service.FirstPrenatalDiagnosisService;
import com.yihu.ehr.health.PostpartumVisit.entity.PostpartumVisit;
import com.yihu.ehr.health.PostpartumVisit.service.PostpartumVisitService;

import com.yihu.ehr.health.PostpartumVisitFortytwo.entity.PostpartumVisitFortytwo;
import com.yihu.ehr.health.PostpartumVisitFortytwo.service.PostpartumVisitFortytwoService;

import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.service.ScheduleTwoFivePrenatalDiagnosisService;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity.ScheduleTwoFivePrenatalDiagnosis;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.MaternalManagement.entity.MaternalManagement;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "MaternalManagement", description = "公卫系统-综合查询", tags = {"孕产妇查询"})
public class MaternalManagementEndPoint extends EnvelopRestEndPoint {

    @Autowired
    PerinforService perinforService;
    @Autowired
    FirstPrenatalDiagnosisService firstPrenatalDiagnosisService;
    @Autowired
    PostpartumVisitService postpartumVisitService;
    @Autowired
    PostpartumVisitFortytwoService postpartumVisitFortytwoService;
    @Autowired
    ScheduleTwoFivePrenatalDiagnosisService scheduleTwoFivePrenatalDiagnosisService;

    @RequestMapping(value = ServiceApi.MaternalManagement.MaternalManagement, method = RequestMethod.GET)
    @ApiOperation(value = "获取孕产妇列表", notes = "根据查询条件获取孕产妇列表在前端表格展示")
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
        List<MaternalManagement> userList = new ArrayList<>();
        List<MaternalManagement> noList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer maternalBuffer = new StringBuffer();
            StringBuffer firstBuffer = new StringBuffer();
            StringBuffer secondBuffer = new StringBuffer();
            StringBuffer threeBuffer = new StringBuffer();
            StringBuffer fourBuffer = new StringBuffer();
            StringBuffer handleBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            try {
                Date dNow = new Date( );
                SimpleDateFormat  ft  = new SimpleDateFormat("yyyy-MM-dd");
                String nowDateString = ft.format(dNow);
                Date nowDate = ft.parse(nowDateString);
                String area = seachobj.get("area").toString();
                String name = seachobj.get("name").toString();
                String peopleId = seachobj.get("peopleId").toString();
                String responDoctor = seachobj.get("responDoctor").toString();
                String sex = seachobj.get("sex").toString();
                String age = seachobj.get("age").toString();
                String ethnic = seachobj.get("ethnic").toString();
                String docDate_s = seachobj.get("docDate_s").toString();
                String docDate_e = seachobj.get("docDate_e").toString();
                String phone = seachobj.get("phone").toString();
                String docPeople = seachobj.get("docPeople").toString();
                String state = seachobj.get("state").toString();
                area = area.replace("null","");
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                responDoctor = responDoctor.replace("null","");
                sex = sex.replace("null","");
                ethnic = ethnic.replace("null","");
                docDate_s = docDate_s.replace("null","");
                docDate_e = docDate_e.replace("null","");
                phone = phone.replace("null","");
                docPeople = docPeople.replace("null","");
                state = state.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                stringBuffer.append( "pregnant=" + "是" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress?" + area + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(responDoctor)) {
                    stringBuffer.append("creator?" + responDoctor + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append("sex?" + sex + ";");
                }
                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic?" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(docDate_s)) {
                    stringBuffer.append( "createDate>=" + docDate_s + ";");
                }
                if (!StringUtils.isEmpty(docDate_e)) {
                    stringBuffer.append("createDate<=" + docDate_e+ ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phonerNum?" + phone + ";");
                }
                if (!StringUtils.isEmpty(docPeople)) {
                    stringBuffer.append("creator?" + docPeople + ";");
                }
                if (!StringUtils.isEmpty(state)) {
                    stringBuffer.append( "livestate?" + state + ";");
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
                    Date nowDate = new Date();
                    Integer age = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    MaternalManagement data = new MaternalManagement();
                    data.setArea(item.getHouseAddress());
                    data.setName(item.getName());
                    data.setSex(item.getSex());
                    data.setAge(age);
                    data.setEthnic(item.getEthnic());
                    data.setPeopleId(item.getPeopleId());
                    data.setPhone(item.getPhonerNum());
                    data.setDocDate(item.getCreateDate());
                    data.setDocPeople(item.getCreator());
                    data.setState(item.getLivestate());
                    data.setDeleteState(item.getDeleteState());

                    int  maternalLength = maternalBuffer.length();
                    maternalBuffer.delete(0,maternalLength);

                    int firstLength = firstBuffer.length();
                    firstBuffer.delete(0,firstLength);

                    int secondLength = secondBuffer.length();
                    secondBuffer.delete(0,secondLength);

                    int threeLength = threeBuffer.length();
                    threeBuffer.delete(0,threeLength);

                    int fourLength = fourBuffer.length();
                    fourBuffer.delete(0,fourLength);

                    int handleLength = handleBuffer.length();
                    handleBuffer.delete(0,handleLength);

                    maternalBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    maternalBuffer.append("gestationalWeeks<" +"13"+ ";");
                    maternalBuffer.append( "deleteState=" + "未删" + ";");

                    firstBuffer.append("peopleId=" +item.getPeopleId() + ";");
                    firstBuffer.append( "deleteState=" + "未删" + ";");


                    secondBuffer.append("peopleId=" +item.getPeopleId() + ";");
                    secondBuffer.append( "deleteState=" + "未删" + ";");

                    threeBuffer.append("peopleId=" +item.getPeopleId() + ";");
                    threeBuffer.append( "deleteState=" + "未删" + ";");


                    fourBuffer.append("peopleId=" +item.getPeopleId() + ";");
                    fourBuffer.append( "deleteState=" + "未删" + ";");


                    handleBuffer.append("peopleId=" +item.getPeopleId() + ";");
                    handleBuffer.append("handle=" + "结案" + ";");
                    handleBuffer.append( "deleteState=" + "未删" + ";");

                    String maternalFilters=maternalBuffer.toString();
                    String firstFilters=firstBuffer.toString();
                    String secondFilters=secondBuffer.toString();
                    String thridFilters=threeBuffer.toString();
                    String fithFilters=fourBuffer.toString();
                    String handleFilters=handleBuffer.toString();
                    List<FirstPrenatalDiagnosis> maternalList = firstPrenatalDiagnosisService.search(fields, maternalFilters, sorts, page, size);
                    List<FirstPrenatalDiagnosis> firstList = firstPrenatalDiagnosisService.search(fields, firstFilters, sorts, page, size);
                    List<PostpartumVisit> secondList = postpartumVisitService.search(fields, secondFilters, sorts, page, size);
                    List<PostpartumVisitFortytwo> thirdList = postpartumVisitFortytwoService.search(fields, thridFilters, sorts, page, size);
                    List<ScheduleTwoFivePrenatalDiagnosis> fifthList = scheduleTwoFivePrenatalDiagnosisService.search(fields, fithFilters, sorts, page, size);
                    List<PostpartumVisitFortytwo> handleList = postpartumVisitFortytwoService.search(fields, handleFilters, sorts, page, size);
                    if(maternalList.size()>0){
                        data.setEarlyPregnancy("是");
                        data.setResponDoctor(item.getCreator());
                    }
                    if(firstList.size()>0){
                        data.setLastVisit(firstList.get(firstList.size()-1).getFirstDate());       //添加最近随访时间
                    }
                    if(secondList.size()>0){
                        data.setLastVisit(secondList.get(secondList.size()-1).getDate());       //添加最近随访时间
                    }

                    if(thirdList.size()>0){
                        data.setLastVisit(thirdList.get(thirdList.size()-1).getDate());       //添加最近随访时间
                    }
                    if(fifthList.size()>0){
                        data.setLastVisit(fifthList.get(fifthList.size()-1).getDate());       //添加最近随访时间
                    }
                    data.setNeonatalVisit(firstList.size()+secondList.size()+thirdList.size()+fifthList.size());     //随访记录
                    if(handleList.size()>0){
                        data.setHandle(handleList.get(0).getHandle());
                    }
                    userList.add(data);
                }
            }
            pagedResponse(request, response, perinforService.getCount(filters), page, size);
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
