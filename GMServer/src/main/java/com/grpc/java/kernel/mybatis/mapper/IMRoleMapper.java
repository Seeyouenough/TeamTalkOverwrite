package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMRoleMapper {
    IMRole selectById(@Param("role_id") Integer Id);

    IMRole selectByName(@Param("role_name") String Name);

    List<IMRole> selectAll();

    int deleteByPrimaryKey(@Param("role_id") Integer id);

    int insertSelective(IMRole record);

    int updateByPrimaryKeySelective(IMRole record);

}