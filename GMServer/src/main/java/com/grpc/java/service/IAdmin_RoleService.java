package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMAdmin_Role;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IAdmin_RoleService {

    IMAdmin_Role getById(Integer id);

    Boolean add(IMAdmin_Role user);

    List<IMAdmin_Role> getAll();

    Boolean delete(Integer id);

    Boolean update(IMAdmin_Role user);




}
