package com.yihu.ehr.health.SevereHyperSelectFollow.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.SevereHyperSelectFollow.entity.SevereHyperSelectFollow;
import com.yihu.ehr.health.SevereHyperFollow.entity.SevereHyperFollow;
import com.yihu.ehr.health.SevereHyperFollow.service.SevereHyperFollowService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
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
@Api(value = "SevereHyperSelectFollow", description = "公卫系统-高血压查询", tags = {"高血压查询"})
public class SevereHyperSelectFollowController extends EnvelopRestEndPoint {
    @Autowired
    SevereHyperFollowService severeHyperFollowService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.SevereHyperSelectFollow.SevereHyperSelectFollow, method = RequestMethod.GET)
    @ApiOperation(value = "获取高血压列表", notes = "根据查询条件获取高血压列表在前端表格展示")
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
        List<SevereHyperSelectFollow> userList = new ArrayList<>();
        List<SevereHyperSelectFollow> noList = new ArrayList<>();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer oneStringBuffer = new StringBuffer();
            JSONObject seachobj = JSONObject.fromObject(filters);
            String name = seachobj.get("name").toString();
            String sex = seachobj.get("sex").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String area = seachobj.get("area").toString();
            String total = seachobj.get("total").toString();
            String ethnic = seachobj.get("ethnic").toString();
            String phone = seachobj.get("phone").toString();
            String responDoctor = seachobj.get("responDoctor").toString();
            String docPeople = seachobj.get("docPeople").toString();
            String lastVisit_s = seachobj.get("lastVisit_s").toString();
            String lastVisit_e = seachobj.get("lastVisit_e").toString();
            String neonatalVisit = seachobj.get("neonatalVisit").toString();
            String docDate_s = seachobj.get("docDate_s").toString();
            String docDate_e= seachobj.get("docDate_e").toString();
            try {
                name = name.replace("null","");
                total = total.replace("null","");
                sex = sex.replace("null","");
                peopleId = peopleId.replace("null","");
                area = area.replace("null","");
                ethnic = ethnic.replace("null","");
                phone = phone.replace("null","");
                responDoctor = responDoctor.replace("null","");
                docPeople = docPeople.replace("null","");
                lastVisit_s = lastVisit_s.replace("null","");
                lastVisit_e = lastVisit_e.replace("null","");
                neonatalVisit = neonatalVisit.replace("null","");
                docDate_s = docDate_s.replace("null","");
                docDate_e = docDate_e.replace("null","");
                if (!StringUtils.isEmpty(name)) {
                    stringBuffer.append("name?" + name + ";");
                }
                if (!StringUtils.isEmpty(ethnic)) {
                    stringBuffer.append("ethnic?" + ethnic + ";");
                }
                if (!StringUtils.isEmpty(total)) {
                    stringBuffer.append("livestate?" + total + ";");
                }
                if (!StringUtils.isEmpty(sex)) {
                    stringBuffer.append("sex?" + sex + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId?" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(area)) {
                    stringBuffer.append("houseAddress?" + area + ";");
                }
                if (!StringUtils.isEmpty(phone)) {
                    stringBuffer.append("phonerNum?" + phone + ";");
                }
                if (!StringUtils.isEmpty(responDoctor)) {
                    stringBuffer.append("responDoctor?" + responDoctor + ";");
                }
                if (!StringUtils.isEmpty(docPeople)) {
                    stringBuffer.append("creator?" + docPeople + ";");
                }
                if (!StringUtils.isEmpty(lastVisit_s)) {
                    stringBuffer.append( "lastVisit>" +lastVisit_s + ";");
                }
                if (!StringUtils.isEmpty(lastVisit_e)) {
                    stringBuffer.append("lastVisit<" +lastVisit_e + ";");
                }
                if (!StringUtils.isEmpty(neonatalVisit)) {
                    stringBuffer.append("neonatalVisit?" + neonatalVisit + ";");
                }
                if (!StringUtils.isEmpty(docDate_s)) {
                    stringBuffer.append( "docDate>" +docDate_s + ";");
                }
                if (!StringUtils.isEmpty(docDate_e)) {
                    stringBuffer.append("docDate<" +docDate_e + ";");
                }

            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            stringBuffer.append("pastHistory?" + "高血压" + ";");
            filters = stringBuffer.toString();
            List<Perinfor> personList = perinforService.search(fields, filters, sorts, page, size);
            if( personList !=null  &&  personList.size()>0) {
                for (Perinfor item : personList
                ) {
                    int  oneLength = oneStringBuffer.length();
                    oneStringBuffer.delete(0,oneLength);
                    oneStringBuffer.append( "peopleId=" + item.getPeopleId() + ";");
                    oneStringBuffer.append( "deleteState=" + "未删" + ";");
                    String oneFilters=oneStringBuffer.toString();
                    List<SevereHyperFollow> HyperList = severeHyperFollowService.search(fields, oneFilters, sorts, page, size);
                    SevereHyperSelectFollow data = new SevereHyperSelectFollow();

                    if(HyperList.size()>0){
                        data.setName(item.getName());
                        data.setSex(item.getSex());                       //性别
                        data.setPeopleId(item.getPeopleId());               //身份证
                        data.setPhone(item.getPhonerNum());                  //联系方式
                        data.setEthnic(item.getEthnic());                    //民族
                        data.setDocPeople(item.getCreator());       //建档人用创建人替代
                        data.setDocDate(item.getCreateDate());       //创建时间即建档时间
                        data.setNeonatalVisit(HyperList.size());     //随访记录
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setTotal(item.getLivestate());             //添加生存状态
                        data.setDocPeople(item.getCreator());          //添加建档人
                        data.setLastVisit(HyperList.get(HyperList.size()-1).getFollUpdate());       //添加最近随访时间
                        data.setResponDoctor(HyperList.get(HyperList.size()-1).getDocSignature());
                        userList.add(data);
                    }
                    if(HyperList.size()==0){
                        data.setName(item.getName());
                        data.setSex(item.getSex());                       //性别
                        data.setPeopleId(item.getPeopleId());               //身份证
                        data.setPhone(item.getPhonerNum());                  //联系方式
                        data.setEthnic(item.getEthnic());                    //民族
                        data.setDocPeople(item.getCreator());       //建档人用创建人替代
                        data.setDocDate(item.getCreateDate());       //创建时间即建档时间
                        data.setNeonatalVisit(0);     //随访记录
                        data.setArea(item.getHouseAddress());                //添加地址
                        data.setTotal(item.getLivestate());             //添加生存状态
                        data.setDocPeople(item.getCreator());          //添加建档人
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
