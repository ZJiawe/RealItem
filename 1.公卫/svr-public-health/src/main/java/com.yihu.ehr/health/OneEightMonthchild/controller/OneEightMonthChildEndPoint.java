package com.yihu.ehr.health.OneEightMonthchild.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ChildrenStatistic.service.ChildrenStatisticesService;
import com.yihu.ehr.health.OneEightMonthchild.entity.OneEightMonthChild;
import com.yihu.ehr.health.OneEightMonthchild.service.OneEightMonthChildService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
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
@Api(value = "OneEightMonthChild", description = "公卫系统-儿童满月至八月随访管理", tags = {"儿童满月至八月随访管理"})
public class OneEightMonthChildEndPoint extends EnvelopRestEndPoint {
    @Autowired
    OneEightMonthChildService oneEightMonthChildService;
    @Autowired
    ChildrenStatisticesService childrenStatisticService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.OneEightMonthChild.check, method = RequestMethod.GET)
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

            Date dNow = new Date( );
            SimpleDateFormat  ft  = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateString = ft.format(dNow);
            Date nowDate = ft.parse(nowDateString);
            GregorianCalendar gc =new GregorianCalendar();
            gc.setTime(nowDate);
            gc.add(1,-6);         //获取六年前日期
            gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
            Date childDate = gc.getTime();

            filters = stringBuffer.toString();
            List<Perinfor> checkList = new ArrayList<>();
            List<Perinfor> userList = perinforService.search(fields, filters, sorts, page, size);
            if(userList.size()>0)
            {
                Perinfor checkData = userList.get(0);
                if(checkData.getBirth().before(nowDate) && checkData.getBirth().after(childDate)){   //满足模块要求
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


    @RequestMapping(value = ServiceApi.OneEightMonthChild.print, method = RequestMethod.GET)
    @ApiOperation(value = "获取打印数据", notes = "根据身份证号获取个人信息")
    public Envelop printDatas(
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
            List<OneEightMonthChild> sortList=new ArrayList<>();   //存放排序好的数据
            List<String> sort=new ArrayList<>();   //存放排序条件
            List<String> haveSort=new ArrayList<>();   //存放已经有的数据
            List<String>  noSort=new ArrayList<>();   //存放没有的数据

            sort.add("满月");   //依次添加排序
            sort.add("3月龄");
            sort.add("6月龄");
            sort.add("8月龄");
            List<OneEightMonthChild> userList = oneEightMonthChildService.search(fields, filters, sorts, page, size);   //获取四条数据

            for (OneEightMonthChild item:userList    //获取已有的数据
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
                OneEightMonthChild add=new OneEightMonthChild();
                add.setMonth(item);
                userList.add(add);
            }

            for (int i = 0; i <4 ; i++) {
                for (OneEightMonthChild item:userList                //添加排序
                ) {
                    if( item.getMonth().equals("满月") && i==0)
                        sortList.add(item);
                    if( item.getMonth().equals("3月龄") && i==1)
                        sortList.add(item);
                    if( item.getMonth().equals("6月龄") && i==2)
                        sortList.add(item);
                    if( item.getMonth().equals("8月龄") && i==3)
                        sortList.add(item);
                }
            }
            pagedResponse(request, response, oneEightMonthChildService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(sortList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.OneEightMonthChild.OneEightMonthChild, method = RequestMethod.GET)
    @ApiOperation(value = "获取儿童满月至八月随访列表", notes = "根据查询条件获取儿童满月至八月随访列表在前端表格展示")
    public Envelop searchDatas(
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
                    stringBuffer.append("name=" + name + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<OneEightMonthChild> userList = oneEightMonthChildService.search(fields, filters, sorts, page, size);
            long count = oneEightMonthChildService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, oneEightMonthChildService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.OneEightMonthChild.OneEightMonthChild, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建儿童满月至八月随访", notes = "绑定儿童满月至八月随访信息")
    public Envelop createData(
            @ApiParam(name = "userJsonData", value = "儿童满月至八月随访信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            OneEightMonthChild user = toEntity(userJsonData, OneEightMonthChild.class);
            user.setId(getObjectId(BizObject.User));
            user.setNumber(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = oneEightMonthChildService.saveData(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.OneEightMonthChild.OneEightMonthChild, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改儿童满月至八月随访", notes = "重新绑定儿童满月至八月随访信息")
    public Envelop updateData(
            @ApiParam(name = "userJsonData", value = "儿童满月至八月随访Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            OneEightMonthChild data = toEntity(userJsonData, OneEightMonthChild.class);
            oneEightMonthChildService.saveData(data);
            envelop.setSuccessFlg(true);
            envelop.setObj(data);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.OneEightMonthChild.OneEightMonthChildEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取儿童满月至八月随访信息")
    public Envelop getData(
            @ApiParam(name = "userId", value = "儿童满月至八月随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            OneEightMonthChild user = oneEightMonthChildService.getOneEightMonthChild(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.OneEightMonthChild.OneEightMonthChildEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除儿童满月至八月随访", notes = "根据id删除儿童满月至八月随访")
    public Envelop deleteData(
            @ApiParam(name = "userId", value = "儿童满月至八月随访id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            oneEightMonthChildService.deleteData(userId);
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
        List<OneEightMonthChild> delateList = null;
        delateBuffer.append("peopleId=" + entity.getPeopleId() + ";");
        String delateFilters =  delateBuffer.toString();
        try {
            delateList = oneEightMonthChildService.search("", delateFilters, "", 1, 500);                   //更改后统计实体
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(delateList.size()>0){
            for(OneEightMonthChild item:delateList){
                oneEightMonthChildService.deleteData(item.getId());
            }
        }
    }
}
