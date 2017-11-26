package com.grpc.java.kernel.mybatis.mapper;

import com.grpc.java.kernel.entity.IMManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMManagerMapper {

    IMManager selectById(@Param("id") Integer id);

    IMManager selectByName(@Param("username") String userName);

    IMManager selectToken(@Param("token") String token);

    List<IMManager> selectAll();

    int insertSelective(IMManager record);

    int updatePasswordSelective(IMManager record);

    int deleteByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(IMManager record);
    
}