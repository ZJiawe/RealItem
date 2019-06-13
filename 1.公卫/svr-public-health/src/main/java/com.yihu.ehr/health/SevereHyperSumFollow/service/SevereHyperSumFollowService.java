package com.yihu.ehr.health.SevereHyperSumFollow.service;

import com.yihu.ehr.health.SevereHyperSumFollow.dao.XSevereHyperSumFollowRepository;
import com.yihu.ehr.health.SevereHyperSumFollow.entity.SevereHyperSumFollow;
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
public class SevereHyperSumFollowService extends BaseJpaService<SevereHyperSumFollow, XSevereHyperSumFollowRepository> {
    @Autowired
    XSevereHyperSumFollowRepository severeHyperSumFollowRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public SevereHyperSumFollow getSevereHyperSumFollow(String Id) {
        SevereHyperSumFollow entity = severeHyperSumFollowRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public SevereHyperSumFollow saveUser(SevereHyperSumFollow entity) {
        entity = severeHyperSumFollowRepository.save(entity);
        return entity;
    }
    public  List<SevereHyperSumFollow> getUser(String  filter) {
        List<SevereHyperSumFollow> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int PeopleCount = 0;
        Envelop envelop = new Envelop();
        String peopleSQL=
                "select count(1) as highCount  " +
                        "from perinfor  " +
                        "where delete_state=:delete_state  and past_history like '%高血压%'";
        String areaSQL=
                "select area,yeara,areaCount,dieCount,addCount,masCount " +
                        "from( " +
                        "select area,yeara,max(areaCount) as areaCount,max(dieCount) as dieCount,max(addCount) as addCount,max(masCount) as masCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara' ,count(1) 'areaCount',0 'dieCount',0 'addCount',0 'masCount' " +
                        "from perinfor " +
                        "where delete_state =:delete_state " +
                        "group by house_address,year(create_date) " +
                        "   UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,count(1) 'dieCount',null,null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' and livestate=:dieState " +
                        "group by house_address,year(create_date) " +
                        "   UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,null,count(1) 'addCount',null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' " +
                        "group by house_address,year(create_date) " +
                        "    UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,null,null,count(1)'masCount' " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' " +
                        "group by house_address,year(create_date) " +
                        ")as computer " +
                        "group by computer.area , computer.yeara) as highDB " +
                        "where highDB.masCount is not null ";


        String areaSQLByfilter=
                "select area,yeara,areaCount,dieCount,addCount,masCount " +
                        "from( " +
                        "select area,yeara,max(areaCount) as areaCount,max(dieCount) as dieCount,max(addCount) as addCount,max(masCount) as masCount " +
                        "from ( " +
                        "select house_address 'area',year(create_date) 'yeara' ,count(1) 'areaCount',0 'dieCount',0 'addCount',0 'masCount' " +
                        "from perinfor " +
                        "where delete_state =:delete_state " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "   UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,count(1) 'dieCount',null,null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' and livestate=:dieState " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "   UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,null,count(1) 'addCount',null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    UNION ALL " +
                        "select house_address 'area',year(create_date) 'yeara' ,null,null,null,count(1)'masCount' " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%高血压%' " +
                        "group by house_address,year(create_date) " +
                        ")as computer " +
                        "group by computer.area , computer.yeara) as highDB " +
                        "where highDB.masCount is not null and highDB.area like :area";


        String Vistit =
                "select count(1) 'gmsgCount' " +
                        "from( " +
                        "select * " +
                        "from " +
                        "(select perinfor.people_id as id " +
                        "from perinfor " +
                        "where delete_state=:delete_state and past_history like '%高血压%' and house_address =:house_address and year(create_date) =:create_date" +
                        ") as perinforHigh " +
                        "left join " +
                        "( " +
                        " select hyper_manag.people_id as highid  " +
                        " from  hyper_manag " +
                        "    where delete_state=:delete_state and obey_medical like '%良好%'   " +
                        "    group by hyper_manag.people_id " +
                        ")as hypersum  " +
                        "on perinforHigh.id = hypersum.highid " +
                        ") as high  " +
                        "where high.highid is not null ";

        String Vistit2 ="select count(1) 'nearCount'  " +
                "from(  " +
                "select *  " +
                "from  " +
                "(select perinfor.people_id as id  " +
                "from perinfor  " +
                "where delete_state=:delete_state and past_history like '%高血压%'and house_address =:house_address and year(create_date) =:create_date" +
                ") as perinforHigh  " +
                "left join  " +
                "(  " +
                "  select hyper_manag.people_id as highid   " +
                "  from  hyper_manag  " +
                "    where delete_state=:delete_state and blood_pressure < 130 and blood_pressure > 80   " +
                "    group by hyper_manag.people_id  " +
                ")as hypersum   " +
                "on perinforHigh.id = hypersum.highid  " +
                ") as high   " +
                "where high.highid is not null  ";

        SQLQuery queryPerson = currentSession().createSQLQuery(peopleSQL);
        queryPerson.setParameter("delete_state","未删");
        SQLQuery queryArea = null;

        List<Object> peoplelist = queryPerson.list();

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
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
            SevereHyperSumFollow data = new SevereHyperSumFollow();
            data.setHighNumber(PeopleCount);                            //0~6岁儿童健康管理人数
            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setResidentsNumber(Integer.parseInt(obj[2].toString()));//辖区常住人口数
            data.setDeathsNumber(Integer.parseInt(obj[3].toString()));    //迁出（死亡)人数
            data.setYearHighAddNumber(Integer.parseInt(obj[4].toString()));      //年度内新增高血压管理人数
            data.setHighNumberYear(Integer.parseInt(obj[5].toString()));      //年度内高血压患者管理人数
            data.setResHighNumber((data.getResidentsNumber()/20) + 1);       //辖区内高血压患者总人数(理论数)
            SQLQuery queryVistit = currentSession().createSQLQuery(Vistit);
            SQLQuery queryVistit1 = currentSession().createSQLQuery(Vistit2);
            try {
                queryVistit.setParameter("delete_state","未删");
                queryVistit.setParameter("create_date",""+data.getYear()+"");
                queryVistit.setParameter("house_address",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            try {
                queryVistit1.setParameter("delete_state","未删");
                queryVistit1.setParameter("create_date",""+data.getYear()+"");
                queryVistit1.setParameter("house_address",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
            List<Object> Vistitlist = queryVistit.list();
            List<Object> Vistitlist1 = queryVistit1.list();
            if(Vistitlist.size()>0){
                try {
                    Object visitobj = Vistitlist.get(0);
                    data.setYearHighMagNumber(Integer.parseInt(visitobj.toString()));

                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }
            if(Vistitlist1.size()>0){
                try {
                    Object visitobj = Vistitlist1.get(0);
                    data.setYearHighNearNumber(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }
            if ((data.getHighNumber() != null) && !(data.getHighNumber().equals(0)))
                data.setYearHighMagRate(df.format(((double)data.getYearHighMagNumber() / (double)data.getHighNumber()) * 100) + "%");
            else
                data.setYearHighMagRate(0.00 + "%");
            if ((data.getYearHighNearNumber() != null) && !(data.getYearHighNearNumber().equals(0)))
                data.setYearHighCtrRate(df.format(((double)data.getYearHighMagNumber() / (double)data.getYearHighNearNumber()) * 100 )+ "%");
            else
                data.setYearHighCtrRate(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        severeHyperSumFollowRepository.delete(Id);
    }

}
