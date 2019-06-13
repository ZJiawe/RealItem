package com.yihu.ehr.health.oauth.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.oauth.service.AuthenticationService;
import com.yihu.ehr.health.util.NetBase;
import com.yihu.ehr.health.util.ServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author zdm
 * @version 1.0
 * @created 2018.09.04
 * 接口示例
 */
@RestController
@RequestMapping(ApiVersion.Version1_0)
@CrossOrigin(origins = "http://web.godwolf.com", maxAge = 3600)
@Api(value = "authentication", description = "单点登录-", tags = {"用户s管理"})
public class AuthenticationEndPoint extends EnvelopRestEndPoint {

    @Autowired
    private AuthenticationService authenticationservice;

    @RequestMapping(value = ServiceApi.Authentication.Users, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用户", notes = "绑定用户信息")
    public String  createUser(
            @ApiParam(name = "userJsonData", value = "用户信息json串", defaultValue = "")
            @RequestBody String userJsonData) {
        String tokon= null;
        try {
            NetBase.Gert();
            tokon ="" ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokon;
    }


}