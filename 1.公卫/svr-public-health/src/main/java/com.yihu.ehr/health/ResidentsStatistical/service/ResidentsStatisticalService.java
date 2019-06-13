package com.yihu.ehr.health.ResidentsStatistical.service;
import com.yihu.ehr.health.ResidentsStatistical.entity.ResidentsStatistical;
import com.yihu.ehr.health.ResidentsStatistical.dao.XResidentsStatisticalRepository;
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
public class ResidentsStatisticalService extends BaseJpaService<ResidentsStatistical, XResidentsStatisticalRepository> {
    @Autowired
    XResidentsStatisticalRepository residentsStatisticalRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public ResidentsStatistical getResidentsStatistical(String Id) {
        ResidentsStatistical entity = residentsStatisticalRepository.findOne(Id);
        return entity;
    }

    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public ResidentsStatistical saveUser(ResidentsStatistical entity) {
        entity = residentsStatisticalRepository.save(entity);
        return entity;
    }

    /**
     * 删除个人档案
     * @param Id 个人档案id
     */
    public void deleteUser(String Id) {
        residentsStatisticalRepository.delete(Id);
    }


    public  List<ResidentsStatistical> getUser(String  filter) {
        List<ResidentsStatistical> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        Envelop envelop = new Envelop();

        String areaSQL="select area,yeara,permanentPopulation,numberSetters,electronicHealth,newFiles,deaths " +
                "from( " +
                "select area,yeara,max(permanentPopulation) as permanentPopulation,max(numberSetters) as numberSetters,max(electronicHealth) as electronicHealth,max(newFiles) as newFiles,max(deaths) as deaths " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'numberSetters',0 'electronicHealth',0 'newFiles',0 'deaths' " +
                "from perinfor  " +
                "where delete_state =:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'numberSetters',null,null,null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'electronicHealth',null,null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'newFiles',null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "        group by house_address,year(create_date) " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'deaths' " +
                "from perinfor  " +
                "        where livestate =:dieState and delete_state =:delete_state  " +
                "        group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as residentsDB " +
                "where residentsDB.permanentPopulation is  not null";



        String areaSQLByfilter="select area,yeara,permanentPopulation,numberSetters,electronicHealth,newFiles,deaths " +
                "from( " +
                "select area,yeara,max(permanentPopulation) as permanentPopulation,max(numberSetters) as numberSetters,max(electronicHealth) as electronicHealth,max(newFiles) as newFiles,max(deaths) as deaths " +
                "from ( " +
                "select house_address 'area',year(create_date) 'yeara',count(1) 'permanentPopulation',0 'numberSetters',0 'electronicHealth',0 'newFiles',0 'deaths' " +
                "from perinfor  " +
                "where delete_state =:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,count(1) 'numberSetters',null,null,null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,count(1) 'electronicHealth',null,null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "        group by house_address,year(create_date) " +
                "         " +
                "    UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,count(1) 'newFiles',null " +
                "from perinfor  " +
                "        where delete_state =:delete_state " +
                "        group by house_address,year(create_date) " +
                "UNION ALL    " +
                "select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'deaths' " +
                "from perinfor  " +
                "        where livestate =:dieState and delete_state=:delete_state  " +
                "        group by house_address,year(create_date) " +
                ") as computer " +
                "group by computer.area , computer.yeara) as residentsDB " +
                "where residentsDB.permanentPopulation is  not null and residentsDB.area like :area";

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
            ResidentsStatistical data = new ResidentsStatistical();
            data.setArea(obj[0].toString());                             //设定地区
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setPermanentPopulation(Integer.parseInt(obj[2].toString()));//辖区常住人口数
            data.setNumberSetters(Integer.parseInt(obj[3].toString()));//建档人数
            data.setElectronicHealth(Integer.parseInt(obj[4].toString()));//电子健康档案建档数
            data.setNewFiles(Integer.parseInt(obj[5].toString()));//新增档案数量
            data.setDeaths(Integer.parseInt(obj[6].toString()));//死亡人数

            if ((data.getPermanentPopulation() != null) && !(data.getPermanentPopulation().equals(0)))
                data.setHealthBuildRate(df.format((double) data.getNumberSetters() / (double) data.getPermanentPopulation() * 100) + "%");                  //早孕建册率
            else
                data.setHealthBuildRate(0.00 + "%");
            if ((data.getPermanentPopulation() != null)&& !(data.getPermanentPopulation().equals(0)))
                data.setElectronicRate(df.format(((double)data.getElectronicHealth()/(double)data.getPermanentPopulation())*100)+"%");                  //早孕建册率
            else
                data.setElectronicRate(0.00 + "%");
            Datas.add(data);
        }
        return Datas;
    }
}
