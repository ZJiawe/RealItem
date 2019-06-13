package com.yihu.ehr.health.TwelveThsixEightMonth.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ChildrenStatistic.service.ChildrenStatisticesService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.ThreeSixYearChild.entity.ThreeSixYearChild;
import com.yihu.ehr.health.TwelveThsixEightMonth.entity.TwelveThsixEightMonthChild;
import com.yihu.ehr.health.TwelveThsixEightMonth.service.TwelveThsixEightMonthChildService;
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
import java.util.*;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "TwelveThsixEightMonthChild", description = "公卫系统-儿童十二月至三十月随访管理", tags = {"儿童十二月至三十月随访管理"})
public class TwelveThsixEightMonthChildEndPoint extends EnvelopRestEndPoint {
    @Autowired
    TwelveThsixEightMonthChildService twelveThsixEightMonthChildService;
    @Autowired
    ChildrenStatisticesService childrenStatisticService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.print, method = RequestMethod.GET)
    @ApiOperation(value = "获取打印数据", notes = "根据身份证号获取个人信息")
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
            List<TwelveThsixEightMonthChild> sortList=new ArrayList<>();   //存放排序好的数据
            List<String> sort=new ArrayList<>();   //存放排序条件
            List<String> haveSort=new ArrayList<>();   //存放已经有的数据
            List<String>  noSort=new ArrayList<>();   //存放没有的数据

            sort.add("12月龄");   //依次添加排序
            sort.add("18月龄");
            sort.add("24月龄");
            sort.add("30月龄");
            List<TwelveThsixEightMonthChild> userList = twelveThsixEightMonthChildService.search(fields, filters, sorts, page, size);   //获取四条数据

            for (TwelveThsixEightMonthChild item:userList    //获取已有的数据
            ) {
                haveSort.add(item.getMonth());
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
                TwelveThsixEightMonthChild add=new TwelveThsixEightMonthChild();
                add.setMonth(item);
                userList.add(add);
            }

            for (int i = 0; i <4 ; i++) {
                for (TwelveThsixEightMonthChild item:userList                //添加排序
                ) {
                    if( item.getMonth().equals("12月龄") && i==0)
                        sortList.add(item);
                    if( item.getMonth().equals("18月龄") && i==1)
                        sortList.add(item);
                    if( item.getMonth().equals("24月龄") && i==2)
                        sortList.add(item);
                    if( item.getMonth().equals("30月龄") && i==3)
                        sortList.add(item);
                }
            }

            pagedResponse(request, response, twelveThsixEightMonthChildService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(sortList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.TwelveThsixEightMonthChild, method = RequestMethod.GET)
    @ApiOperation(value = "获取儿童十二月至三十月随访列表", notes = "根据查询条件获取儿童十二月至三十月随访列表在前端表格展示")
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
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters.toString());
            String startTime = seachobj.get("startTime").toString();
            String endTime = seachobj.get("endTime").toString();
            String name = seachobj.get("name").toString();
            String peopleId = seachobj.get("peopleId").toString();
            try {
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
                name = name.replace("null","");
                peopleId = peopleId.replace("null","");
                stringBuffer.append( "deleteState?" + "未删" + ";");    //伪删除需要的
                if (!StringUtils.isEmpty(startTime)) {
                    stringBuffer.append( "followDate>=" + startTime + ";");
                }
                if (!StringUtils.isEmpty(endTime)) {
                    stringBuffer.append("followDate<=" + endTime + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<TwelveThsixEightMonthChild> userList = twelveThsixEightMonthChildService.search(fields, filters, sorts, page, size);
            pagedResponse(request, response, twelveThsixEightMonthChildService.getCount(filters), page, size);
            long count = twelveThsixEightMonthChildService.getCount(filters);
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

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.TwelveThsixEightMonthChild, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建儿童十二月至三十月随访", notes = "绑定儿童十二月至三十月随访信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "儿童十二月至三十月随访信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            TwelveThsixEightMonthChild user = toEntity(userJsonData, TwelveThsixEightMonthChild.class);
            user.setId(getObjectId(BizObject.User));
            user.setNumber(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = twelveThsixEightMonthChildService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.TwelveThsixEightMonthChild, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改儿童十二月至三十月随访", notes = "重新绑定儿童十二月至三十月随访信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "儿童十二月至三十月随访Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            TwelveThsixEightMonthChild user = toEntity(userJsonData, TwelveThsixEightMonthChild.class);
            twelveThsixEightMonthChildService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.TwelveThsixEightMonthChildEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取儿童十二月至三十月随访信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "儿童十二月至三十月随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            TwelveThsixEightMonthChild user = twelveThsixEightMonthChildService.getTwelveThsixEightMonthChild(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.TwelveThsixEightMonthChild.TwelveThsixEightMonthChildEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除儿童十二月至三十月随访", notes = "根据id删除儿童十二月至三十月随访")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "儿童十二月至三十月随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            twelveThsixEightMonthChildService.deleteUser(userId);
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
        List<TwelveThsixEightMonthChild> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = twelveThsixEightMonthChildService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(TwelveThsixEightMonthChild item:delateList){
                twelveThsixEightMonthChildService.deleteUser(item.getId());
            }
        }
    }

}
