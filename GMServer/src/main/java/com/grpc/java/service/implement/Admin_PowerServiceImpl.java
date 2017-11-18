package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMAdmin_Power;
import com.grpc.java.kernel.mybatis.mapper.IMAdmin_PowerMapper;
import com.grpc.java.service.IAdmin_PowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("admin_PowerService")
public class Admin_PowerServiceImpl implements IAdmin_PowerService {
    @Resource
    private IMAdmin_PowerMapper admin_PowerMapper;


    public IMAdmin_Power getById(Integer userId) {

        return this.admin_PowerMapper.selectById(userId);
    }

    @Override
    public List<IMAdmin_Power> getAll() {

        return admin_PowerMapper.selectAll();
    }

    @Override
    public Boolean add(IMAdmin_Power user) {
        return admin_PowerMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return admin_PowerMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean update(IMAdmin_Power user) {
        return admin_PowerMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
