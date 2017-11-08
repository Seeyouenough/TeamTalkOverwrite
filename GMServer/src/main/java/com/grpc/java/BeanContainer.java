package com.grpc.java;

import com.grpc.java.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wx on 2017/11/8.
 */
@Service("beanService")
public class BeanContainer {
    @Resource
    public IAdminService adminService;



}
