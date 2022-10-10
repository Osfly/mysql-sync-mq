package com.haodf.dourw.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.haodf.communal.jdbc.annotation.Column;
import com.haodf.communal.jdbc.annotation.Table;
import com.haodf.communal.jdbc.dao.BaseDO;
import com.haodf.communal.support.web.databind.DateJsonDeserializer;
import com.haodf.communal.support.web.databind.DateJsonSerializer;
import com.haodf.util.HaoDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(value = "doctors", idGroup = "hospital::DoctorNew")
public class DoctorDO extends BaseDO<Long> {

    @Column("id")
    private Long id;

    @Column("name")
    private String name;
}
