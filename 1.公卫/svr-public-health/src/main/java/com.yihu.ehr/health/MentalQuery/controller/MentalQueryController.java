package com.yihu.ehr.health.MentalQuery.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.MentalQuery.entity.*;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "MentalQuery", description = "公卫系统-严重精神患者查询", tags = {"严重精神患者查询"})
public class MentalQueryController extends EnvelopRestEndPoint {


    @Autowired
    PerinforService perinforService;
    @Autowired
    SevereMentalFollowService severeMentalFollowService;


    @RequestMapping(value = ServiceApi.MentalQuery.MentalQuery, method = RequestMethod.GET)
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
        List<MentalQuery> userList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer personStringBuffer = new StringBuffer();
            StringBuffer mentalStringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());

            String area = seachobj.get("area").toString();
            String name = seachobj.get("name").toString();
            String sex = seachobj.get("sex").toString();
            String age = seachobj.get("age").toString();
            String age_s = seachobj.get("age_s").toString();
            String age_e = seachobj.get("age_e").toString();
            String ethnic = seachobj.get("ethnic").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String phone = seachobj.get("phone").toString();
            String responseDoctor = seachobj.get("responseDoctor").toString();
            String creator = seachobj.get("creator").toString();
            String id = seachobj.get("id").toString();
            String recentFollow_s = seachobj.get("recentFollow_s").toString();
            String recentFollow_e = seachobj.get("recentFollow_e").toString();
            String livingCondition = seachobj.get("livingCondition").toString();


            try {
                area = area.replace("null","");
                name = name.replace("null","");
                sex = sex.replace("null","");
                age = age.replace("null","");
                age_s = age_s.replace("null","");
                age_e = age_e.replace("null","");
                ethnic = ethnic.replace("null","");
                peopleId = peopleId.replace("null","");
                phone = phone.replace("null","");
                responseDoctor = responseDoctor.replace("null","");
                creator = creator.replace("null","");
                id = id.replace("null","");
                recentFollow_s = recentFollow_s.replace("null","");
                recentFollow_e = recentFollow_e.replace("null","");
                livingCondition = livingCondition.replace("null","");
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress?" + area + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append("sex=" + sex + ";");
                }
                if (!StringUtils.isEmpty(age)) {
                    stringBuffer.append("age=" + age + ";");
                }
                if (!StringUtils.isEmpty(age_s)) {
                    stringBuffer.append("age>=" + age_s + ";");
                }
                if (!StringUtils.isEmpty(age_e)) {
                    stringBuffer.append("age<=" + age_e + ";");
                }
                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic?" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phone?" + phone + ";");
                }
                if (!StringUtils.isEmpty(responseDoctor)) {
                    stringBuffer.append("creator?" + responseDoctor + ";");
                }
                if (!StringUtils.isEmpty(creator)) {
                    stringBuffer.append("creator?" + creator + ";");
                }
                if (!StringUtils.isEmpty(livingCondition)) {
                    stringBuffer.append("livestate=" + livingCondition + ";");
                }
                if (!StringUtils.isEmpty(id)) {
                    stringBuffer.append("id?" + id + ";");
                }
                if (!StringUtils.isEmpty(recentFollow_s)) {
                    stringBuffer.append("follDate>=" + recentFollow_s + ";");
                }
                if (!StringUtils.isEmpty(recentFollow_e)) {
                    stringBuffer.append("follDate<=" + recentFollow_e + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            stringBuffer.append("pastHistory?" + "严重精神障碍" + ";");
            stringBuffer.append("deleteState=" + "未删" + ";");
            filters = stringBuffer.toString();
            List<Perinfor> personList = perinforService.search(fields, filters, sorts, page, size);

            if( personList !=null  &&  personList.size()>0) {
                for (Perinfor item : personList
                ) {
                    int  mentalLength = mentalStringBuffer.length();
                    mentalStringBuffer.delete(0,mentalLength);

                    mentalStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    mentalStringBuffer.append( "deleteState=" + "未删" + ";");

                    String mentalFilters=mentalStringBuffer.toString();
                    List<SevereMentalFollow> mentalList = severeMentalFollowService.search(fields, mentalFilters, sorts, page, size);
                    Date nowDate = new Date();
                    Integer diff = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    MentalQuery data = new MentalQuery();

                    if(mentalList.size()>0){
                        data.setName(item.getName());
                        data.setSex(item.getSex());                       //性别
                        data.setAge(diff);             //年龄我是字符串形 Integer型去掉转换
                        data.setPeopleId(item.getPeopleId());               //身份证
                        data.setPhone(item.getPhonerNum());                  //联系方式
                        data.setEthnic(item.getEthnic());                    //民族
                        data.setNumberFollow(mentalList.size());     //随访记录
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setLivingCondition(item.getLivestate());             //添加生存状态
                        data.setResponseDoctor(mentalList.get(mentalList.size()-1).getDocSign());
                        data.setCreator(item.getCreator());
                        data.setCreateDate(item.getCreateDate());
                        data.setRecentFollow(mentalList.get(mentalList.size()-1).getFollDate());       //添加最近随访时间
                        userList.add(data);
                    }
                    if(mentalList.size()==0){
                        data.setName(item.getName());
                        data.setSex(item.getSex());                       //性别
                        data.setAge(diff);             //年龄我是字符串形 Integer型去掉转换
                        data.setPeopleId(item.getPeopleId());               //身份证
                        data.setPhone(item.getPhonerNum());                  //联系方式
                        data.setEthnic(item.getEthnic());                    //民族
                        data.setNumberFollow(0);     //随访记录
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setLivingCondition(item.getLivestate());             //添加生存状态
                        data.setCreator(item.getCreator());
                        data.setCreateDate(item.getCreateDate());
                        userList.add(data);
                    }
                }
            }

            pagedResponse(request, response, perinforService.getCount(filters), page, size);
            long count = perinforService.getCount(filters);
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
