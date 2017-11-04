package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */


import com.google.gson.Gson;
import com.webjava.kernel.entity.IMAdmin;
import com.webjava.kernel.service.IAdminService;
import com.webjava.utils.EncryptHelper;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/users")
public class AdminRestController {
    @Resource
    private IAdminService adminService;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");

        password = EncryptHelper.encodeByMD5(password);

        System.out.print(adminname);

        IMAdmin user = this.adminService.getAdminByName(adminname);
        if (user != null && user.getPwd().equals(password))
        {
            System.out.print("okok");
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"很好"));
        }
        else
        {
            System.out.print("fail");
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名或密码错误"));
        }
    }

    @RequestMapping(value="/modify", method= RequestMethod.POST)
    public void modify(HttpServletRequest request, HttpServletResponse response){

        String strData=HttpUtils.getJsonBody(request);

        Gson gson = new Gson();
        IMAdmin admin=gson.fromJson(strData,IMAdmin.class);

        IMAdmin user = this.adminService.getAdminByName(admin.getUname());
        if(user!=null){
            admin.setPwd(EncryptHelper.encodeByMD5(admin.getPwd()));
            this.adminService.updatePassword(admin);
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改成功!"));
        }else{
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"修改失败!"));
        }
    }



}