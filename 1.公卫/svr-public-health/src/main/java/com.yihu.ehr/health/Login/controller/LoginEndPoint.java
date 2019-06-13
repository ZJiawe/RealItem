package com.yihu.ehr.health.Login.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.http.HttpResponse;
import com.yihu.ehr.util.http.HttpUtils;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins =  ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RequestMapping(ApiVersion.Version1_0)
@RestController
@Api(value = "Login", description = "系统用户登录", tags = {"系统用户登录"})
public class LoginEndPoint extends EnvelopRestEndPoint {

    @Value("${info.app.clientId}")
    private String clientId;
    @Value("${service-gateway.agZuul}")
    private String agZuul;

    @RequestMapping(value = ServiceApi.Login.login, method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户账号验证")
    public Envelop login(String username, String password)
    {
        Envelop envelop = new Envelop();
        if(username.isEmpty()){
            envelop.setErrorMsg("用户帐户名不允许为空");
            envelop.setSuccessFlg(false);
            return envelop;
        }
        if(password.isEmpty()){
            envelop.setErrorMsg("用户密码不允许为空");
            envelop.setSuccessFlg(false);
            return envelop;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("password", password);
        params.put("username", username);

        try{
            HttpResponse httpResponse = HttpUtils.doPost(agZuul + "authentication/oauth/login", params);
            if (httpResponse.isSuccessFlg()) {
                Map<String, Object> userMap = objectMapper.readValue(httpResponse.getContent(), Map.class);
                envelop.setObj(userMap);
                envelop.setSuccessFlg(true);
            } else {
                logger.error(httpResponse.getErrorMsg());
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(httpResponse.getErrorMsg());
            }
        }catch (Exception e){
            envelop.setErrorMsg("授权验证失败，请联系管理员！");
            envelop.setSuccessFlg(false);
        }
        return envelop;
    }
    @RequestMapping(value = ServiceApi.Login.logininfo, method = RequestMethod.POST)
    @ApiOperation(value = "用token拉取用户信息", notes = "用token拉取用户信息")
   public Envelop getInfo(String token) {
        Envelop envelopExprot = new Envelop();
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientId);
        params.put("accessToken", token);
            try {
                HttpResponse httpResponse = HttpUtils.doPost(agZuul + "authentication/oauth/validToken", params);
                if (httpResponse.isSuccessFlg()) {
                    Map<String, Object> map = objectMapper.readValue(httpResponse.getContent(), Map.class);
                    Map<String, Object> param = new HashMap<>();
                    param.put("token", token);
                    httpResponse = HttpUtils.doGet(agZuul + "/basic/api/v1.0/users/GetUserByLoginCode/" + map.get("user"), param);
                    Map userMap = this.objectMapper.readValue(httpResponse.getContent(), Map.class);
                    userMap.put("roles", "[\"admin\"]");
                    userMap.put("name", map.get("user"));
                    userMap.put("avatar", "admin");
                    userMap.put("token", token);
                    envelopExprot.setObj(userMap);
                    envelopExprot.setSuccessFlg(true);

                } else {
                    logger.error(httpResponse.getErrorMsg());
                    envelopExprot.setErrorMsg("授权验证失败，请联系管理员！");
                    envelopExprot.setSuccessFlg(false);
                }
            } catch (Exception e) {
                envelopExprot.setErrorMsg("授权验证失败，请联系管理员！");
                envelopExprot.setSuccessFlg(false);
            }
        return envelopExprot;
    }
    @RequestMapping(value = ServiceApi.Login.menuinfo, method = RequestMethod.POST)
    @ApiOperation(value = "用token拉取菜单信息", notes = "用token拉取菜单信息")
    public Envelop getMenu(String token, String user_id) {
        Envelop envelopExprot = new Envelop();
        Map<String, Object> params = new HashMap<>();
        params.put("clientId", clientId);
        params.put("accessToken", token);
        try {
            HttpResponse httpResponse = HttpUtils.doPost(agZuul + "authentication/oauth/validToken", params);
            if (httpResponse.isSuccessFlg()) {
                Map<String, Object> map = objectMapper.readValue(httpResponse.getContent(), Map.class);
                Map<String, Object> param = new HashMap<>();
                param.put("userId", user_id);
                param.put("clientId", clientId);
                param.put("token",token);
                httpResponse = HttpUtils.doGet(agZuul + "/basic/api/v1.0/roles/role_feature/clientFeatures", param);
                Map userMap = this.objectMapper.readValue(httpResponse.getContent(), Map.class);
                userMap.put("roles", "[\"admin\"]");
                userMap.put("name", map.get("user"));
                userMap.put("avatar", "admin");
                userMap.put("token", token);
                envelopExprot.setObj(userMap);
                envelopExprot.setSuccessFlg(true);

            } else {
                logger.error(httpResponse.getErrorMsg());
                envelopExprot.setErrorMsg("授权验证失败，请联系管理员！");
                envelopExprot.setSuccessFlg(false);
            }
        } catch (Exception e) {
            envelopExprot.setErrorMsg("授权验证失败，请联系管理员！");
            envelopExprot.setSuccessFlg(false);
        }
        return envelopExprot;
    }

    @RequestMapping(value = ServiceApi.Login.loginlogout, method = RequestMethod.POST)
    @ApiOperation(value = "用户账号退出", notes = "用户账号退出")
   public Envelop logout() {
       //return "{\"code\":0,\"Message\":\"success\"}";
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        envelop.setErrorMsg("success");
        return envelop;
    }
}
