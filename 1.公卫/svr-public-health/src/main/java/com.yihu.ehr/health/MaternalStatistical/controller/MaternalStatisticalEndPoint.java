package com.yihu.ehr.health.MaternalStatistical.controller;

import com.yihu.ehr.constants.ApiVersion;
import com.yihu.ehr.controller.EnvelopRestEndPoint;
import com.yihu.ehr.health.MaternalStatistical.entity.MaternalStatistical;
import com.yihu.ehr.health.MaternalStatistical.service.MaternalStatisticalService;
import com.yihu.ehr.health.Perinfor.entity.Perinfor;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.entity.FirstPrenatalDiagnosis;
import com.yihu.ehr.health.FirstPrenatalDiagnosis.service.FirstPrenatalDiagnosisService;
import com.yihu.ehr.health.PostpartumVisit.entity.PostpartumVisit;
import com.yihu.ehr.health.PostpartumVisit.service.PostpartumVisitService;
import com.yihu.ehr.health.PostpartumVisitFortytwo.entity.PostpartumVisitFortytwo;
import com.yihu.ehr.health.PostpartumVisitFortytwo.service.PostpartumVisitFortytwoService;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.service.ScheduleTwoFivePrenatalDiagnosisService;
import com.yihu.ehr.health.ScheduleTwoFivePrenatalDiagnosis.entity.ScheduleTwoFivePrenatalDiagnosis;
import com.yihu.ehr.health.util.ServiceApi;
import com.yihu.ehr.util.id.BizObject;
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
import javax.swing.text.html.parser.Entity;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = ServiceApi.Config.CrossOrigin, maxAge = 3600)
@RestController
@RequestMapping(ApiVersion.Version1_0)
@Api(value = "MaternalStatistical", description = "孕产妇统计管理", tags = {"个人资料管理"})
public class MaternalStatisticalEndPoint extends EnvelopRestEndPoint {
    @Autowired
    MaternalStatisticalService maternalStatisticalService;
    @Autowired
    PerinforService perinforService;
    @Autowired
    FirstPrenatalDiagnosisService firstPrenatalDiagnosisService;
    @Autowired
    PostpartumVisitService postpartumVisitService;
    @Autowired
    PostpartumVisitFortytwoService postpartumVisitFortytwoService;
    @Autowired
    ScheduleTwoFivePrenatalDiagnosisService scheduleTwoFivePrenatalDiagnosisService;
    @Autowired
    MaternalStatisticalEndPoint maternalStatisticalEndPoint;
    FirstPrenatalDiagnosis firstPrenatalDiagnosis=new FirstPrenatalDiagnosis();
    public Integer getYear(Date date)     //获取时间年份
    {
        SimpleDateFormat ft  = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = ft.format(date);
        String test = dateString.substring(0,4);
        Integer year = Integer.parseInt(test);
        return year;
    }

