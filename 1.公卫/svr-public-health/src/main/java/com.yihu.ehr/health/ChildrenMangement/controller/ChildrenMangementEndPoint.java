package com.yihu.ehr.health.ChildrenMangement.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.ChildrenMangement.entity.ChildrenFind;
import com.yihu.ehr.health.ChildrenMangement.entity.ChildrenMangement;
import com.yihu.ehr.health.OneEightMonthchild.entity.OneEightMonthChild;
import com.yihu.ehr.health.OneEightMonthchild.service.OneEightMonthChildService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.ThreeSixYearChild.entity.ThreeSixYearChild;
import com.yihu.ehr.health.ThreeSixYearChild.service.ThreeSixYearChildService;
import com.yihu.ehr.health.TwelveThsixEightMonth.entity.TwelveThsixEightMonthChild;
import com.yihu.ehr.health.TwelveThsixEightMonth.service.TwelveThsixEightMonthChildService;
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
import java.util.*;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "ChildrenMangement", description = "公卫系统-儿童管理管理", tags = {"儿童管理"})
public class ChildrenMangementEndPoint extends EnvelopRestEndPoint {
    @Autowired
    OneEightMonthChildService oneEightMonthChildService;
    @Autowired
    TwelveThsixEightMonthChildService twelveThsixEightMonthChildService;
    @Autowired
    ThreeSixYearChildService threeSixYearChildService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.ChildrenMangement.ChildrenMangement, method = RequestMethod.GET)
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
        List<ChildrenMangement> userList = new ArrayList<>();
        List<ChildrenMangement> noList = new ArrayList<>();
        Integer age = -1;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer oneStringBuffer = new StringBuffer();
            StringBuffer threeStringBuffer = new StringBuffer();
            StringBuffer tweleveStringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters);
            String startTime = seachobj.get("startTime").toString();
            String endTime = seachobj.get("endTime").toString();
            String name = seachobj.get("name").toString();
            String area = seachobj.get("area").toString();
            String sex = seachobj.get("sex").toString();
            String nation = seachobj.get("nation").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String phone = seachobj.get("phone").toString();
            String doctor = seachobj.get("doctor").toString();
            String documentDate = seachobj.get("documentDate").toString();
            String documentPeople = seachobj.get("documentPeople").toString();
            String liveState = seachobj.get("liveState").toString();
            String ageStr = seachobj.get("age").toString();
            try {
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
                name = name.replace("null","");
                area = area.replace("null","");
                sex = sex.replace("null","");
                nation = nation.replace("null","");
                peopleId = peopleId.replace("null","");
                phone = phone.replace("null","");
                doctor = doctor.replace("null","");
                documentDate = documentDate.replace("null","");
                documentPeople = documentPeople.replace("null","");
                liveState = liveState.replace("null","");

                if(!ageStr.isEmpty()){
                    age=Integer.parseInt(ageStr);
                }

                stringBuffer.append( "deleteState=" + "未删" + ";");    //伪删除需要的
                stringBuffer.append( "pregnant=" + "否" + ";");    //删除孕妇
                if (!StringUtils.isEmpty(startTime)) {
                    stringBuffer.append( "createDate>" + startTime + ";");
                }
                if (!StringUtils.isEmpty(endTime)) {
                    stringBuffer.append("createDate<" + endTime + ";");
                }
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress?" + area + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append("sex=" + sex + ";");
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
                String childDateString = ft.format(childDate);

                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }

                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phonerNum?" + phone + ";");
                }

                if (!StringUtils.isEmpty(nation)) {
                    stringBuffer.append("ethnic?" + nation + ";");
                }

                if (!StringUtils.isEmpty(documentDate)) {
                    stringBuffer.append("createDate=" + documentDate + ";");
                }
                if (!StringUtils.isEmpty(documentPeople)) {
                    stringBuffer.append("creator?" + documentPeople + ";");
                }
                if (!StringUtils.isEmpty(liveState)) {
                    stringBuffer.append("livestate?" + liveState + ";");
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
                    int  oneLength = oneStringBuffer.length();
                    oneStringBuffer.delete(0,oneLength);
                    int  threeLength = oneStringBuffer.length();
                    threeStringBuffer.delete(0,threeLength);
                    int  tweleveLength = oneStringBuffer.length();
                    tweleveStringBuffer.delete(0,tweleveLength);

                    doctor = doctor.replace("null","");

                    oneStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    threeStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    tweleveStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");

                    oneStringBuffer.append( "deleteState=" + "未删" + ";");

                    threeStringBuffer.append( "deleteState=" + "未删" + ";");

                    tweleveStringBuffer.append( "deleteState=" + "未删" + ";");

                    String oneFilters=oneStringBuffer.toString();
                    String threeFilters=threeStringBuffer.toString();
                    String tweleveFilters=tweleveStringBuffer.toString();
                    List<OneEightMonthChild> oneChildList = oneEightMonthChildService.search(fields, oneFilters, sorts, page, size);
                    List<ThreeSixYearChild> threeChildrenList = threeSixYearChildService.search(fields, threeFilters, sorts, page, size);
                    List<TwelveThsixEightMonthChild> tweleveChildrenList = twelveThsixEightMonthChildService.search(fields, tweleveFilters, sorts, page, size);
                    Date nowDate = new Date();
                    Integer diff = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    ChildrenMangement data = new ChildrenMangement();
                    data.setName(item.getName());
                    data.setSex(item.getSex());                       //性别
                    data.setAge(Integer.toString(diff));             //年龄我是字符串形 Integer型去掉转换
                    data.setPeopleId(item.getPeopleId());               //身份证
                    data.setPhone(item.getPhonerNum());                  //联系方式
                    data.setNation(item.getEthnic());                    //民族
                    data.setDocumentPeople(item.getCreator());       //建档人用创建人替代
                    data.setDocumentDate(item.getCreateDate());       //创建时间即建档时间
                    data.setVisitAllCount(oneChildList.size()+threeChildrenList.size()+tweleveChildrenList.size());     //随访记录
                    data.setArea(item.getHouseAddress());                //添加地址
                    data.setLiveState(item.getLivestate());             //添加生存状态
                    data.setDocumentPeople(item.getCreator());          //添加建档人
                    if(oneChildList.size()>0){
                        data.setLastVisit(oneChildList.get(oneChildList.size()-1).getFollowDate());       //添加最近随访时间
                        data.setNewbornVisit(oneChildList.get(0).getFollowDate());                        //添加第一次产前随访
                        data.setDoctor(oneChildList.get(oneChildList.size()-1).getDoctor());
                    }

                    if(tweleveChildrenList.size()>0){
                        data.setLastVisit(tweleveChildrenList.get(tweleveChildrenList.size()-1).getFollowDate());       //添加最近随访时间
                        data.setDoctor(tweleveChildrenList.get(tweleveChildrenList.size()-1).getDoctor());
                        if(data.getNewbornVisit() == null)     //若前面没有随访添加第一次随访时间
                            data.setNewbornVisit(tweleveChildrenList.get(0).getFollowDate());
                    }

                    if(threeChildrenList.size()>0){
                        data.setLastVisit(threeChildrenList.get(threeChildrenList.size()-1).getFollowDate());       //添加最近随访时间
                        data.setDoctor(threeChildrenList.get(threeChildrenList.size()-1).getDoctor());
                        if(data.getNewbornVisit() == null)             //若前面没有随访  第一次随访时间
                            data.setNewbornVisit(threeChildrenList.get(0).getFollowDate());
                    }
                    if( data.getVisitAllCount()==null )
                        data.setVisitAllCount(0);
                    if(data.getVisitAllCount().equals(0))    //记录随访记录为0的数据
                        noList.add(data);

                    if(age==-1){
                        if(diff<=6) {
                            userList.add(data);
                        }
                    }else if(diff.equals(age) && diff<=6){
                        userList.add(data);
                    }

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
