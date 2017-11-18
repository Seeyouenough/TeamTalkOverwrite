package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMAdmin_Power;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMAdmin_PowerMapper {
    IMAdmin_Power selectById(@Param("user_power_id") Integer Id);

    List<IMAdmin_Power> selectAll();

    int deleteByPrimaryKey(@Param("user_power_id") Integer id);

    int insertSelective(IMAdmin_Power record);

    int updateByPrimaryKeySelective(IMAdmin_Power record);
}