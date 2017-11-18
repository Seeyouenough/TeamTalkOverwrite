package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMAdmin_Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMAdmin_RoleMapper {
    IMAdmin_Role selectById(@Param("user_role_id") Integer Id);

    List<IMAdmin_Role> selectAll();

    int deleteByPrimaryKey(@Param("user_role_id") Integer id);

    int insertSelective(IMAdmin_Role record);

    int updateByPrimaryKeySelective(IMAdmin_Role record);
}