package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */


import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.manager.grpc.ManagerRequest;
import com.manager.grpc.ManagerResponse;
import com.manager.grpc.ManagerServiceGrpc;
import com.webjava.kernel.entity.IMManager;
import com.webjava.model.CheckLogin;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/users")
public class ManagerRestController {

    private static final String HOST = "localhost";
    private static final int PORT = 50051;

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String dataStr =HttpUtils.getJsonBody(request);
        Gson gson=new Gson();
        CheckLogin checkLogin=gson.fromJson(dataStr,CheckLogin.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        ManagerServiceGrpc.ManagerServiceBlockingStub stub =
                ManagerServiceGrpc.newBlockingStub(channel);

        // Create a request
        ManagerRequest loginRequest = ManagerRequest.newBuilder()
                .setUsername(checkLogin.getUsername())
                .setPassword(checkLogin.getPassword())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        ManagerResponse managerResponse = stub.login(loginRequest);


        if(managerResponse.getStatusId()==0){
            String data= JsonFormat.printer().preservingProtoFieldNames().print(managerResponse.getManager(0));
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"登录成功",data));

        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"用户名或密码错误"));
        }

    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(HttpServletRequest request,HttpServletResponse response)
    {
        HttpUtils.setJsonBody(response ,new ResponseInfo(0,"success"));
    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.GET)
    public void getInfo(HttpServletRequest request,HttpServletResponse response) throws InvalidProtocolBufferException {
          String token = request.getParameter("token");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        ManagerServiceGrpc.ManagerServiceBlockingStub stub =
                ManagerServiceGrpc.newBlockingStub(channel);

        // Create a request
        ManagerRequest getRequest = ManagerRequest.newBuilder()
                .setToken(token)
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        ManagerResponse getResponse = stub.getInfo(getRequest);


        if(getResponse.getStatusId()==0){
            String data= JsonFormat.printer().preservingProtoFieldNames().print(getResponse.getManager(0));
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"获取成功",data));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"获取失败"));
        }

    }

    @RequestMapping(value="/manager/modify", method= RequestMethod.POST)
    public void modify(HttpServletRequest request, HttpServletResponse response){

        String strData=HttpUtils.getJsonBody(request);

        Gson gson = new Gson();
        IMManager admin=gson.fromJson(strData,IMManager.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        ManagerServiceGrpc.ManagerServiceBlockingStub stub =
                ManagerServiceGrpc.newBlockingStub(channel);

        // Create a request
        ManagerRequest modifyRequest = ManagerRequest.newBuilder()
                .setUsername(admin.getUsername())
                .setPassword(admin.getPassword())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        ManagerResponse modifyResponse = stub.modifyPassword(modifyRequest);

        if(modifyResponse.getStatusId()==1){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"修改成功!"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改失败!"));
        }

    }

    @RequestMapping(value = "/manager/add",method = RequestMethod.POST)
    public void addManager(HttpServletRequest request, HttpServletResponse response){

        String  strJson= HttpUtils.getJsonBody(request);

        Gson gson=new Gson();

        IMManager user=gson.fromJson(strJson,IMManager.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        ManagerServiceGrpc.ManagerServiceBlockingStub stub =
                ManagerServiceGrpc.newBlockingStub(channel);

        // Create a request
        ManagerRequest addManagerRequest = ManagerRequest.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        ManagerResponse adminResponse = stub.addManager(addManagerRequest);

        if(adminResponse.getStatusId()==0){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"添加成功"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"内容存在"));
        }


    }

    @RequestMapping(value = "/manager/list", method = RequestMethod.GET)
    public void listManager(HttpServletRequest request,HttpServletResponse response){

    }


}