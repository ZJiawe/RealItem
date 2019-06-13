package com.yihu.ehr.health.SevereHyperFollow.controller;

import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.SevereHyperSumFollow.entity.SevereHyperSumFollow;
import com.yihu.ehr.health.util.DateTimeUtil;
import  com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.SevereHyperFollow.entity.SevereHyperFollow;
import com.yihu.ehr.health.SevereHyperFollow.service.SevereHyperFollowService;

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
@Api(value = "SevereHyperFollow", description = "公卫系统-个人资料管理", tags = {"个人资料管理"})
public class SevereHyperFollowController extends EnvelopRestEndPoint {
    @Autowired
    SevereHyperFollowService severeHyperFollowService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.SevereHyperFollow.check, method = RequestMethod.GET)
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

            filters = stringBuffer.toString();
            List<Perinfor> checkList = new ArrayList<>();
            List<Perinfor> userList = perinforService.search(fields, filters, sorts, page, size);
            if(userList.size()>0)
            {
                Perinfor checkData = userList.get(0);
                if(checkData.getPastHistory().contains("高血压")){   //满足模块要求
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
    @RequestMapping(value = ServiceApi.SevereHyperFollow.print, method = RequestMethod.GET)
    @ApiOperation(value = "获取打印数据", notes = "根据身份证号获取个人信息")
    public Envelop print(
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
            List<SevereHyperFollow> sortList=new ArrayList<>();   //存放排序好的数据
            List<String> sort=new ArrayList<>();   //存放排序条件
            List<String> haveSort=new ArrayList<>();   //存放已经有的数据
            List<String>  noSort=new ArrayList<>();   //存放没有的数据

            sort.add("第一列");   //依次添加排序
            sort.add("第二列");
            sort.add("第三列");
            sort.add("第四列");
            List<SevereHyperFollow> userList = severeHyperFollowService.search(fields, filters, sorts, page, size);
            for (SevereHyperFollow item:userList    //获取已有的数据
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
                SevereHyperFollow add=new SevereHyperFollow();
                add.setNumber(item);
                userList.add(add);
            }
            for (int i = 0; i <4 ; i++) {
                for (SevereHyperFollow item:userList                //添加排序
                ) {
                    if( item.getNumber().equals("第一列") && i==0)
                        sortList.add(item);
                    if( item.getNumber().equals("第二列") && i==1)
                        sortList.add(item);
                    if( item.getNumber().equals("第三列") && i==2)
                        sortList.add(item);
                    if( item.getNumber().equals("第四列") && i==3)
                        sortList.add(item);
                }
            }
            pagedResponse(request, response, severeHyperFollowService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(sortList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHyperFollow.SevereHyperFollow, method = RequestMethod.GET)
    @ApiOperation(value = "获取高血压患者列表", notes = "根据查询条件获取高血压患者列表在前端表格展示")
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
            String name = seachobj.get("name").toString();
            String follType = seachobj.get("follType").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String smokePer = seachobj.get("smokePer").toString();
            String sport = seachobj.get("sport").toString();
            String saltSituation = seachobj.get("saltSituation").toString();
            String psyAdjust = seachobj.get("psyAdjust").toString();
            String obeyMedical = seachobj.get("obeyMedical").toString();
            String mediCompli = seachobj.get("mediCompli").toString();
            String adverdrugReact = seachobj.get("adverdrugReact").toString();
            String accessType = seachobj.get("accessType").toString();
            String drugName = seachobj.get("drugName").toString();
            String organAndDiv = seachobj.get("organAndDiv").toString();
            String follUpdate_s = seachobj.get("follUpdate_s").toString();
            String follUpdate_e= seachobj.get("follUpdate_e").toString();
            String dateNextFollow_s = seachobj.get("dateNextFollow_s").toString();
            String dateNextFollow_e= seachobj.get("dateNextFollow_e").toString();
            try {
                name = name.replace("null","");
                follType = follType.replace("null","");
                peopleId = peopleId.replace("null","");
                smokePer = smokePer.replace("null","");
                sport = sport.replace("null","");
                saltSituation = saltSituation.replace("null","");
                psyAdjust = psyAdjust.replace("null","");
                obeyMedical = obeyMedical.replace("null","");
                mediCompli = mediCompli.replace("null","");
                adverdrugReact = adverdrugReact.replace("null","");
                accessType = accessType.replace("null","");
                drugName = drugName.replace("null","");
                organAndDiv = organAndDiv.replace("null","");
                follUpdate_s = follUpdate_s.replace("null","");
                follUpdate_e = follUpdate_e.replace("null","");
                dateNextFollow_s = dateNextFollow_s.replace("null","");
                dateNextFollow_e = dateNextFollow_e.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(follType)) {
                    stringBuffer.append("follType?" + follType + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(smokePer)) {
                    stringBuffer.append("smokePer?" + smokePer + ";");
                }
                if (!StringUtils.isEmpty(sport)) {
                    stringBuffer.append("sport?" + sport + ";");
                }
                if (!StringUtils.isEmpty(saltSituation)) {
                    stringBuffer.append("saltSituation?" + saltSituation + ";");
                }
                if (!StringUtils.isEmpty(psyAdjust)) {
                    stringBuffer.append("psyAdjust?" + psyAdjust + ";");
                }
                if (!StringUtils.isEmpty(obeyMedical)) {
                    stringBuffer.append("obeyMedical?" + obeyMedical + ";");
                }
                if (!StringUtils.isEmpty(mediCompli)) {
                    stringBuffer.append("mediCompli?" + mediCompli + ";");
                }
                if (!StringUtils.isEmpty(adverdrugReact)) {
                    stringBuffer.append("adverdrugReact?" + adverdrugReact + ";");
                }
                if (!StringUtils.isEmpty(accessType)) {
                    stringBuffer.append("accessType?" + accessType + ";");
                }
                if (!StringUtils.isEmpty(drugName)) {
                    stringBuffer.append("drugName?" + drugName + ";");
                }
                if (!StringUtils.isEmpty(organAndDiv)) {
                    stringBuffer.append("organAndDiv?" + organAndDiv + ";");
                }
                if (!StringUtils.isEmpty(follUpdate_s)) {
                    stringBuffer.append( "follUpdate>" +follUpdate_s + ";");
                }
                if (!StringUtils.isEmpty(follUpdate_e)) {
                    stringBuffer.append("follUpdate<" +follUpdate_e + ";");
                }
                if (!StringUtils.isEmpty(dateNextFollow_s)) {
                    stringBuffer.append( "dateNextFollow>" +dateNextFollow_s + ";");
                }
                if (!StringUtils.isEmpty(dateNextFollow_e)) {
                    stringBuffer.append("dateNextFollow<" +dateNextFollow_e + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<SevereHyperFollow> userList = severeHyperFollowService.search(fields, filters, sorts, page, size);
            long count = severeHyperFollowService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, severeHyperFollowService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.SevereHyperFollow.SevereHyperFollow, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建高血压患者", notes = "绑定高血压患者信息")
    public Envelop create(
            @ApiParam(name = "userJsonData", value = "高血压患者信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereHyperFollow user = toEntity(userJsonData, SevereHyperFollow.class);
            user.setId(getObjectId(BizObject.User));
            user.setWorkId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = severeHyperFollowService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHyperFollow.SevereHyperFollow, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改高血压患者", notes = "重新绑定高血压患者信息")
    public Envelop update(
            @ApiParam(name = "userJsonData", value = "高血压患者Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            SevereHyperFollow user = toEntity(userJsonData, SevereHyperFollow.class);
            severeHyperFollowService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHyperFollow.SevereHyperFollowEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取高血压患者信息")
    public Envelop get(
            @ApiParam(name = "userId", value = "高血压患者id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            SevereHyperFollow user = severeHyperFollowService.getSevereHyperFollow(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.SevereHyperFollow.SevereHyperFollowEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除高血压患者", notes = "根据id删除高血压患者")
    public Envelop delete(
            @ApiParam(name = "userId", value = "高血压患者id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            severeHyperFollowService.deleteUser(userId);
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
        List<SevereHyperFollow> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = severeHyperFollowService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(SevereHyperFollow item:delateList){
                severeHyperFollowService.deleteUser(item.getId());
            }

        }
    }
}
