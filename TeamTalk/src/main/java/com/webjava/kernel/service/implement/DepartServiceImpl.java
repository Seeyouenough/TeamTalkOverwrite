package com.webjava.kernel.service.implement;

import com.webjava.kernel.entity.IMDepart;
import com.webjava.kernel.mybatis.mapper.IMDepartMapper;
import com.webjava.kernel.service.IDepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("departService")
public class DepartServiceImpl implements IDepartService {
    @Resource
    private IMDepartMapper departMapper;


    public IMDepart getDepartById(Integer userId) {

        return this.departMapper.selectDepartById(userId);
    }

    @Override
    public IMDepart getDepartByName(String userName) {

        return departMapper.selectDepartByName(userName);
    }

    @Override
    public List<IMDepart> getAllDepart() {

        return departMapper.selectAllDepart();
    }

    @Override
    public Boolean addDepart(IMDepart user) {
        return departMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean deleteDepart(Integer id) {
        return departMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean updateDepart(IMDepart user) {
        return departMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
