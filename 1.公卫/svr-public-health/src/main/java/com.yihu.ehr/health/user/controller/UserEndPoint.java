package com.yihu.ehr.health.user.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.user.entity.Users;
import com.yihu.ehr.health.user.service.UserService;
import com.yihu.ehr.health.util.DateTimeUtil;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.id.BizObject;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author zdm
 * @version 1.0
 * @created 2018.09.04
 * 接口示例
 */
@CrossOrigin(origins =  ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "user", description = "代码示例-用户管理", tags = {"用户管理"})
public class UserEndPoint extends EnvelopRestEndPoint {

    @Autowired
    private UserService userService;



    @RequestMapping(value = ServiceApi.Users.Users, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
    public Envelop searchUsers(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
           /*  StringBuffer stringBuffer = new StringBuffer();
            if (!org.apache.commons.lang.StringUtils.isEmpty("张三")) {
                stringBuffer.append("realName ?" + "张三"+ ";");}
                filters = stringBuffer.toString();*/
            List<Users> userList = userService.search(fields, filters, sorts, page, size);
            pagedResponse(request, response, userService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(userList);
        } catch (ParseException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Users.Users, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public Envelop createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        Envelop envelop  =new Envelop();
        try {
            Users user = toEntity(userJsonData, Users.class);
            user.setId(getObjectId(BizObject.User));
            user.setCreateDate(new Date());
            user.setActivated(true);
            user = userService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (IOException e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Users.Users, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用户", notes = "重新绑定用户信息")
    public Envelop updateUser(
            @ApiParam(name = "userJsonData", value = "用户Json对象", defaultValue = "")
            @RequestBody String userJsonData){
        Envelop envelop = new Envelop();
        try {
            Users user = toEntity(userJsonData, Users.class);
            userService.saveUser(user);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Users.UserAdmin, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取用户信息")
    public Envelop getUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId) {
        Envelop envelop =new Envelop();
        try {
            Users user = userService.getUser(userId);
            envelop.setSuccessFlg(true);
            envelop.setObj(user);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Users.UserAdmin, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    public Envelop deleteUser(
            @ApiParam(name = "userId", value = "用户id", defaultValue = "")
            @PathVariable(value = "userId") String userId){
        Envelop envelop=new Envelop();
        try {
            userService.deleteUser(userId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

}