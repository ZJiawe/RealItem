package com.yihu.ehr.health.ElderlyStatistical.service;

import com.yihu.ehr.health.ElderlyStatistical.dao.XElderlyStatisticalRepository;
import com.yihu.ehr.health.ElderlyStatistical.entity.ElderlyStatistical;
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
public class ElderlyStatisticesService extends BaseJpaService<ElderlyStatistical, XElderlyStatisticalRepository> {
    @Autowired
    XElderlyStatisticalRepository elderlyStatisticalRepository;

    public  List<ElderlyStatistical> getUser(String  filter) {
        List<ElderlyStatistical> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int PeopleCount = 0;
        Envelop envelop = new Envelop();
        String peopleSQL=
                "select count(1) " +
                        "from perinfor " +
                        "where FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and delete_state =:delete_state ";

        String areaSQL= "select area,yeara,oldCount,areaCount,liveCount,dieCount,newCount " +
                "from( " +
                "select area,yeara,max(oldCount) as oldCount,max(areaCount) as areaCount,max(liveCount) as liveCount,max(dieCount) as dieCount,max(newCount) as newCount " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'oldCount',0 'areaCount',0 'liveCount',0 'dieCount',0 'newCount' " +
                "from perinfor  " +
                "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'areaCount',null,null,null " +
                "from perinfor  " +
                "where delete_state =:delete_state  " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'liveCount',null,null " +
                "from perinfor  " +
                "        where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and livestate =:livestate " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'dieCount',null " +
                "from perinfor  " +
                "        where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and livestate=:diestate and delete_state=:delete_state " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL   " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'newCount' " +
                "from perinfor  " +
                "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                "group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as oldrenDB " +
                "where oldrenDB.oldCount is  not null";

        String areaSQLByfilter=
                "select area,yeara,oldCount,areaCount,liveCount,dieCount,newCount " +
                        "from( " +
                        "select area,yeara,max(oldCount) as oldCount,max(areaCount) as areaCount,max(liveCount) as liveCount,max(dieCount) as dieCount,max(newCount) as newCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'oldCount',0 'areaCount',0 'liveCount',0 'dieCount',0 'newCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',null,count(1) 'areaCount',null,null,null " +
                        "from perinfor  " +
                        "where delete_state =:delete_state  " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'liveCount',null,null " +
                        "from perinfor  " +
                        "        where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and livestate =:livestate " +
                        "        group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL    " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'dieCount',null " +
                        "from perinfor  " +
                        "        where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and livestate=:diestate and delete_state=:delete_state " +
                        "        group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL   " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'newCount' " +
                        "from perinfor  " +
                        "where delete_state =:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 " +
                        "group by house_address,year(create_date) " +
                        ") as computer " +
                        "group by computer.area , computer.yeara) as oldrenDB " +
                        "where oldrenDB.oldCount is  not null and oldrenDB.area like :area";


        String Vistit =
                "select count(1)  '辖区访问人数' " +
                        "from " +
                        "( " +
                        "select id " +
                        "from " +
                        "( " +
                        "select id,oid " +
                        "from " +
                        "(select perinfor.people_id as id " +
                        "from perinfor " +
                        "where delete_state=:delete_state and FLOOR(DATEDIFF(CURDATE(),birth)/365.2422)>65 and house_address =:house_address and year(create_date) =:year " +
                        " ) as perinforChild  " +
                        "left join " +
                        "( " +
                        "select health_management_ecord.people_id as oid " +
                        "from  health_management_ecord " +
                        "    where delete_state=:delete_state " +
                        "    group by health_management_ecord.people_id " +
                        ")as elder  " +
                        "on perinforChild.id = elder.oid " +
                        ") as oneChildVisit " +
                        "where oneChildVisit.oid is not null " +
                        ") as allelderVisit";


        SQLQuery queryPerson = currentSession().createSQLQuery(peopleSQL);
        queryPerson.setParameter("delete_state","未删");
        SQLQuery queryArea = null;

        List<Object> peoplelist = queryPerson.list();

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("livestate","存活");
                queryArea.setParameter("diestate","死亡");
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
                queryArea.setParameter("diestate","死亡");
                queryArea.setParameter("area","%"+filter+"%");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }


        List<Object> Arealist = queryArea.list();

        if(peoplelist.size()>0){                      //老人健康管理人数
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
            ElderlyStatistical data = new ElderlyStatistical();
            data.setElderlyCount(PeopleCount);                            //老人总数
            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setAreasfPeople(Integer.parseInt(obj[2].toString()));   //辖区老人人数
            data.setPeopleCount(Integer.parseInt(obj[3].toString()));    //辖区总人数即常驻人口
            data.setDiePeople(Integer.parseInt(obj[5].toString()));      //辖区内死亡数
            data.setYearNewcount(Integer.parseInt(obj[6].toString()));    //年度新增人数

            //随访数据提取
            SQLQuery queryVistit = currentSession().createSQLQuery(Vistit);
            try {
                queryVistit.setParameter("delete_state","未删");
                queryVistit.setParameter("year",""+data.getYear()+"");
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
                    data.setManagePeople(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }

            if ((data.getAreasfPeople() != null) && !(data.getAreasfPeople().equals(0)))
                data.setYearAreaManageradio(df.format(((double)data.getYearNewcount() / (double)data.getAreasfPeople()) * 100) + "%");   //新生儿访问率
            else
                data.setYearAreaManageradio(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }

}
