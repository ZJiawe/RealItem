package com.yihu.ehr.health.ChildrenStatistic.service;

import com.yihu.ehr.health.ChildrenStatistic.dao.XChildrenStatisticRepository;
import com.yihu.ehr.health.ChildrenStatistic.entity.ChildrenStatistics;
import com.yihu.ehr.health.NewbornVisit.dao.XNewbornVisitRepository;
import com.yihu.ehr.health.NewbornVisit.entity.NewbornVisit;
import com.yihu.ehr.health.NewbornVisit.service.NewbornVisitService;
import com.yihu.ehr.health.OneEightMonthchild.dao.XOneEightMonthChildRepository;
import com.yihu.ehr.health.OneEightMonthchild.entity.OneEightMonthChild;
import com.yihu.ehr.health.OneEightMonthchild.service.OneEightMonthChildService;
import com.yihu.ehr.health.Perinfor.service.PerinforService;
import com.yihu.ehr.health.ThreeSixYearChild.dao.XThreeSixYearChildRepository;
import com.yihu.ehr.health.ThreeSixYearChild.entity.ThreeSixYearChild;
import com.yihu.ehr.health.ThreeSixYearChild.service.ThreeSixYearChildService;
import com.yihu.ehr.health.TwelveThsixEightMonth.dao.XTwelveThsixEightMonthChildRepository;
import com.yihu.ehr.health.TwelveThsixEightMonth.entity.TwelveThsixEightMonthChild;
import com.yihu.ehr.health.TwelveThsixEightMonth.service.TwelveThsixEightMonthChildService;
import com.yihu.ehr.query.BaseJpaService;
import com.yihu.ehr.util.rest.Envelop;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChildrenStatisticesService extends BaseJpaService<ChildrenStatistics, XChildrenStatisticRepository> {
    @Autowired
    XChildrenStatisticRepository childrenStatisticRepository;
    @Autowired
    PerinforService perinforService;
    @Autowired
    OneEightMonthChildService oneEightMonthChildService;
    @Autowired
    TwelveThsixEightMonthChildService twelveThsixEightMonthChildService;
    @Autowired
    ThreeSixYearChildService threeSixYearChildService;
    @Autowired
    NewbornVisitService newbornVisitService;
    @Autowired
    XNewbornVisitRepository newbornVisitRepository;
    @Autowired
    XOneEightMonthChildRepository oneEightMonthChildRepository;
    @Autowired
    XThreeSixYearChildRepository threeSixYearChildRepository;
    @Autowired
    XTwelveThsixEightMonthChildRepository twelveThsixEightMonthChildRepository;

    public  List<ChildrenStatistics> getUser(String  filter) {
        List<ChildrenStatistics> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int PeopleCount = 0;
        Envelop envelop = new Envelop();
        String peopleSQL=
                "select count(1) as childrenCount "+
                "from perinfor "+
                "where delete_state=:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0 ";

        String areaSQL=
        "select area,yeara,childCount,areaCount,liveCount,dieCount,newCount " +
                "from( " +
                "select area,yeara,max(childCount) as childCount,max(areaCount) as areaCount,max(liveCount) as liveCount,max(dieCount) as dieCount,max(newCount) as newCount " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'childCount',0 'areaCount',0 'liveCount',0 'dieCount',0 'newCount' " +
                "from perinfor  " +
                "where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0  " +
                "group by house_address,year(create_date) " +
                "UNION ALL " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'areaCount',null,null,null " +
                "from perinfor  " +
                "where delete_state =:delete_state  " +
                "group by house_address,year(create_date) " +
                "    UNION ALL " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'liveCount',null,null " +
                "from perinfor  " +
                "        where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0 and livestate=:liveState " +
                "        group by house_address,year(create_date) " +
                "    UNION ALL  " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'dieCount',null " +
                "from perinfor  " +
                "        where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0 and livestate=:dieState " +
                "        group by house_address,year(create_date) " +
                "    UNION ALL " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'newCount' " +
                "from perinfor  " +
                "where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0  " +
                "group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as childrenDB " +
                "where childrenDB.childCount is  not null";


        String areaSQLByfilter=
                "select area,yeara,childCount,areaCount,liveCount,dieCount,newCount " +
                        "from( " +
                        "select area,yeara,max(childCount) as childCount,max(areaCount) as areaCount,max(liveCount) as liveCount,max(dieCount) as dieCount,max(newCount) as newCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'childCount',0 'areaCount',0 'liveCount',0 'dieCount',0 'newCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara',null,count(1) 'areaCount',null,null,null " +
                        "from perinfor  " +
                        "where delete_state =:delete_state  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'liveCount',null,null " +
                        "from perinfor  " +
                        "        where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0 and livestate=:liveState " +
                        "        group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL  " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'dieCount',null " +
                        "from perinfor  " +
                        "        where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0 and livestate=:dieState " +
                        "        group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'newCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0  " +
                        "group by house_address,year(create_date) " +
                        "   " +
                        ") as computer " +
                        "group by computer.area , computer.yeara) as childrenDB " +
                        "where childrenDB.childCount is  not null and childrenDB.area like :area";


        String Vistit =
                   "select count(1) '辖区访问人数' " +
                           "from " +
                           "(select * " +
                           "from " +
                           "( " +
                           "select id " +
                           "from " +
                           "(select * " +
                           "from " +
                           "(select perinfor.people_id as id " +
                           "from perinfor " +
                           "where delete_state=:delete_state and house_address =:house_address and year(create_date) =:create_date " +
                           "and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0) as perinforChild  " +
                           "left join " +
                           "( " +
                           "select one_eight_month_child.people_id as oid " +
                           "from  one_eight_month_child " +
                           "    where delete_state=:delete_state " +
                           "    group by one_eight_month_child.people_id " +
                           ")as oneChildren  " +
                           "on perinforChild.id = oneChildren.oid) as oneChildVisit " +
                           "UNION ALL " +
                           " " +
                           "select id " +
                           "from " +
                           "(select * " +
                           "from " +
                           "(select perinfor.people_id as id " +
                           "from perinfor " +
                           "where delete_state=:delete_state and house_address =:house_address and year(create_date) =:create_date " +
                           "and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0) as perinforChild  " +
                           "left join " +
                           "( " +
                           "select three_six_year_child.people_id as oid " +
                           "from  three_six_year_child " +
                           "    where delete_state=:delete_state " +
                           "    group by three_six_year_child.people_id " +
                           ")as threeChildren  " +
                           "on perinforChild.id = threeChildren.oid) as threeChildVisit " +
                           " " +
                           "UNION ALL " +
                           "select id " +
                           "from " +
                           "(select * " +
                           "from " +
                           "(select perinfor.people_id as id " +
                           "from perinfor " +
                           "where delete_state=:delete_state and house_address =:house_address and year(create_date) =:create_date " +
                           "and timestampdiff(hour,birth,date_add(now(), interval -6 year))<0) as perinforChild  " +
                           "left join " +
                           "( " +
                           "select twelve_thsix_eight_month_child.people_id as oid " +
                           "from  twelve_thsix_eight_month_child " +
                           "    where delete_state=:delete_state " +
                           "    group by twelve_thsix_eight_month_child.people_id " +
                           ")as twelveChildren  " +
                           "on perinforChild.id = twelveChildren.oid) as twelveChildVisit " +
                           ") as allchildrenVisit " +
                           "group by allchildrenVisit.id) as totalVisit";


        SQLQuery queryPerson = currentSession().createSQLQuery(peopleSQL);
        queryPerson.setParameter("delete_state","未删");
        SQLQuery queryArea = null;

        List<Object> peoplelist = queryPerson.list();

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("liveState","存活");
                queryArea.setParameter("dieState","死亡");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
            queryArea = currentSession().createSQLQuery(areaSQLByfilter);
            queryArea.setParameter("delete_state","未删");
            queryArea.setParameter("liveState","存活");
            queryArea.setParameter("dieState","死亡");
            queryArea.setParameter("area","%"+filter+"%");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }


        List<Object> Arealist = queryArea.list();

        if(peoplelist.size()>0){                      //0~6岁儿童健康管理人数
            try {
                Object obj = peoplelist.get(0);
                PeopleCount = Integer.parseInt(obj.toString());
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }

        for (Object item:Arealist
             ) {
            Object[] obj = (Object[])item;
            ChildrenStatistics data = new ChildrenStatistics();
            data.setMangePeople(PeopleCount);                            //0~6岁儿童健康管理人数
            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setAreaMangePeople(Integer.parseInt(obj[2].toString()));//辖区儿童人数
            data.setPeopleCount(Integer.parseInt(obj[3].toString()));    //辖区总人数即常驻人口
            data.setLiveCount(Integer.parseInt(obj[4].toString()));      //辖区内活产数
            data.setDiePeople(Integer.parseInt(obj[5].toString()));      //辖区内死亡数
            data.setNewbornYear(Integer.parseInt(obj[6].toString()));    //年度新增人数

            SQLQuery queryVistit = currentSession().createSQLQuery(Vistit);

            try {
            queryVistit.setParameter("delete_state","未删");
            queryVistit.setParameter("create_date",""+data.getYear()+"");
            queryVistit.setParameter("house_address",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            List<Object> Vistitlist = queryVistit.list();

            if(Vistitlist.size()>0){
                try {
                 Object visitobj = Vistitlist.get(0);
                data.setNewbornVisit(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }

            if ((data.getLiveCount() != null) && !(data.getLiveCount().equals(0)))
                data.setVisitRatio(df.format(((double)data.getNewbornVisit() / (double)data.getLiveCount()) * 100) + "%");   //新生儿访问率
            else
                data.setVisitRatio(0.00 + "%");
            if ((data.getMangePeople() != null) && !(data.getMangePeople().equals(0)))
                data.setManageVisitRatio(df.format(((double)data.getNewbornVisit() / (double)data.getMangePeople()) * 100) + "%");  //新生儿管理率
            else
                data.setManageVisitRatio(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }

    public void deleteChildrenVisit(String PeopleId){
        StringBuffer newStringBuffer = new StringBuffer();
        StringBuffer oneStringBuffer = new StringBuffer();
        StringBuffer threeStringBuffer = new StringBuffer();
        StringBuffer tweleveStringBuffer = new StringBuffer();

        newStringBuffer.append("peopleId=" + PeopleId + ";");
        newStringBuffer.append("deleteState=" + "未删" + ";");

        oneStringBuffer.append("peopleId=" + PeopleId + ";");
        oneStringBuffer.append("deleteState=" + "未删" + ";");

        threeStringBuffer.append("peopleId=" + PeopleId + ";");
        threeStringBuffer.append("deleteState=" + "未删" + ";");

        tweleveStringBuffer.append("peopleId=" + PeopleId + ";");
        tweleveStringBuffer.append("deleteState=" + "未删" + ";");

        String oneFilters = oneStringBuffer.toString();
        String newFilters = newStringBuffer.toString();
        String threeFilters = threeStringBuffer.toString();
        String tweleveFilters = tweleveStringBuffer.toString();
        List<OneEightMonthChild> oneChildList = null;
        List<ThreeSixYearChild> threeChildrenList = null;
        List<TwelveThsixEightMonthChild> tweleveChildrenList = null;
        List<NewbornVisit> newbornVisitsList = null;
        Envelop envelop = new Envelop();

        try {
            newbornVisitsList = newbornVisitService.search("", newFilters, "", 1, 500);
            oneChildList = newbornVisitService.search("", oneFilters, "", 1, 500);
            threeChildrenList = newbornVisitService.search("", threeFilters, "", 1, 500);
            tweleveChildrenList = newbornVisitService.search("", tweleveFilters, "", 1, 500);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (newbornVisitsList != null) {
            for (NewbornVisit item : newbornVisitsList            //个人档案删除时候随访记录伪删
            ) {
                try{
                    item.setDeleteState("已删");
                    newbornVisitRepository.save(item);
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        if (oneChildList != null) {
            for (OneEightMonthChild item : oneChildList            //个人档案删除时候随访记录伪删
            ) {
                item.setDeleteState("已删");
                oneEightMonthChildRepository.save(item);
            }
        }

        if (threeChildrenList != null) {
            for (ThreeSixYearChild item : threeChildrenList    //个人档案删除时候随访记录伪删
            ) {
                item.setDeleteState("已删");
                threeSixYearChildRepository.save(item);
            }
        }

        if (tweleveChildrenList != null) {
            for (TwelveThsixEightMonthChild item : tweleveChildrenList    //个人档案删除时候随访记录伪删
            ) {
                item.setDeleteState("已删");
                twelveThsixEightMonthChildRepository.save(item);
            }
        }

    }

}
