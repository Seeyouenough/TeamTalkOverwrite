package com.webjava.kernel.mybatis.mapper;

import com.webjava.kernel.entity.IMDiscovery;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMDiscoveryMapper {
    IMDiscovery selectDiscoveryById(@Param("id") Integer Id);

    IMDiscovery selectDiscoveryByName(@Param("itemName") String Name);

    List<IMDiscovery> selectAllDiscovery();

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insertSelective(IMDiscovery record);

    int updateByPrimaryKeySelective(IMDiscovery record);
}