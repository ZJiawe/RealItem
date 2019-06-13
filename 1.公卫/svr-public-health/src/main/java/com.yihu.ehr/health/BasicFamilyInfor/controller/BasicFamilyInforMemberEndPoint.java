package com.yihu.ehr.health.BasicFamilyInfor.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInfor;
import com.yihu.ehr.health.BasicFamilyInfor.entity.BasicFamilyInforMember;
import com.yihu.ehr.health.BasicFamilyInfor.entity.PerinforMember;
import com.yihu.ehr.health.BasicFamilyInfor.service.BasicFamilyInforMemberService;
import com.yihu.ehr.health.BasicFamilyInfor.service.BasicFamilyInforService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.id.BizObject;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
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
@Api(value = "BasicFamilyInfor", description = "公卫系统-家庭成员资料管理", tags = {"家庭成员资料管理"})
public class BasicFamilyInforMemberEndPoint extends EnvelopRestEndPoint {
    @Autowired
    BasicFamilyInforService basicfamilyinforService;
    @Autowired
    BasicFamilyInforMemberService basicFamilyInforMemberService;
    @Autowired
    PerinforService perinforService;


    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformember, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop searchUsers(
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
            org.json.JSONObject seachobj = new JSONObject(filters);
            String id = seachobj.get("familyid").toString();
            String idNum = seachobj.get("idNum").toString();
            String category = seachobj.get("category").toString();
            try {
                id = id.replace("null","");
                idNum = idNum.replace("null","");
                category = category.replace("null","");
                if (!StringUtils.isEmpty(id)) {
                    stringBuffer.append("familyid?" + id + ";");
                }
                if (!StringUtils.isEmpty(idNum)) {
                    stringBuffer.append("idNum?" + idNum + ";");
                }
                if (!StringUtils.isEmpty(category)) {
                    stringBuffer.append("category?" + category + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<PerinforMember> userList = new ArrayList<>();
            List<BasicFamilyInforMember> usermengList = basicFamilyInforMemberService.search(fields, filters, sorts, page, size);
            StringBuffer stringBuffer2 = new StringBuffer();
            for (BasicFamilyInforMember item:usermengList            //提取现用户信息
            ) {
                String filters2 = "peopleId=" + item.getidNum() + ";";
                List<Perinfor> perinforList = perinforService.search(fields, filters2, sorts, page, size);
                if(perinforList.size()>0)
                {
                    PerinforMember perinformember = new PerinforMember();
                    Perinfor perinfor = perinforList.get(0);
                    BeanUtils.copyProperties(perinfor, perinformember);
                    perinformember.setId(item.getId());
                    perinformember.setName(perinfor.getName());
                    perinformember.setIdNum(item.getidNum());
                    perinformember.setSex(perinfor.getSex());
                    perinformember.setMaritalStatus(perinfor.getMaritalStatus());
                    perinformember.setBirth(perinfor.getBirth());
                    perinformember.setCategory(item.getCategory());
                    perinformember.setOccurrenceDate(item.getOccurrenceDate());
                    perinformember.setMajorHealthIssues(item.getMajorHealthIssues());
                    userList.add(perinformember);
                }

            }
            long count = basicFamilyInforMemberService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, basicFamilyInforMemberService.getCount(filters), page, size);
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

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformember, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInforMember user = toEntity(userJsonData, BasicFamilyInforMember.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user = basicFamilyInforMemberService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformember, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInforMember user = toEntity(userJsonData, BasicFamilyInforMember.class);
            basicFamilyInforMemberService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformemberManage, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "合并", notes = "重新合并档案信息")
    public Envelop manager(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            String x = userJsonData.substring(8,userJsonData.length()-2);
            String[] ids = x.split(",");
            String mageid = "";
            for(String id:ids){
                if(mageid == "")
                {
                    mageid = id;
                }
                List<BasicFamilyInforMember> usermengList = basicFamilyInforMemberService.search("", "familyid=" + id + ";", "",1,500);
                for (BasicFamilyInforMember item:usermengList)
                {
                    item.setfamilyid(mageid);
                    basicFamilyInforMemberService.saveUser(item);
                }
                if( mageid != id)
                {
                   //  basicfamilyinforService.deleteUser(id);
                }
            }
           envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformemberSplit, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "拆分", notes = "重新拆分档案信息")
    public Envelop Split(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop = new Envelop();
        try {
            String x = userJsonData.substring(2,userJsonData.length()-2);
            String[] ids = x.split(",");
            //可以使用便利循环进行简化
            String mageid = "";
            for(String id:ids){
                id = id.replace("\"", "");
                if(mageid == "")
                {
                    List<BasicFamilyInforMember> usermengList = basicFamilyInforMemberService.search("", "id=" + id + ";", "",1,500);
                    for (BasicFamilyInforMember item1:usermengList) {
                        List<Perinfor> perinforList = perinforService.search("", "peopleId=" + item1.getidNum() + ";", "", 1, 500);
                        for (Perinfor item2:perinforList) {
                            BasicFamilyInfor user = new BasicFamilyInfor();
                            user.setId(getObjectId(BizObject.User));
                            mageid = user.getId();
                            user.setHeadeName(item2.getName());
                            user.setCreateDate(new Date());
                            user = basicfamilyinforService.saveUser(user);
                            item1.setfamilyid(mageid);
                            item1.setCategory("户主");
                            basicFamilyInforMemberService.saveUser(item1);
                        }
                    }
                    continue;
                }
                List<BasicFamilyInforMember> usermengList = basicFamilyInforMemberService.search("", "id=" + id + ";", "",1,500);
                for (BasicFamilyInforMember item:usermengList)
                {
                    item.setfamilyid(mageid);
                    item.setCategory("");
                    basicFamilyInforMemberService.saveUser(item);
                }
                if( mageid != id)
                {
                }
            }
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformemberEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            BasicFamilyInforMember user = basicFamilyInforMemberService.getBasicFamilyInforMember(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.BasicFamilyInformember.BasicFamilyInformemberEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop = new Envelop();
        try {
            basicFamilyInforMemberService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
