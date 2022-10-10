package com.haodf.dourw.test;

import com.haodf.communal.rpc.response.RpcResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: drw
 * @create: 2022-10-10 10:41
 **/
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorDAO doctorDAO;

    @PostMapping("/save")
    public RpcResponse<Integer> save(@RequestBody DoctorDO doctorDO){
        return RpcResponse.success(doctorDAO.save(doctorDO));
    }
}
