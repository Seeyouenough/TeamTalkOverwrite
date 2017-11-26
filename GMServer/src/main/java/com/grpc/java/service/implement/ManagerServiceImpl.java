package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMManager;
import com.grpc.java.kernel.mybatis.mapper.IMManagerMapper;
import com.grpc.java.service.IManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/*******************************
 * Created by wx on 2017/10/27.*
 *******************************/
@Service("managerService")
public class ManagerServiceImpl implements IManagerService {
    @Resource
    private IMManagerMapper managerMapper;

    public IMManager getId(Integer id) {

        return managerMapper.selectById(id);
    }

    @Override
    public IMManager getName(String userName) {
        return this.managerMapper.selectByName(userName);
    }

    @Override
    public IMManager getToken(String token){
        return  this.managerMapper.selectToken(token);
    }

    @Override
    public List<IMManager> getAll() {

        return this.managerMapper.selectAll();
    }

    @Override
    public Boolean add(IMManager user) {
        return this.managerMapper.insertSelective(user) > 0;
    }


    @Override
    public Boolean updatePassword(IMManager user) {
        return managerMapper.updatePasswordSelective(user) > 0;
    }

}
