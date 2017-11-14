package com.grpc.java.service;



import com.grpc.java.kernel.entity.IMAdmin;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IAdminService {

    IMAdmin getAdminById(Integer id);

    IMAdmin getAdminByName(String userName);

    Boolean addAdmin(IMAdmin user);

    List<IMAdmin> getAllAdmin();

    Boolean updatePassword(IMAdmin user);

}
