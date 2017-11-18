package com.grpc.java.server;

import com.grpc.java.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wx on 2017/11/8.
 */
@Service("beanService")
public class BeanContainer {
    @Resource
    public IAdminService adminService;

    @Resource
    public IDepartService departService;

    @Resource
    public IDiscoveryService discoveryService;

    @Resource
    public IGroupService groupService;

    @Resource
    public IUserService userService;

    @Resource
    public IAdmin_PowerService admin_powerService;

    @Resource
    public IAdmin_RoleService admin_roleService;

    @Resource
    public IPowerService powerService;

    @Resource
    public IRoleService roleService;

}
