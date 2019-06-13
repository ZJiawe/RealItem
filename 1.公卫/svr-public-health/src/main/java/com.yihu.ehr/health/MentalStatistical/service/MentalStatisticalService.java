package com.yihu.ehr.health.MentalStatistical.service;

import com.yihu.ehr.health.MentalStatistical.dao.*;
import com.yihu.ehr.health.MentalStatistical.entity.*;
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
public class MentalStatisticalService extends BaseJpaService<MentalStatistical, XMentalStatisticalRepository> {
    @Autowired
    XMentalStatisticalRepository mentalStatisticalRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public MentalStatistical getMentalStatistical(String Id) {
        MentalStatistical mentalStatistical = mentalStatisticalRepository.findOne(Id);
        return mentalStatistical;
    }

    /**
     * 保存个人档案信息
     * @param mentalStatistical 个人档案对象
     * @return
     */
    public MentalStatistical saveUser(MentalStatistical mentalStatistical) {
        mentalStatistical = mentalStatisticalRepository.save(mentalStatistical);
        return mentalStatistical;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        mentalStatisticalRepository.delete(Id);

    }

    public List<MentalStatistical> getUser(String  filter) {
        List<MentalStatistical> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int PeopleCount = 0;
        Envelop envelop = new Envelop();

        String areaSQL=
               "select area,yeara,permanentPopulation,mentalIllnessYear,mentalRegulated,newMental,movedNumber " +
                       "from( " +
                       "select area,yeara,max(permanentPopulation) as permanentPopulation,max(mentalIllnessYear) as mentalIllnessYear,max(mentalRegulated) as mentalRegulated,max(newMental) as newMental,max(movedNumber) as movedNumber " +
                       "    from( " +
                       "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'mentalIllnessYear',0 'mentalRegulated',0 'newMental',0 'movedNumber' " +
                       "        from perinfor " +
                       "        where delete_state =:delete_state " +
                       "        group by house_address,year(create_date) " +
                       "         " +
                       "union all " +
                       "select house_address 'area',year(create_date) 'yeara',null,count(1) 'mentalIllnessYear',null,null,null " +
                       "from perinfor " +
                       "where delete_state =:delete_state and past_history like '%精神%' " +
                       "group by house_address,year(create_date) " +
                       "         " +
                       "union all " +
                       "select house_address 'area',year(perinfor.create_date) 'yeara',null,null,count(1) 'mentalRegulated',null,null " +
                       "from severe_mental_supplement,perinfor " +
                       "where severe_mental_supplement.delete_state =:delete_state and perinfor.delete_state =:delete_state and perinfor.people_id =severe_mental_supplement.people_id " +
                       "group by house_address,year(perinfor.create_date) " +
                       "         " +
                       "         " +
                       "union all " +
                       "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'newMental',null " +
                       "from perinfor " +
                       "where delete_state =:delete_state and past_history like '%精神%' " +
                       "group by house_address,year(create_date) " +
                       " " +
                       "    union all " +
                       "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'movedNumber' " +
                       "from perinfor " +
                       "where delete_state =:delete_state and livestate=:dieState " +
                       "group by house_address,year(create_date) " +
                       "         " +
                       "    ) as computer " +
                       "    group by computer.area, computer.yeara)as mDB " +
                       "where mDB.permanentPopulation is not null ";


        String areaSQLByfilter=
                "select area,yeara,permanentPopulation,mentalIllnessYear,mentalRegulated,newMental,movedNumber " +
                        "from( " +
                        "select area,yeara,max(permanentPopulation) as permanentPopulation,max(mentalIllnessYear) as mentalIllnessYear,max(mentalRegulated) as mentalRegulated,max(newMental) as newMental,max(movedNumber) as movedNumber " +
                        "    from( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'mentalIllnessYear',0 'mentalRegulated',0 'newMental',0 'movedNumber' " +
                        "        from perinfor " +
                        "        where delete_state =:delete_state " +
                        "        group by house_address,year(create_date) " +
                        "         " +
                        "union all " +
                        "select house_address 'area',year(create_date) 'yeara',null,count(1) 'mentalIllnessYear',null,null,null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%精神%' " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "union all " +
                        "select house_address 'area',year(perinfor.create_date) 'yeara',null,null,count(1) 'mentalRegulated',null,null " +
                        "from severe_mental_supplement,perinfor " +
                        "where severe_mental_supplement.delete_state =:delete_state and perinfor.delete_state =:delete_state and perinfor.people_id =severe_mental_supplement.people_id " +
                        "group by house_address,year(perinfor.create_date) " +
                        "         " +
                        "         " +
                        "union all " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'newMental',null " +
                        "from perinfor " +
                        "where delete_state =:delete_state and past_history like '%精神%' " +
                        "group by house_address,year(create_date) " +
                        " " +
                        "    union all " +
                        "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'movedNumber' " +
                        "from perinfor " +
                        "where delete_state =:delete_state and livestate=:dieState " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        "    ) as computer " +
                        "    group by computer.area, computer.yeara)as mDB " +
                        "where mDB.permanentPopulation is not null and mDB.area like :area";


        SQLQuery queryArea = null;

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

        for (Object item:Arealist
        ) {
            Object[] obj = (Object[])item;
            MentalStatistical data = new MentalStatistical();

            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setPermanentPopulation(Integer.parseInt(obj[2].toString()));//辖区常住人口数
            data.setMentalIllnessYear(Integer.parseInt(obj[3].toString()));    //年度内登记在册的确诊严重精神障碍患者人数(人)
            data.setMentalRegulated(Integer.parseInt(obj[4].toString()));      //年度内严重精神障碍患者规范管理人数(人)
            data.setNewMental(Integer.parseInt(obj[5].toString()));      //年度内新增严重精神障碍患者管理人数
            data.setMovedNumber(Integer.parseInt(obj[6].toString()));    //迁出（死亡)人数


            if ((data.getMentalIllnessYear() != null)&&(data.getMentalIllnessYear() != 0))
                data.setMentalPatients(df.format(((double)data.getMentalRegulated() / (double)data.getMentalIllnessYear()) * 100) + "%");   //新生儿访问率
            else
                data.setMentalPatients( 0.00+ "%");
            if ((data.getPermanentPopulation() != null)){
                data.setTotalNumberMental(data.getPermanentPopulation()/20+1);
            }
            Datas.add(data);
        }
        return Datas;
    }



}
