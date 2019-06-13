package com.yihu.ehr.health.Elderlyhealthy.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Elderlyhealthy.entity.Elderlyhealthy;
import com.yihu.ehr.health.HealthManagementRecord.entity.HealthManagementRecord;
import com.yihu.ehr.health.HealthManagementRecord.service.HealthManagementRecordService;
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
@Api(value = "Elderlyhealthy", description = "公卫系统-老年人管理管理", tags = {"老年人管理"})
public class ElderlyhealthyEndPoint extends EnvelopRestEndPoint {
    @Autowired
    HealthManagementRecordService healthManagementRecordService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.Elderlyhealthy.Elderlyhealthy, method = RequestMethod.GET)
    @ApiOperation(value = "获取老年人管理列表", notes = "根据查询条件获取老年人管理列表在前端表格展示")
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
        List<Elderlyhealthy> userList = new ArrayList<>();
        List<Elderlyhealthy> noList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer VisitStringBuffer = new StringBuffer();

            JSONObject seachobj = JSONObject.fromObject(filters);
            String startTime = seachobj.get("lastVisit_s").toString();
            String endTime = seachobj.get("lastVisit_e").toString();
            String name = seachobj.get("name").toString();
            String area = seachobj.get("area").toString();
            String sex = seachobj.get("sex").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String phone = seachobj.get("phone").toString();
            String docPeople = seachobj.get("docPeople").toString();
            String total = seachobj.get("total").toString();

            try {
                startTime = startTime.replace("null","");
                endTime = endTime.replace("null","");
                name = name.replace("null","");
                area = area.replace("null","");
                sex = sex.replace("null","");
                peopleId = peopleId.replace("null","");
                phone = phone.replace("null","");
                docPeople = docPeople.replace("null","");
                total = total.replace("null","");

                stringBuffer.append( "deleteState=" + "未删" + ";");    //伪删除需要的
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
                gc.add(1,-65);         //获取六十五年前日期
                gc.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DATE));
                Date elderlyDate = gc.getTime();
                String elderlyDateString = ft.format(elderlyDate);

                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }

                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phonerNum?" + phone + ";");
                }

                if (!StringUtils.isEmpty(docPeople)) {
                    stringBuffer.append("creator?" + docPeople + ";");
                }
                if (!StringUtils.isEmpty(total)) {
                    stringBuffer.append("livestate?" + total + ";");
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
                    int  Length = VisitStringBuffer.length();
                    VisitStringBuffer.delete(0,Length);

                    VisitStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");

                    VisitStringBuffer.append( "deleteState=" + "未删" + ";");

                    String oneFilters=VisitStringBuffer.toString();

                    List<HealthManagementRecord> HealthManagementEcordList = healthManagementRecordService.search(fields, oneFilters, sorts, page, size);

                    Date nowDate = new Date();
                    Integer diff = (getYear(nowDate)-getYear(item.getBirth()));  //获取年龄
                    Elderlyhealthy data = new Elderlyhealthy();
                    data.setName(item.getName());                     //姓名
                    data.setSex(item.getSex());                       //性别
                    data.setAge(diff);
                    data.setPeopleId(item.getPeopleId());               //身份证
                    data.setPhone(item.getPhonerNum());                  //联系方式
                    data.setDocPeople(item.getCreator());             //添加建档人
                    data.setTotal(item.getLivestate());               //生存状态
                    data.setEthnic(item.getEthnic());                  //民族
                    data.setArea(item.getHouseAddress());              //地区
                    data.setDocDate(item.getCreateDate());             //建档时间
                    data.setCreateDate(item.getCreateDate());             //创建时间
                    data.setNeonatalVisit(Integer.toString(HealthManagementEcordList.size()));     //随访次数

                    if(HealthManagementEcordList.size()>0){
                        data.setLastVisit(HealthManagementEcordList.get(HealthManagementEcordList.size()-1).getHealthDate());       //添加最近随访时间 填报时间
                        data.setResponDoctor(HealthManagementEcordList.get(0).getDoctorSignature());                                //添加医生签名
                    }
                    if(data.getAge()>=65)
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
