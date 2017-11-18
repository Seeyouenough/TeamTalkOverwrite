package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMAdmin_Role;
import com.grpc.java.kernel.mybatis.mapper.IMAdmin_RoleMapper;
import com.grpc.java.service.IAdmin_RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("admin_RoleService")
public class Admin_RoleServiceImpl implements IAdmin_RoleService {
    @Resource
    private IMAdmin_RoleMapper admin_RoleMapper;


    public IMAdmin_Role getById(Integer userId) {

        return this.admin_RoleMapper.selectById(userId);
    }

    @Override
    public List<IMAdmin_Role> getAll() {

        return admin_RoleMapper.selectAll();
    }

    @Override
    public Boolean add(IMAdmin_Role user) {
        return admin_RoleMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return admin_RoleMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean update(IMAdmin_Role user) {
        return admin_RoleMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
