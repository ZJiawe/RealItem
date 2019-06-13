package com.yihu.ehr.health.MedicalStatistical.service;

import com.yihu.ehr.health.ChildrenStatistic.dao.XChildrenStatisticRepository;
import com.yihu.ehr.health.MedicalStatistical.entity.MedicalStatistical;
import com.yihu.ehr.query.BaseJpaService;
import com.yihu.ehr.util.rest.Envelop;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MedicalStatisticalService extends BaseJpaService<MedicalStatistical, XChildrenStatisticRepository> {

    public  List<MedicalStatistical> getUser(String  filter) {
        List<MedicalStatistical> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        Envelop envelop = new Envelop();
        SQLQuery queryArea = null;

        String areaSQL=
        "select area,yeara,oldCount,areaCount,childrenCount " +
                "from( " +
                "select area,yeara,max(oldCount) as oldCount,max(areaCount) as areaCount,max(childrenCount) as childrenCount " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'oldCount',0 'areaCount',0 'childrenCount' " +
                "from perinfor  " +
                "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',0,count(1) 'areaCount',0 " +
                "from perinfor  " +
                "where delete_state =:delete_state  " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',0,0,count(1) 'childrenCount' " +
                "from perinfor  " +
                "        where delete_state=:delete_state and timestampdiff(hour,birth,date_add(now(), interval -3 year))<0 and livestate=:livestate " +
                "        group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as oldrenDB";
               

        String areaSQLByfilter=
                "select area,yeara,oldCount,areaCount,childrenCount " +
                        "from( " +
                        "select area,yeara,oldCount,areaCount,childrenCount " +
                        "from( " +
                        "select area,yeara,max(oldCount) as oldCount,max(areaCount) as areaCount,max(childrenCount) as childrenCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'oldCount',0 'areaCount',0 'childrenCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,count(1) 'areaCount',0 " +
                        "from perinfor  " +
                        "where delete_state =:delete_state  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,0,count(1) 'childrenCount' " +
                        "from perinfor  " +
                        "        where delete_state=:delete_state and timestampdiff(hour,birth,date_add(now(), interval -3 year))<0 and livestate=:livestate " +
                        "        group by house_address,year(create_date) " +
                        ") as computer " +
                        "group by computer.area , computer.yeara) as oldrenDB) filterArea " +
                        "    where filterArea.area like :area";

        String VistitChildren =
                "select count(id) as '管理人数',count(case when result.medicine <> '' then 1 else null end) '中医药管理人数' " +
                "from( " +
                "select id ,max(medicine) 'medicine' " +
                "from ( " +
                " " +
                "select id,oid,medicine " +
                "from( " +
                "( " +
                "select perinfor.people_id as id " +
                "from perinfor " +
                "where delete_state=:delete_state and timestampdiff(hour,birth,date_add(now(), interval -3 year))<0  and  house_address =:house_address and year(create_date) =:year " +
                ") as perinforChild  " +
                "left join " +
                "( " +
                "select one_eight_month_child.people_id as oid,max(medicine) 'medicine' " +
                "from  one_eight_month_child " +
                "    where delete_state=:delete_state " +
                "    group by one_eight_month_child.people_id " +
                ")as oneChildren  " +
                "on perinforChild.id = oneChildren.oid  ) " +
                "where oid is not null  " +
                " " +
                "UNION ALL   " +
                " " +
                "select id,oid,medicine " +
                "from( " +
                "( " +
                "select perinfor.people_id as id " +
                "from perinfor " +
                "where delete_state=:delete_state and timestampdiff(hour,birth,date_add(now(), interval -3 year))<0   " +
                ") as perinforChild  " +
                "left join " +
                "( " +
                "select twelve_thsix_eight_month_child.people_id as oid,max(medicine) 'medicine' " +
                "from  twelve_thsix_eight_month_child " +
                "    where delete_state=:delete_state " +
                "    group by twelve_thsix_eight_month_child.people_id " +
                ")as twelveChildren  " +
                "on perinforChild.id = twelveChildren.oid) " +
                "where oid is not null " +
                " " +
                ") as childall " +
                "group by childall.id) as result";

        String VistitOld = 
                "select count(id) as '管理人数',count(case when result.chmedicine_guidance <> '' then 1 else null end) '中医药管理人数' " +
                        "from( " +
                        "select id ,max(chmedicine_guidance) 'chmedicine_guidance' " +
                        "from ( " +
                        " " +
                        "select id,oid,chmedicine_guidance " +
                        "from( " +
                        "( " +
                        "select perinfor.people_id as id " +
                        "from perinfor " +
                        "where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65  and  house_address =:house_address and year(create_date) =:year " +
                        ") as perinforChild  " +
                        "left join " +
                        "( " +
                        "select health_management_ecord.people_id as oid,max(chmedicine_guidance) 'chmedicine_guidance' " +
                        "from  health_management_ecord " +
                        "    where delete_state=:delete_state " +
                        "    group by health_management_ecord.people_id " +
                        ")as oneChildren   " +
                        "on perinforChild.id = oneChildren.oid  ) " +
                        "where oid is not null  " +
                        " " +
                        ") as elderAll " +
                        "group by elderAll.id) as result";

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("livestate","存活");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
            queryArea = currentSession().createSQLQuery(areaSQLByfilter);
            queryArea.setParameter("delete_state","未删");
            queryArea.setParameter("livestate","存活");
            queryArea.setParameter("area","%"+filter+"%");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }

        List<Object> Arealist = queryArea.list();

        for (Object item:Arealist
             ) {
            Object[] obj = (Object[])item;
            MedicalStatistical data = new MedicalStatistical();
            data.setArea(obj[0].toString());                             //设定地区
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setElderCount(Integer.parseInt(obj[2].toString()));     //辖区老人数量
            data.setPeopleCount(Integer.parseInt(obj[3].toString()));    //辖区总人数即常驻人口
            data.setChildrenCount(Integer.parseInt(obj[4].toString()));  //辖区0~3岁儿童数量

            SQLQuery ChildrenVistit = currentSession().createSQLQuery(VistitChildren);
            SQLQuery ElderVistit = currentSession().createSQLQuery(VistitChildren);

            try {
                ChildrenVistit.setParameter("delete_state","未删");
                ChildrenVistit.setParameter("year",""+data.getYear()+"");
                ChildrenVistit.setParameter("house_address",""+data.getArea()+"");

                ElderVistit.setParameter("delete_state","未删");
                ElderVistit.setParameter("year",""+data.getYear()+"");
                ElderVistit.setParameter("house_address",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            List<Object> childrenlist = ChildrenVistit.list();
            List<Object> elderlist = ElderVistit.list();

            for (Object chldrenItem:childrenlist
                 ) {
                Object[] childrenobj = (Object[])chldrenItem;
                data.setChildrenMangerCount(Integer.parseInt(childrenobj[0].toString()));
                data.setChildrenCountByMedical(Integer.parseInt(childrenobj[1].toString()));
            }

            for (Object elderItem:elderlist
            ) {
                Object[] elderobj = (Object[])elderItem;
                data.setElderMangerCount(Integer.parseInt(elderobj[0].toString()));
                data.setElderCountByMedical(Integer.parseInt(elderobj[1].toString()));
            }

            if ((data.getChildrenCountByMedical() != null) && !(data.getChildrenCountByMedical().equals(0)))
                data.setChildrenCountByMedicalRato(df.format(((double)data.getChildrenMangerCount() / (double)data.getChildrenCountByMedical()) * 100) + "%");   //新生儿访问率
            else
                data.setChildrenCountByMedicalRato(0.00 + "%");

            if ((data.getElderCountByMedical() != null) && !(data.getElderCountByMedical().equals(0)))
                data.setElderCountByMedicalRato(df.format(((double)data.getElderMangerCount() / (double)data.getElderCountByMedical()) * 100) + "%");  //新生儿管理率
            else
                data.setElderCountByMedicalRato(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }

}
