package com.webjava.kernel.service.implement;

import com.webjava.kernel.entity.IMDiscovery;
import com.webjava.kernel.mybatis.mapper.IMDiscoveryMapper;
import com.webjava.kernel.service.IDiscoveryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
@Service("discoveryService")
public class DiscoveryServiceImpl  implements IDiscoveryService{

    @Resource
    private IMDiscoveryMapper discoveryMapper;

    public IMDiscovery getDiscoveryById(Integer userId) {
        return this.discoveryMapper.selectDiscoveryById(userId);
    }

    @Override
    public IMDiscovery getDiscoveryByName(String userName) {
        return discoveryMapper.selectDiscoveryByName(userName);
    }

    @Override
    public List<IMDiscovery> getAllDiscovery() {
        return discoveryMapper.selectAllDiscovery();
    }

    @Override
    public Boolean addDiscovery(IMDiscovery user) {
        return discoveryMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean deleteDiscovery(Integer id) {
        return discoveryMapper.deleteByPrimaryKey(id) > 0;
    }


    @Override
    public Boolean updateDiscovery(IMDiscovery user) {
        return discoveryMapper.updateByPrimaryKeySelective(user) > 0;
    }


}
