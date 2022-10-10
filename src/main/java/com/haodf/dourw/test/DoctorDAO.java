package com.haodf.dourw.test;

import com.haodf.communal.jdbc.dao.JdbcDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class DoctorDAO {

    @Resource
    private JdbcDAO jdbcDAO;


    public int save(DoctorDO doctorDO){
        return jdbcDAO.save(doctorDO);
    }

    public int update(DoctorDO doctorDO) {
        return jdbcDAO.update(doctorDO);
    }

    public int delete(Long id) {
        return jdbcDAO.deleteById(DoctorDO.class, id);
    }
}
