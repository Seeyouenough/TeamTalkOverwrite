package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMPower;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IPowerService {

    IMPower getById(Integer id);

    IMPower getByName(String userName);

    Boolean add(IMPower user);

    List<IMPower> getAll();

    Boolean delete(Integer id);

    Boolean update(IMPower user);




}
