package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMPower;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMPowerMapper {
    IMPower selectById(@Param("power_id") Integer Id);

    IMPower selectByName(@Param("power_name") String Name);

    List<IMPower> selectAll();

    int deleteByPrimaryKey(@Param("power_id") Integer id);

    int insertSelective(IMPower record);

    int updateByPrimaryKeySelective(IMPower record);
}