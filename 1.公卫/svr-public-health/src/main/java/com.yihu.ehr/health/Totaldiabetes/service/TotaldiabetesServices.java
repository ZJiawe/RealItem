package com.yihu.ehr.health.Totaldiabetes.service;

import com.yihu.ehr.health.Totaldiabetes.entity.Totaldiabetes;
import com.yihu.ehr.health.Totaldiabetes.dao.XTotaldiabetesRepository;
import com.yihu.ehr.query.BaseJpaService;
import com.yihu.ehr.util.rest.Envelop;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TotaldiabetesServices extends BaseJpaService<Totaldiabetes, XTotaldiabetesRepository> {
    @Autowired
    XTotaldiabetesRepository totaldiabetesRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     */
    public List<Totaldiabetes> getTotaldiabetes(String filter) {
        List<Totaldiabetes> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        Envelop envelop = new Envelop();
        int PeopleCount = 0;
        String peopleSQL=
                "select count(1) " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like :past_history";

        String areaTotal=
                "select count(1) " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like :past_history and house_address =:area";

        String areaSQL=
                "select area,yeara,diabeteCount,areaCount,dieCount " +
                        "from( " +
                        "select area,yeara,max(diabeteCount) as diabeteCount,max(areaCount) as areaCount,max(dieCount) as dieCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'diabeteCount',0 'areaCount',0 'dieCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and past_history like :past_history " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,count(1) 'areaCount',0 " +
                        "from perinfor  " +
                        "where delete_state =:delete_state  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,0,count(1) 'dieCount' " +
                        "from perinfor  " +
                        "        where delete_state=:delete_state and  past_history like :past_history and livestate=:livestate " +
                        "        group by house_address,year(create_date) " +
                        ") as computer " +
                        "group by computer.area , computer.yeara) as diabeterenDB";


        String areaSQLByfilter=
                "select * " +
                        "from( " +
                        "select area,yeara,diabeteCount,areaCount,dieCount " +
                        "from( " +
                        "select area,yeara,max(diabeteCount) as diabeteCount,max(areaCount) as areaCount,max(dieCount) as dieCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'diabeteCount',0 'areaCount',0 'dieCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and past_history like :past_history " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,count(1) 'areaCount',0 " +
                        "from perinfor  " +
                        "where delete_state =:delete_state  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',0,0,count(1) 'dieCount' " +
                        "from perinfor  " +
                        "        where delete_state=:delete_state and  past_history like :past_history and livestate=:livestate " +
                        "        group by house_address,year(create_date) " +
                        ") as computer " +
                        "group by computer.area , computer.yeara) as diabeterenDB) as totaldiabeteren " +
                        "where totaldiabeteren.area =:area";


        String Vistit =
                "select count(case when diabetesAll.compliance_behavior = '良好' then 1 else null end) '年度2型糖尿病患者管理人数', " +
                        "count(case when diabetesAll.fasting_blood < 7 then 1 else null end) '年度内2型糖尿并患者规范管理人数' " +
                        "from " +
                        "( " +
                        " " +
                        "select * " +
                        "from ( " +
                        "(select perinfor.people_id as id " +
                        "from perinfor " +
                        "where delete_state=:delete_state and past_history like :past_history  and  house_address =:area and year(create_date) =:year " +
                        ") as perinforChild  " +
                        "left join " +
                        "( " +
                        "select diabetes.people_id as oid,compliance_behavior,fasting_blood " +
                        "from  diabetes " +
                        "    where delete_state=:delete_state " +
                        "    group by diabetes.people_id " +
                        ")as diabetesTatal   " +
                        "on perinforChild.id = diabetesTatal.oid) " +
                        " " +
                        ") as diabetesAll";

        String Vistit1 =
                "select count(case when diabetesAll.compliance_behavior = '良好' then 1 else null end) '年度2型糖尿病患者管理人数' " +
                        "from " +
                        "( " +
                        " " +
                        "select * " +
                        "from ( " +
                        "(select perinfor.people_id as id " +
                        "from perinfor " +
                        "where delete_state=:delete_state and past_history like :past_history  and  house_address =:area and year(create_date) =:year " +
                        ") as perinforChild  " +
                        "left join " +
                        "( " +
                        "select diabetes.people_id as oid,compliance_behavior,fasting_blood " +
                        "from  diabetes " +
                        "    where delete_state=:delete_state  " +
                        "    order by create_date desc " +
                        ")as diabetesTatal   " +
                        "on perinforChild.id = diabetesTatal.oid " +
                        ") " +
                        "group by id " +
                        ") as diabetesAll";


        SQLQuery queryPerson = currentSession().createSQLQuery(peopleSQL);
        queryPerson.setParameter("delete_state","未删");
        queryPerson.setParameter("past_history","%糖尿病%");

        SQLQuery queryArea = null;
        List<Object> peoplelist = queryPerson.list();

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("past_history","%糖尿病%");
                queryArea.setParameter("livestate","死亡");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                queryArea = currentSession().createSQLQuery(areaSQLByfilter);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("livestate","死亡");
                queryArea.setParameter("past_history","%糖尿病%");
                queryArea.setParameter("area","%"+filter+"%");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }

        List<Object> Arealist = queryArea.list();

        if(peoplelist.size()>0){                      //糖尿病健康管理人数
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
            Totaldiabetes data = new Totaldiabetes();
            data.setPeoples(PeopleCount);                                //糖尿病健康管理人数
            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setAddPeople(Integer.parseInt(obj[2].toString()));     //辖区糖尿病人数
            data.setPeopleCount(Integer.parseInt(obj[3].toString()));    //辖区总人数即常驻人口
            data.setDiePeople(Integer.parseInt(obj[4].toString()));      //辖区内死亡数

            SQLQuery queryVistit = currentSession().createSQLQuery(Vistit);
            SQLQuery queryAreaCount = currentSession().createSQLQuery(areaTotal);
            SQLQuery queryVistit1 = currentSession().createSQLQuery(Vistit1);

            try {
                queryVistit.setParameter("delete_state","未删");
                queryVistit.setParameter("year",""+data.getYear()+"");
                queryVistit.setParameter("past_history","%糖尿病%");
                queryVistit.setParameter("area",""+data.getArea()+"");

                queryVistit1.setParameter("delete_state","未删");
                queryVistit1.setParameter("year",""+data.getYear()+"");
                queryVistit1.setParameter("past_history","%糖尿病%");
                queryVistit1.setParameter("area",""+data.getArea()+"");

                queryAreaCount.setParameter("delete_state","未删");
                queryAreaCount.setParameter("past_history","%糖尿病%");
                queryAreaCount.setParameter("area",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            List<Object> Vistitlist = queryVistit.list();
            List<Object> Vistit1list = queryVistit1.list();
            List<Object> AreaCountlist = queryAreaCount.list();

            for (Object Vistititem: Vistitlist
                 ) {
                Object[] Vistitobj = (Object[])Vistititem;
                data.setYearPeople(Integer.parseInt(Vistitobj[0].toString()));
                data.setMangePeople(Integer.parseInt(Vistitobj[1].toString()));
            }

            if(Vistit1list.size()>0){
                try {
                    Object visitobj = Vistit1list.get(0);
                    data.setNearPeople(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }

            if(AreaCountlist.size()>0){
                try {
                    Object Areaobj = AreaCountlist.get(0);
                    data.setAreaPeople(Integer.parseInt(Areaobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }

            if ((data.getYearPeople() != null) && !(data.getYearPeople().equals(0)))
                data.setMangeRate(df.format(((double)data.getMangePeople() / (double)data.getYearPeople()) * 100) + "%");   //新生儿访问率
            else
                data.setMangeRate(0.00 + "%");

            if ((data.getMangePeople() != null) && !(data.getMangePeople().equals(0)))
                data.setControlRate(df.format(((double)(data.getAreaPeople()-data.getAreaPeople()) / (double)data.getMangePeople()) * 100) + "%");  //新生儿管理率
            else
                data.setControlRate(0.00 + "%");
            if(!data.getAreaPeople().equals(0))
               Datas.add(data);
        }
        return Datas;
    }

}
