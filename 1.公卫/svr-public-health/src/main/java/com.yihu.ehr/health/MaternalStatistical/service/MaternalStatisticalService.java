package com.yihu.ehr.health.MaternalStatistical.service;
import com.yihu.ehr.health.MaternalStatistical.entity.MaternalStatistical;
import com.yihu.ehr.health.MaternalStatistical.dao.XMaternalStatisticalRepository;
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
public class MaternalStatisticalService extends BaseJpaService<MaternalStatistical, XMaternalStatisticalRepository> {
    @Autowired
    XMaternalStatisticalRepository maternalStatisticalRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public MaternalStatistical getMaternalStatistical(String Id) {
        MaternalStatistical entity = maternalStatisticalRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public MaternalStatistical saveUser(MaternalStatistical entity) {
        entity = maternalStatisticalRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        maternalStatisticalRepository.delete(Id);
    }

    public  List<MaternalStatistical> getUser(String  filter) {
        List<MaternalStatistical> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int PeopleCount = 0;
        Envelop envelop = new Envelop();

        String areaSQL="select area,yeara,permanentPopulation,liveBirthNumber,managementWomen,movedNumber " +
                "from( " +
                "select area,yeara,max(permanentPopulation) as permanentPopulation,max(liveBirthNumber) as liveBirthNumber,max(managementWomen) as managementWomen,max(movedNumber) as movedNumber " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'liveBirthNumber',0 'managementWomen',0 'movedNumber' " +
                "from perinfor  " +
                "where delete_state=:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'liveBirthNumber',null,null " +
                "from perinfor  " +
                "where pregnant =:pregnant and livestate =:liveState and delete_state=:delete_state  " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'managementWomen',null " +
                "from perinfor  " +
                "        where pregnant =:pregnant and delete_state=:delete_state " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'movedNumber' " +
                "from perinfor  " +
                "        where pregnant =:pregnant and livestate =:dieState and delete_state=:delete_state  " +
                "        group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as maternalDB " +
                "where maternalDB.managementWomen is  not null";



        String areaSQLByfilter="select area,yeara,permanentPopulation,liveBirthNumber,managementWomen,movedNumber " +
                "from( " +
                "select area,yeara,max(permanentPopulation) as permanentPopulation,max(liveBirthNumber) as liveBirthNumber,max(managementWomen) as managementWomen,max(movedNumber) as movedNumber " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'liveBirthNumber',0 'managementWomen',0 'movedNumber' " +
                "from perinfor  " +
                "where delete_state=:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'liveBirthNumber',null,null " +
                "from perinfor  " +
                "where pregnant =:pregnant and livestate =:liveState and delete_state=:delete_state  " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'managementWomen',null " +
                "from perinfor  " +
                "        where pregnant =:pregnant and delete_state=:delete_state " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'movedNumber' " +
                "from perinfor  " +
                "        where pregnant =:pregnant and livestate =:dieState and delete_state=:delete_state  " +
                "        group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as maternalDB " +
                "where maternalDB.managementWomen is  not null and maternalDB.area like :area";

        String Vistit ="select count(1) '年度内结案产妇管理人数' " +
                "from ( " +
                "select id " +
                "from " +
                "( " +
                "select id,mid " +
                "from " +
                "(select perinfor.people_id as id " +
                "from perinfor " +
                "where delete_state=:delete_state and pregnant =:pregnant and house_address =:house_address and year(create_date) =:create_date " +
                ")as perinforMaternal  " +
                "left join " +
                "( " +
                "select postpartum_visit_forty_two.people_id as mid " +
                "from  postpartum_visit_forty_two " +
                "    where delete_state=:delete_state and handle=:handle " +
                "    group by postpartum_visit_forty_two.people_id " +
                ")as fortyTwoMaternal  " +
                "on perinforMaternal.id = fortyTwoMaternal.mid " +
                ") as maternalVisit " +
                "where maternalVisit.mid is not null " +
                ") as allmaternalVisit";
        String Vistit2 ="select count(1) '早孕建册人数' " +
                "from ( " +
                "select id " +
                "from " +
                "( " +
                "select id,mid " +
                "from " +
                "(select perinfor.people_id as id " +
                "from perinfor " +
                "where delete_state=:delete_state and pregnant =:pregnant and house_address =:house_address and year(create_date) =:create_date " +
                ")as perinforMaternal  " +
                "left join " +
                "( " +
                "select first_prenatal_diagnosis.people_id as mid " +
                "from  first_prenatal_diagnosis " +
                "    where delete_state=:delete_state and gestational_weeks<13 " +
                "    group by first_prenatal_diagnosis.people_id " +
                ")as maternal  " +
                "on perinforMaternal.id = maternal.mid " +
                ") as maternalVisit " +
                "where maternalVisit.mid is not null " +
                ") as allmaternalVisit";

        String Vistit3="select count(1) '产后访视'  " +
                "from (  " +
                "select id  " +
                "from  " +
                "(  " +
                "select id,mid  " +
                "from  " +
                "(select perinfor.people_id as id  " +
                "from perinfor  " +
                "where delete_state=:delete_state and pregnant =:pregnant and house_address =:house_address and year(create_date) =:create_date  " +
                ")as perinforMaternal   " +
                "left join  " +
                "(  " +
                "select postpartum_visit.people_id as mid  " +
                "from  postpartum_visit  " +
                "    where delete_state=:delete_state  " +
                "    group by postpartum_visit.people_id  " +
                ")as maternal   " +
                "on perinforMaternal.id = maternal.mid  " +
                ") as maternalVisit  " +
                "where maternalVisit.mid is not null  " +
                ") as allmaternalVisit";
        SQLQuery queryArea = null;

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("liveState","存活");
                queryArea.setParameter("dieState","死亡");
                queryArea.setParameter("pregnant","是");
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
                queryArea.setParameter("pregnant","是");
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
            MaternalStatistical data = new MaternalStatistical();
            data.setArea(obj[0].toString());                             //设定地区
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setPermanentPopulation(Integer.parseInt(obj[2].toString()));//辖区常住人口数
            data.setLiveBirthNumber(Integer.parseInt(obj[3].toString()));//辖区内活产数
            data.setManagementWomen(Integer.parseInt(obj[4].toString()));//年度内新增产妇管理人数
            data.setMovedNumber(Integer.parseInt(obj[5].toString()));//迁出（死亡）孕产妇人数


            SQLQuery queryVistit = currentSession().createSQLQuery(Vistit);
            SQLQuery queryVistit2 = currentSession().createSQLQuery(Vistit2);
            SQLQuery queryVistit3 = currentSession().createSQLQuery(Vistit3);

            try {
                queryVistit.setParameter("delete_state","未删");
                queryVistit.setParameter("pregnant","是");
                queryVistit.setParameter("create_date",""+data.getYear()+"");
                queryVistit.setParameter("house_address",""+data.getArea()+"");
                queryVistit.setParameter("handle","结案");
                queryVistit2.setParameter("delete_state","未删");
                queryVistit2.setParameter("pregnant","是");
                queryVistit2.setParameter("create_date",""+data.getYear()+"");
                queryVistit2.setParameter("house_address",""+data.getArea()+"");
                queryVistit3.setParameter("delete_state","未删");
                queryVistit3.setParameter("pregnant","是");
                queryVistit3.setParameter("create_date",""+data.getYear()+"");
                queryVistit3.setParameter("house_address",""+data.getArea()+"");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }

            List<Object> Vistitlist = queryVistit.list();
            List<Object> Vistitlist2 = queryVistit2.list();
            List<Object> Vistitlist3 = queryVistit3.list();

            if(Vistitlist.size()>0){
                try {
                    Object visitobj = Vistitlist.get(0);
                    data.setCustodyWomen(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }
            if(Vistitlist2.size()>0){
                try {
                    Object visitobj = Vistitlist2.get(0);
                    data.setEarlyPregnancy(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }
            if(Vistitlist3.size()>0){
                try {
                    Object visitobj = Vistitlist2.get(0);
                    data.setPostpartumVisits(Integer.parseInt(visitobj.toString()));
                } catch (Exception e) {
                    envelop.setSuccessFlg(false);
                    envelop.setErrorMsg(e.getMessage());
                    e.printStackTrace();
                }
            }

            if ((data.getLiveBirthNumber() != null) && !(data.getLiveBirthNumber().equals(0)))
                data.setPostpartumVisitsRate(df.format((double) data.getPostpartumVisits() / (double) data.getLiveBirthNumber() * 100) + "%");                  //早孕建册率
            else
                data.setPostpartumVisitsRate(0.00 + "%");
            if ((data.getLiveBirthNumber() != null)&&(data.getEarlyPregnancy() !=null) && !(data.getLiveBirthNumber().equals(0)))
                data.setEarlyPregnancyRate(df.format(((double)data.getEarlyPregnancy()/(double)data.getLiveBirthNumber())*100)+"%");                  //早孕建册率
            else
                data.setEarlyPregnancyRate(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }
}
