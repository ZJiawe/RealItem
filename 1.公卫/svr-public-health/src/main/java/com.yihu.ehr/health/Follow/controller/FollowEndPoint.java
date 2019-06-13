package com.yihu.ehr.health.Follow.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.Follow.entity.FollowEntity;
import com.yihu.ehr.health.Follow.service.FollowService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "Follow", description = "公卫系统-随访记录", tags = {"随访记录"})
public class FollowEndPoint extends EnvelopRestEndPoint {
    @Autowired
    FollowService followService;
    @Autowired
    PerinforService perinforService;

    @RequestMapping(value = ServiceApi.Follow.Follow, method = RequestMethod.GET)
    @ApiOperation(value = "获取随访记录列表", notes = "根据查询条件获取随访记录列表在前端表格展示")
    public Envelop searchfollow(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件", defaultValue = "")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "20")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request, HttpServletResponse response) {
        Envelop envelop = new Envelop();
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject seachobj = new JSONObject(filters);
            String nextFollow_s = seachobj.get("nextFollow_s").toString();
            String nextFollow_e = seachobj.get("nextFollow_e").toString();
            String peopleId = seachobj.get("peopleId").toString();
            String userId = seachobj.get("userId").toString();
            try {
                nextFollow_s = nextFollow_s.replace("null","");
                nextFollow_e = nextFollow_e.replace("null","");
                peopleId = peopleId.replace("null","");
                if (!StringUtils.isEmpty(nextFollow_s)) {
                    stringBuffer.append( "nextFollow>=" + nextFollow_s + ";");
                }
                if (!StringUtils.isEmpty(nextFollow_e)) {
                    stringBuffer.append("nextFollow<=" + nextFollow_e + ";");
                }
                if (!StringUtils.isEmpty(peopleId)) {
                    stringBuffer.append("peopleId=" + peopleId + ";");
                }
                if (!StringUtils.isEmpty(userId)) {
                    stringBuffer.append("userId=" + userId + ";");
                }
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            filters = stringBuffer.toString();
            List<FollowEntity> followList = followService.search(fields, filters, sorts, page, size);
            long count = followService.getCount(filters);
            envelop.setPageSize(size);
            envelop.setTotalCount((int)count);
            pagedResponse(request, response, followService.getCount(filters), page, size);
            envelop.setSuccessFlg(true);
            envelop.setDetailModelList(followList);
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

    @RequestMapping(value = ServiceApi.Follow.Follow, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建随访记录", notes = "绑定随访记录信息")
    public Envelop createfollow(
            @ApiParam(name = "followJsonData", value = "随访记录信息json串", defaultValue = "")
            @RequestBody String followJsonData) {
        Envelop envelop = new Envelop();
        try {
            FollowEntity follow = toEntity(followJsonData, FollowEntity.class);
            List<Perinfor> perinfor = perinforService.search("", "peopleId=" + follow.getPeopleId() + ";", "", 1, 500);
            if (perinfor.size() > 0){
                follow.setPersonalId(perinfor.get(0).getId());
                follow.setName(perinfor.get(0).getName());
                follow.setPhone(perinfor.get(0).getPhonerNum());
            }
            follow.setId(getObjectId(BizObject.User));
            follow.setCreateDate(new Date());
            follow = followService.save(follow);
            envelop.setSuccessFlg(true);
            envelop.setObj(follow);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Follow.Follow, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改随访记录", notes = "重新绑定随访记录信息")
    public Envelop updatefollow(
            @ApiParam(name = "followJsonData", value = "随访记录Json对象", defaultValue = "")
            @RequestBody String followJsonData) {
        Envelop envelop = new Envelop();
        try {
            FollowEntity follow = toEntity(followJsonData, FollowEntity.class);
            followService.save(follow);
            envelop.setSuccessFlg(true);
            envelop.setObj(follow);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Follow.FollowEntiry, method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取获取随访记录信息")
    public Envelop getfollow(
            @ApiParam(name = "followId", value = "随访记录id", defaultValue = "")
            @PathVariable(value = "followId") String followId) {
        Envelop envelop = new Envelop();
        try {
            FollowEntity follow = followService.getFollow(followId);
            envelop.setSuccessFlg(true);
            envelop.setObj(follow);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }

    @RequestMapping(value = ServiceApi.Follow.FollowEntiry, method = RequestMethod.DELETE)
    @ApiOperation(value = "删除随访记录", notes = "根据id删除随访记录")
    public Envelop deletefollow(
            @ApiParam(name = "followId", value = "随访记录id", defaultValue = "")
            @PathVariable(value = "followId") String followId) {
        Envelop envelop = new Envelop();
        try {
            followService.delete(followId);
            envelop.setSuccessFlg(true);
        } catch (Exception e) {
            envelop.setSuccessFlg(false);
            envelop.setErrorMsg(e.getMessage());
            e.printStackTrace();
        }
        return envelop;
    }


}
