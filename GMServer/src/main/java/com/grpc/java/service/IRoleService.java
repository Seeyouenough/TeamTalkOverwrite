package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMRole;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IRoleService {

    IMRole getById(Integer id);

    IMRole getByName(String userName);

    Boolean add(IMRole user);

    List<IMRole> getAll();

    Boolean delete(Integer id);

    Boolean update(IMRole user);




}
