package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMRole;
import com.grpc.java.kernel.mybatis.mapper.IMRoleMapper;
import com.grpc.java.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Resource
    private IMRoleMapper roleMapper;


    public IMRole getById(Integer userId) {

        return this.roleMapper.selectById(userId);
    }

    @Override
    public IMRole getByName(String userName) {

        return roleMapper.selectByName(userName);
    }

    @Override
    public List<IMRole> getAll() {

        return roleMapper.selectAll();
    }

    @Override
    public Boolean add(IMRole user) {
        return roleMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return roleMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean update(IMRole user) {
        return roleMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
