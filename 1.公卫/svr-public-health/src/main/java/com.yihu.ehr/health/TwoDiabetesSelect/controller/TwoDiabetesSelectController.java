package com.yihu.ehr.health.TwoDiabetesSelect.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.TwoDiabetes.entity.TwoDiabetesEntity;
import com.yihu.ehr.health.TwoDiabetes.service.TwoDiabetesService;
import com.yihu.ehr.health.TwoDiabetesSelect.entity.TwoDiabetesSelect;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
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
@Api(value = "SevereHyperSelectFollow", description = "公卫系统-糖尿病管理", tags = {"糖尿病管理"})
public class TwoDiabetesSelectController extends EnvelopRestEndPoint {
    @Autowired
    TwoDiabetesService twoDiabetesService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.TwoDiabetesSelect.TwoDiabetesSelect, method = RequestMethod.GET)
    @ApiOperation(value = "获取儿童管理列表", notes = "根据查询条件获取儿童管理列表在前端表格展示")
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
        List<TwoDiabetesSelect> userList = new ArrayList<>();
        List<TwoDiabetesSelect> noList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer oneStringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters);
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
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(doctor)) {
                    stringBuffer.append( "responDoctor?" + doctor + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append( "peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append( "sex?" + sex + ";");
                }
                if (!StringUtils.isEmpty(liveStatus)) {
                    stringBuffer.append( "livestate?" + liveStatus + ";");
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
                    stringBuffer.append( "houseAddress?" + area + ";");
                }
                if (!StringUtils.isEmpty(creator)) {
                    stringBuffer.append( "creator?" + creator + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            stringBuffer.append("pastHistory?" + "糖尿病" + ";");
            filters = stringBuffer.toString();
            List<Perinfor> personList = perinforService.search(fields, filters, sorts, page, size);
            if( personList !=null  &&  personList.size()>0) {
                for (Perinfor item : personList
                ) {
                    TwoDiabetesSelect data = new TwoDiabetesSelect();
                    Date nowDate = new Date();
                    Integer diff = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    data.setName(item.getName());
                    data.setSex(item.getSex());                       //性别
                    data.setAge(Integer.toString(diff));             //年龄我是字符串形 Integer型去掉转换
                    data.setPeopleId(item.getPeopleId());               //身份证
                    data.setPhone(item.getPhonerNum());                  //联系方式
                    data.setEthnic(item.getEthnic());                    //民族
                    data.setCreator(item.getCreator());       //建档人用创建人替代
                    data.setCreateDate(item.getCreateDate());       //创建时间即建档时间
                    data.setArea(item.getHouseAddress());                //添加地址
                    data.setLiveStatus(item.getLivestate());             //添加生存状态
                    data.setCreator(item.getCreator());          //添加建档人
                    int  oneLength = oneStringBuffer.length();
                    oneStringBuffer.delete(0,oneLength);
                    oneStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    oneStringBuffer.append( "deleteState=" + "未删" + ";");
                    String oneFilters=oneStringBuffer.toString();
                    List<TwoDiabetesEntity> DiabeteList = twoDiabetesService.search(fields, oneFilters, sorts, page, size);

                    if(DiabeteList.size()>0) {
                        data.setCount(DiabeteList.size());
                        data.setFollowVisit(DiabeteList.get(DiabeteList.size()-1).getFollowDate());       //添加最近随访时间
                        data.setResponDoctor(DiabeteList.get(DiabeteList.size() - 1).getDoctorSign());

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
    //yyyy-MM-dd HH:mm:ss 转换为yyyy-MM-dd'T'HH:mm:ss'Z 格式
    public String changeToUtc(String datetime) throws Exception{
        Date date = DateTimeUtil.simpleDateTimeParse(datetime);
        return DateTimeUtil.utcDateTimeFormat(date);
    }

    public Integer getYear(Date date )     //获取时间年份
    {
        SimpleDateFormat ft  = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = ft.format(date);
        String test = dateString.substring(0,4);
        Integer year = Integer.parseInt(test);
        return year;
    }

}
