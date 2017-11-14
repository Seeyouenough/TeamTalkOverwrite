package com.grpc.java.service.implement;


import com.grpc.java.kernel.entity.IMAdmin;
import com.grpc.java.kernel.mybatis.mapper.IMAdminMapper;
import com.grpc.java.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/*******************************
 * Created by wx on 2017/10/27.*
 *******************************/
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IMAdminMapper imAdminMapper;

    public IMAdmin getAdminById(Integer id) {

        return imAdminMapper.selectAdminById(id);
    }

    @Override
    public IMAdmin getAdminByName(String userName) {
        return this.imAdminMapper.selectAdminByName(userName);
    }

    @Override
    public List<IMAdmin> getAllAdmin() {

        return imAdminMapper.selectAllAdmin();
    }

    @Override
    public Boolean addAdmin(IMAdmin user) {
        return imAdminMapper.insertSelective(user) > 0;
    }


    @Override
    public Boolean updatePassword(IMAdmin user) {
        return imAdminMapper.updatePasswordSelective(user) > 0;
    }

}
