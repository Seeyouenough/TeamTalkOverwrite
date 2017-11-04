package com.webjava.kernel.service;

import com.webjava.kernel.entity.IMDepart;
import com.webjava.kernel.entity.IMUser;

import java.util.List;

/**
 * Created by wx on 2017/10/27.
 */
public interface IDepartService {

    IMDepart getDepartById(Integer id);

    IMDepart getDepartByName(String departName);

    List<IMDepart> getAllDepart();

    Boolean addDepart(IMDepart user);

    Boolean deleteDepart(Integer id);

    Boolean updateDepart(IMDepart user);
}
