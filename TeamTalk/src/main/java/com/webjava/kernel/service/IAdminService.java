package com.webjava.kernel.service;

import com.webjava.kernel.entity.IMAdmin;
import com.webjava.kernel.entity.IMUser;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IAdminService {

    IMAdmin getAdminById(Integer id);

    IMAdmin getAdminByName(String userName);

    List<IMAdmin> getAllAdmin();

    Boolean updatePassword(IMAdmin user);

}
