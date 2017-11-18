package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMPower;
import com.grpc.java.kernel.mybatis.mapper.IMPowerMapper;
import com.grpc.java.service.IPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("powerService")
public class PowerServiceImpl implements IPowerService {
    @Resource
    private IMPowerMapper powerMapper;


    public IMPower getById(Integer userId) {

        return this.powerMapper.selectById(userId);
    }

    @Override
    public IMPower getByName(String userName) {

        return powerMapper.selectByName(userName);
    }

    @Override
    public List<IMPower> getAll() {

        return powerMapper.selectAll();
    }

    @Override
    public Boolean add(IMPower user) {
        return powerMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return powerMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean update(IMPower user) {
        return powerMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
