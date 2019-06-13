package com.yihu.ehr.health.TuberculosisHealth.service;

import com.yihu.ehr.health.TuberculosisHealth.dao.XTuberculosisHealthRepository;
import com.yihu.ehr.health.TuberculosisHealth.entity.TuberculosisHealthEntity;
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
public class TuberculosisHealthService extends BaseJpaService<TuberculosisHealthEntity, XTuberculosisHealthRepository> {
    @Autowired
    XTuberculosisHealthRepository tuberculosisHealthRepository;

    /**
     * 根据ID获取个人档案接口.
     *
     * @param Id 用户id
     */
    public TuberculosisHealthEntity getTuberculosisHealthEntity(String Id) {
        TuberculosisHealthEntity entity = tuberculosisHealthRepository.findOne(Id);
        return entity;
    }


    /**
     * 保存个人档案信息
     * @param entity 个人档案对象
     * @return
     */
    public TuberculosisHealthEntity saveUser(TuberculosisHealthEntity entity) {
        entity = tuberculosisHealthRepository.save(entity);
        return entity;
    }
    public  List<TuberculosisHealthEntity> getUser(String  filter) {
        List<TuberculosisHealthEntity> Datas = new ArrayList<>();
        java.text.DecimalFormat df =new java.text.DecimalFormat("0.00");
        int tuNumber = 0;
        Envelop envelop = new Envelop();
        String peopleSQL=
                "select count(1) 'tuNumber' " +
                        "from perinfor " +
                        "where delete_state=:delete_state and past_history like'%结核病%'";

        String areaSQL=
                "select area,yeara,tuNumber,tumNumber,treNumber,ruleNumber,addNumber,outNumber " +
                        "from ( " +
                        "select area,yeara, max(tuNumber) 'tuNumber',max(tumNumber)'tumNumber' ,max(treNumber)'treNumber',max(ruleNumber)'ruleNumber',max(addNumber)'addNumber',max(outNumber)'outNumber' " +
                        "from( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'tuNumber',0 'tumNumber',0'treNumber',0'ruleNumber',0'addNumber',0'outNumber' " +
                        "from perinfor " +
                        "        where delete_state=:delete_state and past_history like'%结核病%' " +
                        "group by house_address,year(create_date) " +
                        " " +
                        "union all " +
                        " " +
                        "select area 'area',year(create_date) 'yeara',null,count(1) 'tumNumber',null,null,null,null " +
                        "from firsthomevisit_record " +
                        "         where delete_state=:delete_state  " +
                        "group by area,year(create_date) " +
                        "         " +
                        "union all " +
                        "        select area 'area',year(create_date) 'yeara',null,null,count(1) 'treNumber',null,null,null " +
                        "from firsthomevisit_record " +
                        "where delete_state=:delete_state and management='是' " +
                        "group by area,year(create_date) " +
                        "         " +
                        "union all " +
                        "        select area 'area',year(create_date) 'yeara',null,null,null,count(1) 'ruleNumber',null,null " +
                        "from firsthomevisit_record " +
                        "where delete_state=:delete_state and rule_medicine='是' " +
                        "group by area,year(create_date) " +
                        " " +
                        "union all " +
                        "        select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'addNumber',null " +
                        "from perinfor " +
                        "where delete_state=:delete_state  " +
                        "group by house_address,year(create_date) " +
                        " " +
                        "union all " +
                        "        select house_address 'area',year(create_date) 'yeara',null,null,null,null,null,count(1) 'outNumber' " +
                        "from perinfor " +
                        "where delete_state=:delete_state and livestate='死亡' " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        ")as computer " +
                        "group by  computer.area, computer.yeara " +
                        ") as TuberDB " +
                        "where TuberDB.tuNumber is not null";


        String areaSQLByfilter=
                "select area,yeara,tuNumber,tumNumber,treNumber,ruleNumber,addNumber,outNumber " +
                        "from ( " +
                        "select area,yeara, max(tuNumber) 'tuNumber',max(tumNumber)'tumNumber' ,max(treNumber)'treNumber',max(ruleNumber)'ruleNumber',max(addNumber)'addNumber',max(outNumber)'outNumber' " +
                        "from( " +
                        "select house_address 'area',year(create_date) 'yeara',count(1) 'tuNumber',0 'tumNumber',0'treNumber',0'ruleNumber',0'addNumber',0'outNumber' " +
                        "from perinfor " +
                        "        where delete_state=:delete_state and past_history like'%结核病%' " +
                        "group by house_address,year(create_date) " +
                        " " +
                        "union all " +
                        " " +
                        "select area 'area',year(create_date) 'yeara',null,count(1) 'tumNumber',null,null,null,null " +
                        "from firsthomevisit_record " +
                        "         where delete_state=:delete_state  " +
                        "group by area,year(create_date) " +
                        "         " +
                        "union all " +
                        "        select area 'area',year(create_date) 'yeara',null,null,count(1) 'treNumber',null,null,null " +
                        "from firsthomevisit_record " +
                        "where delete_state=:delete_state and management='是' " +
                        "group by area,year(create_date) " +
                        "         " +
                        "union all " +
                        "        select area 'area',year(create_date) 'yeara',null,null,null,count(1) 'ruleNumber',null,null " +
                        "from firsthomevisit_record " +
                        "where delete_state=:delete_state and rule_medicine='是' " +
                        "group by area,year(create_date) " +
                        " " +
                        "union all " +
                        "        select house_address 'area',year(create_date) 'yeara',null,null,null,null,count(1) 'addNumber',null " +
                        "from perinfor " +
                        "where delete_state=:delete_state  " +
                        "group by house_address,year(create_date) " +
                        " " +
                        "union all " +
                        "        select house_address 'area',year(create_date) 'yeara',null,null,null,null,null,count(1) 'outNumber' " +
                        "from perinfor " +
                        "where delete_state=:delete_state and livestate='死亡' " +
                        "group by house_address,year(create_date) " +
                        "         " +
                        ")as computer " +
                        "group by  computer.area, computer.yeara " +
                        ") as TuberDB " +
                        "where TuberDB.tuNumber is  not null and TuberDB.area like :area";


        SQLQuery queryPerson = currentSession().createSQLQuery(peopleSQL);
        queryPerson.setParameter("delete_state","未删");
        SQLQuery queryArea = null;

        List<Object> peoplelist = queryPerson.list();

        if(filter.equals("")){
            try {
                queryArea = currentSession().createSQLQuery(areaSQL);
                queryArea.setParameter("delete_state","未删");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                queryArea = currentSession().createSQLQuery(areaSQLByfilter);
                queryArea.setParameter("delete_state","未删");
                queryArea.setParameter("area","%"+filter+"%");
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }


        List<Object> Arealist = queryArea.list();

        if(peoplelist.size()>0){                      //肺结核患者人数
            try {
                Object obj = peoplelist.get(0);
                tuNumber = Integer.parseInt(obj.toString());
            } catch (Exception e) {
                envelop.setSuccessFlg(false);
                envelop.setErrorMsg(e.getMessage());
                e.printStackTrace();
            }
        }

        for (Object item:Arealist
        ) {
            Object[] obj = (Object[])item;
            TuberculosisHealthEntity data = new TuberculosisHealthEntity();
            data.setTuNumber(tuNumber);                            //总肺结核患者人数
            data.setArea(obj[0].toString());                             //设定地区   注：下标与SQl语句中Select对应
            data.setYear(Integer.parseInt(obj[1].toString()));           //设定年份
            data.setTuNumber(Integer.parseInt(obj[2].toString()));        //肺结核患者人数
            data.setTumNumber(Integer.parseInt(obj[3].toString()));  //管理人数
            data.setTreNumber(Integer.parseInt(obj[4].toString()));  //完成治疗人数
            data.setRuleNumber(Integer.parseInt(obj[5].toString()));  //规则服药人数
            data.setAddNumber(Integer.parseInt(obj[6].toString()));  //年度新增人数
            data.setOutNumber(Integer.parseInt(obj[7].toString()));  //年度死亡人数


            if ( (data.getTuNumber()!=null) && !(data.getTuNumber().equals(0)) )
                data.setTumRate( df.format((double)data.getTumNumber()/(double)data.getTuNumber()*100)+"%");  //肺结核管理率
            else
                data.setTumRate(0+"%");

            if ( (data.getTreNumber()!=null) && !(data.getTreNumber().equals(0)) )
                data.setRuleRate( df.format((double)data.getRuleNumber()/(double)data.getTreNumber()*100)+"%");  //规则服药率
            else
                data.setRuleRate(0+"%");

            Datas.add(data);
        }
        return Datas;
    }
    /**
     * 删除个人档案
     * @param Id 个人档案id
     */

    public void deleteUser(String Id) {
        tuberculosisHealthRepository.delete(Id);
    }
}
