package com.haodf.dourw.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChangeEventVO {

    private String event;
    private String table;
    private Long id;
}
