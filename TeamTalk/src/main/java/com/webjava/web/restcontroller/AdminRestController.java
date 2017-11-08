package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */


import com.admin.grpc.Admin;
import com.admin.grpc.AdminRequest;
import com.admin.grpc.AdminResponse;
import com.admin.grpc.AdminServiceGrpc;
import com.google.gson.Gson;
import com.webjava.kernel.entity.IMAdmin;
import com.webjava.kernel.service.IAdminService;
import com.webjava.utils.EncryptHelper;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

    private static final String HOST = "localhost";
    private static final int PORT = 50051;


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        AdminServiceGrpc.AdminServiceBlockingStub stub =
                AdminServiceGrpc.newBlockingStub(channel);

        // Create a request
        AdminRequest adminRequest = AdminRequest.newBuilder()
                .setUname(adminname)
                .setPwd(password)
                .build();

        // Send the request using the stub
        System.out.println("GreeterClient sending request");
        AdminResponse adminResponse = stub.login(adminRequest);

        if(adminResponse.getStatusId()==0){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"很好"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名或密码错误"));
        }


        /*password = EncryptHelper.encodeByMD5(password);

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
        }*/
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