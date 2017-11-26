package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMManager;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IManagerService {

    IMManager getId(Integer id);

    IMManager getName(String userName);

    IMManager getToken(String token);

    Boolean add(IMManager user);

    List<IMManager> getAll();

    Boolean updatePassword(IMManager user);

}
