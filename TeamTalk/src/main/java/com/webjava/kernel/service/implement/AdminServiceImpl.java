package com.webjava.kernel.service.implement;

import com.webjava.kernel.entity.IMAdmin;
import com.webjava.kernel.entity.IMUser;
import com.webjava.kernel.mybatis.mapper.IMAdminMapper;
import com.webjava.kernel.service.IAdminService;
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
    public Boolean updatePassword(IMAdmin user) {
        return imAdminMapper.updatePasswordSelective(user) > 0;
    }

}
