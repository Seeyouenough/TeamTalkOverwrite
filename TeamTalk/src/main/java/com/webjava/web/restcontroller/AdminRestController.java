package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */


import com.admin.grpc.AdminRequest;
import com.admin.grpc.AdminResponse;
import com.admin.grpc.AdminServiceGrpc;
import com.google.gson.Gson;
import com.webjava.kernel.entity.IMAdmin;
import com.webjava.kernel.service.IAdminService;
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
import java.io.*;


@RestController
@RequestMapping("/users")
public class AdminRestController {
    @Resource
    private IAdminService adminService;

    private static final String HOST = "localhost";
    private static final int PORT = 50051;


    @RequestMapping(value="/login", method= RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String adminname = request.getParameter("username");
        String password = request.getParameter("password");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        AdminServiceGrpc.AdminServiceBlockingStub stub =
                AdminServiceGrpc.newBlockingStub(channel);

        // Create a request
        AdminRequest loginRequest = AdminRequest.newBuilder()
                .setUname(adminname)
                .setPwd(password)
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        AdminResponse adminResponse = stub.login(loginRequest);

        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream("D:\\working\\IDEAProject\\TeamTalkOverwrite\\TeamTalk\\Menus.json"),"UTF-8"));
        String str;
        String data;
        StringBuffer strb =new StringBuffer();
        while((str=br.readLine())!=null){
            strb.append(str);

        }
        System.out.println(strb);
        data=strb.toString();


        if(adminResponse.getStatusId()==0){

            HttpUtils.setJsonBody(response,new ResponseInfo(0,"很好",data));

        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名或密码错误"));
        }

    }

    @RequestMapping(value="/modify", method= RequestMethod.POST)
    public void modify(HttpServletRequest request, HttpServletResponse response){

        String strData=HttpUtils.getJsonBody(request);

        Gson gson = new Gson();
        IMAdmin admin=gson.fromJson(strData,IMAdmin.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        AdminServiceGrpc.AdminServiceBlockingStub stub =
                AdminServiceGrpc.newBlockingStub(channel);

        // Create a request
        AdminRequest modifyRequest = AdminRequest.newBuilder()
                .setUname(admin.getUname())
                .setPwd(admin.getPwd())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        AdminResponse modifyResponse = stub.modifyPassword(modifyRequest);

        if(modifyResponse.getStatusId()==1){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"修改成功!"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改失败!"));
        }

    }

    @RequestMapping(value = "/admin/add",method = RequestMethod.POST)
    public void addAdmin(HttpServletRequest request, HttpServletResponse response){

        String  strJson= HttpUtils.getJsonBody(request);

        Gson gson=new Gson();

        IMAdmin user=gson.fromJson(strJson,IMAdmin.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        AdminServiceGrpc.AdminServiceBlockingStub stub =
                AdminServiceGrpc.newBlockingStub(channel);

        // Create a request
        AdminRequest addAdminRequest = AdminRequest.newBuilder()
                .setUname(user.getUname())
                .setPwd(user.getPwd())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        AdminResponse adminResponse = stub.addAdmin(addAdminRequest);

        if(adminResponse.getStatusId()==0){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"添加成功"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"内容存在"));
        }


    }


}