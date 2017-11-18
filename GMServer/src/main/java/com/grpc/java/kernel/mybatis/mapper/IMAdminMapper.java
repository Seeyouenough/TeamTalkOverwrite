package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMAdminMapper {

    IMAdmin selectAdminById(@Param("user_id") Integer id);

    IMAdmin selectAdminByName(@Param("username") String userName);

    List<IMAdmin> selectAllAdmin();

    int insertSelective(IMAdmin record);

    int updatePasswordSelective(IMAdmin record);

    int deleteByPrimaryKey(@Param("user_id") Integer id);

    int updateByPrimaryKeySelective(IMAdmin record);

}