    @RequestMapping(value = ServiceApi.MaternalStatistical.MaternalStatistical, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表", notes = "根据查询条件获取用户列表在前端表格展示")
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
        List<MaternalStatistical> Datas = null;
        long count = 0;
        JSONObject seachobj = JSONObject.fromObject(filters);
        String area = seachobj.get("area").toString();
        area = area.replace("null", "");
        Datas = maternalStatisticalService.getUser(area);
        if(Datas != null)
            count = Datas.size();
        envelop.setPageSize(size);
        envelop.setTotalCount((int) count);
        envelop.setSuccessFlg(true);
        envelop.setDetailModelList(Datas);
        return envelop;
    }

/*    public MaternalStatistical upDateData(Perinfor entity, MaternalStatistical data, Integer num) {       //+1:新增  -1：删除   0：初始化

        if (num.equals(0)) {    //初始化
            data.setId(getObjectId(BizObject.User));                     //设置主键
            data.setArea(entity.getHouseAddress());                      //添加地区
            data.setYear(getYear(entity.getCreateDate()));               //添加年份
            data.setLiveBirthNumber(0);
            data.setPostpartumVisits(0);
            data.setPostpartumVisitsRate(0 + "%");
            if (entity.getLivestate().equals("存活")) {
                data.setLiveBirthNumber(1);                                  //辖区活产数
                data.setMovedNumber(0);                                  //辖区死亡数
            } else if (entity.getLivestate().equals("死亡")) {
                data.setLiveBirthNumber(0);
                data.setMovedNumber(1);
            } else {
                data.setLiveBirthNumber(0);
                data.setMovedNumber(0);
            }
            data.setCustodyWomen(0);
            data.setManagementWomen(1);                                    //年度新增人数
            data.setPermanentPopulation(1);                                //区域管理人数
        }
        return data;
    }*/

/*    public MaternalStatistical updateVisit(Perinfor entity , MaternalStatistical data , Integer num , String update ,String area){          //num 1:添加  -1：删除
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuffer MaternalStringBuffer = new StringBuffer();
        MaternalStringBuffer.append( "peopleId=" + entity.getPeopleId() + ";");
        MaternalStringBuffer.append("deleteState=" + "未删" + ";");
        String MaternalFilters=MaternalStringBuffer.toString();

        List<FirstPrenatalDiagnosis> maternalList = null;
        List<ScheduleTwoFivePrenatalDiagnosis> scheduleTwoFivePrenatalDiagnosisList = null;
        List<PostpartumVisit> postpartumVisitsList = null;
        List<PostpartumVisitFortytwo> postpartumVisitFortytwoList = null;

        try {
            maternalList = firstPrenatalDiagnosisService.search("", MaternalFilters, "", 1, 500);   //获取随访表实体
            scheduleTwoFivePrenatalDiagnosisList = scheduleTwoFivePrenatalDiagnosisService.search("", MaternalFilters, "", 1, 500);   //获取随访表实体
            postpartumVisitsList = postpartumVisitService.search("", MaternalFilters, "", 1, 500);   //获取随访表实体
            postpartumVisitFortytwoList = postpartumVisitFortytwoService.search("", MaternalFilters, "", 1, 500);   //获取随访表实体

            if(maternalList.size()>0 && update.equals("删除")){
                data.setEarlyPregnancy(data.getEarlyPregnancy()+num);    //更改管理人数
            }
            if(postpartumVisitsList.size()>0 && update.equals("删除")){
                data.setPostpartumVisits(data.getPostpartumVisits() + num);
            }
            if(postpartumVisitFortytwoList.size()>0&&update.equals("删除")){
                data.setCustodyWomen(data.getCustodyWomen() + num);
            }
            if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                data.setPostpartumVisitsRate(df.format((double) data.getPostpartumVisits() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
            else
                data.setPostpartumVisitsRate(0.00 + "%");

            if ((data.getLiveBirthNumber() != null)&&(data.getEarlyPregnancy() !=null) && !(data.getLiveBirthNumber().equals(0)))
                data.setEarlyPregnancyRate(df.format(((double)data.getEarlyPregnancy()/(double)data.getLiveBirthNumber())*100)+"%");                  //早孕建册率
            else
                data.setEarlyPregnancyRate(0.00 + "%");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (update.equals("删除") && num.equals(-1)){     //删除则删掉随访记录
            if (maternalList.size()>0) {
                for (FirstPrenatalDiagnosis item: maternalList            //个人档案删除时候随访记录伪删
                ) {
                    firstPrenatalDiagnosisService.deleteUser(item.getId());
                }
            }
            if (scheduleTwoFivePrenatalDiagnosisList.size()>0) {
                for (ScheduleTwoFivePrenatalDiagnosis item: scheduleTwoFivePrenatalDiagnosisList            //个人档案删除时候随访记录伪删
                ) {
                    scheduleTwoFivePrenatalDiagnosisService.deleteUser(item.getId());
                }
            }
            if (postpartumVisitsList.size()>0) {
                for (PostpartumVisit item: postpartumVisitsList            //个人档案删除时候随访记录伪删
                ) {
                    postpartumVisitService.deleteUser(item.getId());
                }
            }
            if (postpartumVisitFortytwoList.size()>0) {
                for (PostpartumVisitFortytwo item: postpartumVisitFortytwoList            //个人档案删除时候随访记录伪删
                ) {
                    postpartumVisitFortytwoService.deleteUser(item.getId());
                }
            }
        }

        return data;
    }
    //添加个人档案数据时调用     个人档案Service层调用
    public void addMaternalStatistical(Perinfor entity,String status) {
        StringBuffer filterStaticBuffer = new StringBuffer();           //统计筛选条件
        StringBuffer filterMaternalBuffer = new StringBuffer();         //个人档案孕产妇筛选条件
        StringBuffer areaStringBuffer = new StringBuffer();             //个人档案筛选出辖区条件
        StringBuffer liveMaternalBuffer = new StringBuffer();           //辖区内存活的孕妇的筛选条件
        StringBuffer weekBuffer = new StringBuffer();                   //用来获取第一次产前的孕周
        StringBuffer numberBuffer = new StringBuffer();                 //用来获取随访次数
        StringBuffer handleBuffer = new StringBuffer();                   //产后42天随访表结案条件



        StringBuffer idBuffer = new StringBuffer();               //查找身份证对应的人并改变数据
        DecimalFormat df = new DecimalFormat("0.00");

        List<MaternalStatistical> filterMaternalList = null;
        List<MaternalStatistical> allMaternalList = null;
        List<PostpartumVisit> postpartumVisitsList = null;
        Integer Count = 0;
        Integer MaternalCount = 0;
        Integer LiveMaternalCount=0;
        Integer DieMaternalCount=0;//关系Count-liveMaternalCount
        Integer number=0;                                         //随访次数
        Integer handlenumber=0;                                   //结案次数
        Integer Week=0;                                           //用来获取第一次产前的孕周

        filterStaticBuffer.append("area=" + entity.getHouseAddress() + ";");                 //筛选统计表
        filterStaticBuffer.append("year=" + Integer.toString(getYear(entity.getCreateDate())) + ";");


        filterMaternalBuffer.append("pregnant=" + "是" + ";");        //在个人档案中筛选出孕产妇
        filterMaternalBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");
        filterMaternalBuffer.append("deleteState=" + "未删" + ";");

        areaStringBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");                 //在个人档案中筛选出辖区
        areaStringBuffer.append("deleteState=" + "未删" + ";");

        liveMaternalBuffer.append("pregnant=" + "是" + ";");
        liveMaternalBuffer.append("livestate="+"存活"+";");                                   //选出该区域里面存活的孕妇，用于下面计算用
        liveMaternalBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");
        liveMaternalBuffer.append("deleteState=" + "未删" + ";");


        weekBuffer.append("area=" + entity.getHouseAddress() + ";");
        weekBuffer.append("gestationalWeeks<" +"13"+ ";");
        weekBuffer.append("deleteState=" + "未删" + ";");

        numberBuffer.append("area=" + entity.getHouseAddress() + ";");                 //筛选统计表
        numberBuffer.append("createDate>="+ Integer.toString(getYear(entity.getCreateDate())) +";");
        numberBuffer.append("createDate<"+ Integer.toString(getYear(entity.getCreateDate())+1) +";");
        numberBuffer.append("deleteState=" + "未删" + ";");

        handleBuffer.append("createDate>="+ Integer.toString(getYear(entity.getCreateDate())) +";");
        handleBuffer.append("createDate<"+ Integer.toString(getYear(entity.getCreateDate())+1) +";");
        handleBuffer.append("handle=" + "结案" + ";");
        handleBuffer.append("deleteState=" + "未删" + ";");

        String filterStaticFilters = filterStaticBuffer.toString();
        String filters = filterMaternalBuffer.toString();
        String areaFilters = areaStringBuffer.toString();
        String liveFilters = liveMaternalBuffer.toString();
        String weekFilters = weekBuffer.toString();
        String numberFilters = numberBuffer.toString();
        String handleFilters = handleBuffer.toString();
        try {
            filterMaternalList = maternalStatisticalService.search("", filterStaticFilters, "", 1, 500);
            allMaternalList = maternalStatisticalService.search("", "", "", 1, 500);   //获取统计所有实体
            postpartumVisitsList=postpartumVisitService.search("", numberFilters, "", 1, 500);

            MaternalCount = (int) (perinforService.getCount(filters));                 //获取个人档案所有孕产妇数量
            Count = (int) (perinforService.getCount(areaFilters));                      //获取辖区内个人档案数量
            LiveMaternalCount = (int) (perinforService.getCount(liveFilters));                 //区域里面活着的孕产妇数量
            DieMaternalCount=Count-LiveMaternalCount;                                             //区域里面死亡的孕产妇数量
            Week=(int) (firstPrenatalDiagnosisService.getCount(weekFilters));                 //获取早孕建册数
            number=postpartumVisitsList.size();                                             //获取访视数
            handlenumber=(int) (postpartumVisitFortytwoService.getCount(handleFilters));        //结案次数
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (entity.getPregnant().equals("是")) {    //当添加人口为孕妇
            if (filterMaternalList.size() == 0) {                //统计没有实体
                MaternalStatistical data = new MaternalStatistical();
                if(status.equals("修改")){
                    idBuffer.append("peopleId=" + entity.getPeopleId() + ";");
                    idBuffer.append("gestationalWeeks<" +"13"+ ";");
                    idBuffer.append("deleteState=" + "未删" + ";");
                    StringBuffer idsBuffer = new StringBuffer();
                    idsBuffer.append("peopleId=" + entity.getPeopleId() + ";");
                    idsBuffer.append("deleteState=" + "未删" + ";");
                    String idFilters = idBuffer.toString();
                    String idsFilters = idsBuffer.toString();
                    try {
                        List<FirstPrenatalDiagnosis>idList = firstPrenatalDiagnosisService.search("", idFilters, "", 1, 500);
                        List<ScheduleTwoFivePrenatalDiagnosis>idoneList = scheduleTwoFivePrenatalDiagnosisService.search("", idsFilters, "", 1, 500);
                        List<PostpartumVisit>idtwoList = postpartumVisitService.search("", idsFilters, "", 1, 500);
                        List<PostpartumVisitFortytwo>idthreeList = postpartumVisitFortytwoService.search("", idsFilters, "", 1, 500);

                        if(idList.size()!=0){
                            idList.get(0).setArea(entity.getHouseAddress());
                            idList.get(0).setName(entity.getName());
                        }
                        if(idoneList.size()!=0){
                            idoneList.get(0).setArea(entity.getHouseAddress());
                            idoneList.get(0).setName(entity.getName());
                        }
                        if(idtwoList.size()!=0){
                            idtwoList.get(0).setArea(entity.getHouseAddress());
                            idtwoList.get(0).setName(entity.getName());
                        }
                        if(idthreeList.size()!=0){
                            idthreeList.get(0).setArea(entity.getHouseAddress());
                            idthreeList.get(0).setName(entity.getName());
                                if(idthreeList.get(0).getHandle().equals("处理")){
                                    data.setCustodyWomen(1);
                                }
                        }
                        Week=(int)(idList.size());                 //获取早孕建册数
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                data.setPermanentPopulation(Count + 1);                          //新增实体类更新
                data.setManagementWomen(MaternalCount + 1);
                data.setEarlyPregnancy(Week);


                for (MaternalStatistical item : allMaternalList                 //儿童全部管理人数  统计所有实体更新  新增的实体类还没保存所以再上面更新
                ) {
                    item.setManagementWomen(MaternalCount + 1);
                    maternalStatisticalService.saveUser(item);
                }
                data = upDateData(entity, data, 0);   //初始化


                if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                    data.setEarlyPregnancyRate(df.format((double)data.getEarlyPregnancy()/(double)data.getLiveBirthNumber()*100)+"%");                  //早孕建册率
                else
                    data.setEarlyPregnancyRate(0.00 + "%");
                data.setPostpartumVisits(number);
                if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                    data.setPostpartumVisitsRate(df.format((double)data.getPostpartumVisits()/(double)data.getLiveBirthNumber()*100)+"%");                  //早孕建册率
                else
                    data.setPostpartumVisitsRate(0.00 + "%");          //产后访视率

                maternalStatisticalService.saveUser(data);
            } else {
                if (filterMaternalList.size() > 0) {
                    //统计实体已存在情况
                    MaternalStatistical data = filterMaternalList.get(0);
                    data.setManagementWomen(MaternalCount + 1);
                    data.setPermanentPopulation(Count + 1);
                    data.setEarlyPregnancy(Week);
                    if (entity.getLivestate().equals("存活")) {
                        data.setLiveBirthNumber(LiveMaternalCount + 1);
                        if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                            data.setEarlyPregnancyRate(df.format((double) data.getEarlyPregnancy() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
                        else
                            data.setEarlyPregnancyRate(0.00 + "%");
                    }
                    if (entity.getLivestate().equals("死亡")) {
                        data.setMovedNumber(DieMaternalCount + 1);
                        if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                            data.setEarlyPregnancyRate(df.format((double) data.getEarlyPregnancy() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
                        else
                            data.setEarlyPregnancyRate(0.00 + "%");
                    }
                    data.setPostpartumVisits(number);
                    if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                        data.setPostpartumVisitsRate(df.format((double) data.getPostpartumVisits() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
                    else
                        data.setPostpartumVisitsRate(0.00 + "%");          //产后访视率
                    data.setCustodyWomen(handlenumber);
                    maternalStatisticalService.saveUser(data);
                }
            }
        } else {
            if(filterMaternalList.size()==0){
                MaternalStatistical data = new MaternalStatistical();
                data.setId(getObjectId(BizObject.User));                     //设置主键
                data.setArea(entity.getHouseAddress());                      //添加地区
                data.setYear(getYear(entity.getCreateDate()));               //添加年份
                data.setPermanentPopulation(1);                             //新增实体类更新
                data.setLiveBirthNumber(0);
                data.setEarlyPregnancy(0);
                data.setEarlyPregnancyRate(0.00 + "%");
                data.setManagementWomen(0);
                data.setPostpartumVisits(0);
                data.setPostpartumVisitsRate(0.00 + "%");          //产后访视率
                data.setCustodyWomen(0);
                data.setMovedNumber(0);
                maternalStatisticalService.saveUser(data);             //保存更新数据
            }
            if(filterMaternalList.size()>0){
                MaternalStatistical data = filterMaternalList.get(0);
                data.setPermanentPopulation(Count + 1);                             //新增实体类更新
                maternalStatisticalService.saveUser(data);             //保存更新数据
            }
        }
    }

    //删除个人档案数据时候调用         个人档案Service层调用
    public void deleteMaternalStatistic(Perinfor entity,String status) {
        StringBuffer filterMaternalBuffer = new StringBuffer();               //个人档案孕产妇筛选条件
        StringBuffer areaStringBuffer = new StringBuffer();                   //个人档案辖区所有人的筛选条件
        StringBuffer liveMaternalBuffer = new StringBuffer();                 //辖区内存活的孕妇的筛选条件
        StringBuffer filterStaticBuffer = new StringBuffer();                 //统计筛选条件
        DecimalFormat df = new DecimalFormat("0.00");
        List<MaternalStatistical> filterMaternalList = null;

        filterMaternalBuffer.append("pregnant=" + "是" + ";");        //通过是否怀孕筛选出孕产妇
        filterMaternalBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");
        filterMaternalBuffer.append("deleteState=" + "未删" + ";");

        areaStringBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");     //筛选辖区条件
        areaStringBuffer.append("deleteState=" + "未删" + ";");

        liveMaternalBuffer.append("livestate=" + "存活" + ";");           //选出该区域里面存活的孕妇，用于下面计算用
        liveMaternalBuffer.append("houseAddress=" + entity.getHouseAddress() + ";");
        liveMaternalBuffer.append("deleteState=" + "未删" + ";");

        filterStaticBuffer.append("area=" + entity.getHouseAddress() + ";");                 //筛选统计表
        filterStaticBuffer.append("year=" + Integer.toString(getYear(entity.getCreateDate())) + ";");


        String filters = filterMaternalBuffer.toString();
        String areaFilters = areaStringBuffer.toString();
        String liveFilters = liveMaternalBuffer.toString();
        String filterStaticFilters = filterStaticBuffer.toString();


        Integer Count = 0;
        Integer MaternalCount = 0;
        Integer LiveMaternalCount = 0;
        Integer DieMaternalCount = 0;     //关系Count-liveMaternalCount

        try {
            filterMaternalList = maternalStatisticalService.search("", filterStaticFilters, "", 1, 500);
            MaternalCount = (int) (perinforService.getCount(filters));                 //获取个人档案所有孕产妇数量
            Count = (int) (perinforService.getCount(areaFilters));                     //获取辖区内个人档案数量
            LiveMaternalCount = (int) (perinforService.getCount(liveFilters));                 //区域里面活着的孕产妇数量
            DieMaternalCount = Count - LiveMaternalCount;                                             //区域里面死亡的孕产妇数量
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(filterMaternalList.size()>0) {
            MaternalStatistical data = filterMaternalList.get(0);
            data.setPermanentPopulation(Count - 1);                          //辖区人口

            if (entity.getPregnant().equals("是")) {    //删除人口为孕妇
                if (filterMaternalList.size() == 1) {               //存在统计实体
                    data.setManagementWomen(MaternalCount - 1);
                    if (entity.getLivestate().equals("存活")) {
                        data.setLiveBirthNumber(LiveMaternalCount - 1);
                    } else {
                        data.setMovedNumber(DieMaternalCount - 1);
                    }
                    *//*data.setPostpartumVisits(number);*//*
                    if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                        data.setPostpartumVisitsRate(df.format((double) data.getPostpartumVisits() / (double) data.getLiveBirthNumber() * 100) + "%");                  //产后访视率
                    else
                        data.setPostpartumVisitsRate(0.00 + "%");

                    data = upDateData(entity, data, -1);                   //更新统计 除随访记录
                    if(!status.equals("修改"))
                        data = updateVisit(entity,data,-1 , "","");    //更新统计随访记录

                    if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                        data.setEarlyPregnancyRate(df.format(((double) data.getEarlyPregnancy() / (double) data.getLiveBirthNumber()) * 100) + "%");                  //早孕建册率
                    else
                        data.setEarlyPregnancyRate(0.00 + "%");
                }
            }
            if (data.getPermanentPopulation() > 0)              //辖区孕妇管理人数还有，统计就存在
                maternalStatisticalService.saveUser(data);             //保存更新数据
            else
                maternalStatisticalService.deleteUser(data.getId());   //辖区孕妇管理人数为0直接删掉统计   为0统计无意义

        }
    }


    //修改个人档案数据时候调用         个人档案Service层调用
    public void editMaternaleStatistic(Perinfor beforeEntiy,Perinfor entity) {
        StringBuffer filterStaticBuffer = new StringBuffer();           //统计筛选条件
        DecimalFormat df = new DecimalFormat("0.00");
        List<MaternalStatistical> filterMaternalList = null;
        List<MaternalStatistical> allMaternalList = null;
        MaternalStatistical data = null;


        filterStaticBuffer.append("area=" + entity.getHouseAddress() + ";");                 //筛选统计表
        filterStaticBuffer.append("year=" + Integer.toString(getYear(entity.getCreateDate())) + ";");


        String filterStaticFilters = filterStaticBuffer.toString();

        try {
            filterMaternalList = maternalStatisticalService.search("", filterStaticFilters, "", 1, 500);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(filterMaternalList.size()>0)
            data = filterMaternalList.get(0);
        if (data != null) {
            if (beforeEntiy.getHouseAddress().equals(entity.getHouseAddress())) {
                if(entity.getPregnant().equals("是")){
                    if (beforeEntiy.getLivestate().equals("存活") && entity.getLivestate().equals("死亡")) {
                        data.setLiveBirthNumber(data.getLiveBirthNumber() - 1);
                        data.setMovedNumber(data.getMovedNumber() + 1);
                    }
                    if (beforeEntiy.getLivestate().equals("死亡") && entity.getLivestate().equals("存活")) {
                        data.setLiveBirthNumber(data.getLiveBirthNumber() + 1);
                        data.setMovedNumber(data.getMovedNumber() - 1);
                    }
                }
                if (beforeEntiy.getPregnant().equals("否") && entity.getPregnant().equals("是")) {
                    data.setManagementWomen(data.getManagementWomen() + 1);
                    if (entity.getLivestate().equals("存活")) {
                        data.setLiveBirthNumber(data.getLiveBirthNumber() + 1);
                    } else {
                        data.setMovedNumber(data.getMovedNumber() + 1);
                    }
                }
                if (beforeEntiy.getPregnant().equals("是") && entity.getPregnant().equals("否")) {
                    data.setManagementWomen(data.getManagementWomen() - 1);
                    if (entity.getLivestate().equals("存活")) {
                        data.setLiveBirthNumber(data.getLiveBirthNumber() - 1);
                    } else {
                        data.setMovedNumber(data.getMovedNumber() - 1);
                    }
                }
            }
            if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                data.setPostpartumVisitsRate(df.format((double) data.getPostpartumVisits() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
            else
                data.setPostpartumVisitsRate(0.00 + "%");
            if(!beforeEntiy.getHouseAddress().equals(entity.getHouseAddress())) {

                try {
                    filterMaternalList = maternalStatisticalService.search("", filterStaticFilters, "", 1, 500);
                    if (filterMaternalList.size() > 0)
                        data = filterMaternalList.get(0);    //获取之前的统计实体
                    if ( data != null && data.getPermanentPopulation() < 0)
                        maternalStatisticalService.deleteUser(data.getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                maternalStatisticalEndPoint.addMaternalStatistical(entity,"");
                maternalStatisticalEndPoint.deleteMaternalStatistic(beforeEntiy,"");
            }
        }else{
            if(!beforeEntiy.getHouseAddress().equals(entity.getHouseAddress())) {
                try {
                    filterMaternalList = maternalStatisticalService.search("", filterStaticFilters, "", 1, 500);
                    if (filterMaternalList.size() > 0)
                        data = filterMaternalList.get(0);    //获取之前的统计实体
                    if ( data != null && data.getPermanentPopulation() < 0)
                        maternalStatisticalService.deleteUser(data.getId());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                maternalStatisticalEndPoint.addMaternalStatistical(entity,"修改");
                maternalStatisticalEndPoint.deleteMaternalStatistic(beforeEntiy,"");
            }
        }
    }*/
}


