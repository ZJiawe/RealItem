package com.yihu.ehr.health.ResidentManagement.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.MaternalManagement.entity.MaternalManagement;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.ResidentManagement.entity.*;
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
@Api(value = "ResidentManagement", description = "公卫系统-居民查询", tags = {"居民查询"})
public class ResidentManagementController extends EnvelopRestEndPoint {

    @Autowired
    PerinforService perinforService;
    @RequestMapping(value = ServiceApi.ResidentManagement.ResidentManagement, method = RequestMethod.GET)
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
        List<ResidentManagement> userList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String area = seachobj.get("area").toString();
            String creator = seachobj.get("creator").toString();
            String crowd = seachobj.get("crowd").toString();
            String name = seachobj.get("name").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String sex = seachobj.get("sex").toString();
            String responDoctor = seachobj.get("responDoctor").toString();
            String ethnic = seachobj.get("ethnic").toString();
            String state = seachobj.get("state").toString();
            String age = seachobj.get("age").toString();
            try {
                area = area.replace("null","");
                name = name.replace("null","");
                crowd = crowd.replace("null","");
                age = age.replace("null","");
                peopleId = peopleId.replace("null","");
                responDoctor = responDoctor.replace("null","");
                sex = sex.replace("null","");
                creator = creator.replace("null","");
                ethnic = ethnic.replace("null","");
                state = state.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress?" + area + ";");
                }
/*                if (!StringUtils.isEmpty(crowd)) {
                    stringBuffer.append("creator=" + crowd + ";");
                }*/
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
/*                if (!StringUtils.isEmpty(age)) {
                    stringBuffer.append("age?" + age + ";");
                }*/
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(responDoctor)) {
                    stringBuffer.append("creator?" + responDoctor + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append("sex?" + sex + ";");
                }
                if (!StringUtils.isEmpty(creator)) {
                    stringBuffer.append("creator?" + creator + ";");
                }
                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic?" + ethnic + ";");
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
                    ResidentManagement data = new ResidentManagement();
                    data.setArea(item.getHouseAddress());
                    data.setName(item.getName());
                    data.setPeopleId(item.getPeopleId());
                    data.setSex(item.getSex());
                    data.setEthnic(item.getEthnic());
                    data.setPhone(item.getTPhoneNum());
                    data.setElectronicRecord("是");
                    data.setDocDate(item.getCreateDate());
                    data.setDocPeople(item.getCreator());
                    data.setState(item.getLivestate());
                    data.setDeleteState(item.getDeleteState());
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
