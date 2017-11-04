package com.webjava.kernel.mybatis.mapper;

import com.webjava.kernel.entity.IMDepart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMDepartMapper {

    IMDepart selectDepartById(@Param("id") Integer Id);

    IMDepart selectDepartByName(@Param("departName") String Name);

    List<IMDepart> selectAllDepart();

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insertSelective(IMDepart record);

    int updateByPrimaryKeySelective(IMDepart record);

    
}