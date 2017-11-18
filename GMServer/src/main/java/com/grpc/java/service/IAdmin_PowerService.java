package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMAdmin_Power;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IAdmin_PowerService {

    IMAdmin_Power getById(Integer id);

    Boolean add(IMAdmin_Power user);

    List<IMAdmin_Power> getAll();

    Boolean delete(Integer id);

    Boolean update(IMAdmin_Power user);


}
