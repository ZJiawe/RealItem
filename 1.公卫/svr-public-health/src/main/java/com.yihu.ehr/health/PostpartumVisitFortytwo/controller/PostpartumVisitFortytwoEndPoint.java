package com.yihu.ehr.health.PostpartumVisitFortytwo.controller;
import com.yihu.ehr.health.MaternalStatistical.entity.MaternalStatistical;
import com.yihu.ehr.health.MaternalStatistical.service.MaternalStatisticalService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.PostpartumVisitFortytwo.entity.PostpartumVisitFortytwo;
import com.yihu.ehr.health.PostpartumVisitFortytwo.service.PostpartumVisitFortytwoService;

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

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "PostpartumVisitFortytwo", description = "公卫系统-孕产妇管理", tags = {"产后42天访视表"})
public class PostpartumVisitFortytwoEndPoint extends EnvelopRestEndPoint {
    @Autowired
    PostpartumVisitFortytwoService postpartumVisitFortytwoService;
    @Autowired
    MaternalStatisticalService maternalStatisticalService;
    @Autowired
    PerinforService perinforService;
    @RequestMapping(value = ServiceApi.PostpartumVisitFortytwo.PostpartumVisitFortytwo, method = RequestMethod.GET)
    @ApiOperation(value = "获取孕产妇列表", notes = "根据查询条件获取孕产妇列表在前端表格展示")
    public Envelop search(
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

            try {
                String peopleId = seachobj.get("peopleId").toString();
                String name = seachobj.get("name").toString();
                String date_s = seachobj.get("date_s").toString();
                String date_e = seachobj.get("date_e").toString();
                String insandDep = seachobj.get("insandDep").toString();
                String doctor = seachobj.get("doctor").toString();
                String leaveHospitale_s = seachobj.get("leaveHospitale_s").toString();
                String leaveHospitale_e = seachobj.get("leaveHospitale_e").toString();
                peopleId = peopleId.replace("null","");
                name = name.replace("null","");
                date_s = date_s.replace("null","");
                date_e = date_e.replace("null","");
                insandDep = insandDep.replace("null","");
                doctor = doctor.replace("null","");
                leaveHospitale_s = leaveHospitale_s.replace("null","");
                leaveHospitale_e = leaveHospitale_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(date_s)) {
                    stringBuffer.append( "date>=" + date_s + ";");
                }
                if (!StringUtils.isEmpty(date_e)) {
                    stringBuffer.append( "date<=" + date_e + ";");
                }
                if (!StringUtils.isEmpty(insandDep)) {
                    stringBuffer.append("insandDep?" + insandDep + ";");
                }
                if (!StringUtils.isEmpty(doctor)) {
                    stringBuffer.append("doctor?" + doctor + ";");
                }
                if (!StringUtils.isEmpty(leaveHospitale_s)) {
                    stringBuffer.append( "leaveHospitale>=" + leaveHospitale_s + ";");
                }
                if (!StringUtils.isEmpty(leaveHospitale_e)) {
                    stringBuffer.append( "leaveHospitale<=" + leaveHospitale_e + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<PostpartumVisitFortytwo> userList = postpartumVisitFortytwoService.search(fields, filters, sorts, page, size);
            long count = postpartumVisitFortytwoService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, postpartumVisitFortytwoService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.PostpartumVisitFortytwo.PostpartumVisitFortytwo, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建孕产妇", notes = "绑定孕产妇信息")
    public Envelop create(
            @ApiParam(name = "userJsonData", value = "孕产妇信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            PostpartumVisitFortytwo maternal = toEntity(userJsonData, PostpartumVisitFortytwo.class);
            maternal.setId(getObjectId(BizObject.User));
            maternal.setCreateDate(new Date());
            maternal = postpartumVisitFortytwoService.saveUser(maternal);
            updateData(maternal,1);      //更改统计表数据
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.PostpartumVisitFortytwo.PostpartumVisitFortytwo, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改孕产妇", notes = "重新绑定孕产妇信息")
    public Envelop update(
            @ApiParam(name = "userJsonData", value = "孕产妇Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            PostpartumVisitFortytwo maternal = toEntity(userJsonData, PostpartumVisitFortytwo.class);
            postpartumVisitFortytwoService.saveUser(maternal);
            updateData(maternal,1);      //更改统计表数据
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.PostpartumVisitFortytwo.PostpartumVisitFortytwoEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取孕产妇信息")
    public Envelop get(
            @ApiParam(name = "maternalId", value = "孕产妇id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            PostpartumVisitFortytwo maternal = postpartumVisitFortytwoService.getPostpartumVisitFortytwo(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(maternal);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.PostpartumVisitFortytwo.PostpartumVisitFortytwoEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除孕产妇", notes = "根据id删除孕产妇")
    public Envelop delete(
            @ApiParam(name = "maternalId", value = "孕产妇id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            postpartumVisitFortytwoService.deleteUser(userId);
            PostpartumVisitFortytwo data = postpartumVisitFortytwoService.getPostpartumVisitFortytwo(userId);
            updateData(data,-1);      //更改统计表数据
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }
    public void updateData(PostpartumVisitFortytwo entity,Integer num){
        DecimalFormat df = new DecimalFormat("0.00");
        List<Perinfor> personDatas = null;              //个人档案信息
        List<MaternalStatistical> datas = null;          //统计表信息
        StringBuffer filterStaticBuffer = new StringBuffer();           //统计筛选条件
        filterStaticBuffer.append("peopleId=" + entity.getPeopleId() + ";");                 //筛选统计表
        filterStaticBuffer.append("deleteState=" + "未删" + ";");
        String filterStaticFilters = filterStaticBuffer.toString();
        try {
            personDatas = perinforService.search("", filterStaticFilters, "", 1, 500);     //获取个人信息
            if (personDatas.size() > 0)
                datas = maternalStatisticalService.search("", "area=" + personDatas.get(0).getHouseAddress() + ";", "", 1, 500);  //获取统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //对统计实体操作
        if(datas.size()>0) {

            MaternalStatistical data = datas.get(0);
            if(entity.getHandle().equals("结案"))
                data.setCustodyWomen(data.getCustodyWomen() + num);
            if(entity.getHandle().equals("转诊")){
                if(data.getCustodyWomen()>0)
                    data.setCustodyWomen(data.getCustodyWomen() - num);
            }


            maternalStatisticalService.saveUser(data);
        }
    }
    public void totalDelate (Perinfor entity){
        StringBuffer delateBuffer = new StringBuffer();
        List<PostpartumVisitFortytwo> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = postpartumVisitFortytwoService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(PostpartumVisitFortytwo item:delateList){
                postpartumVisitFortytwoService.deleteUser(item.getId());
            }

        }
    }
}

