package com.webjava.kernel.mybatis.mapper;

import com.webjava.kernel.entity.IMGroupMember;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMGroupMemberMapper {

    IMGroupMember selectGroupMemberById(@Param("id") Integer Id);

    IMGroupMember selectGroupMemberByGroupId(@Param("groupId") Integer Id);

    IMGroupMember selectGroupMemberByUserId(@Param("userId") Integer Id);

    List<IMGroupMember> selectAllGroupMember();

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insertSelective(IMGroupMember record);

    int updateByPrimaryKeySelective(IMGroupMember record);
}