package com.grpc.java.server;

import com.grpc.java.kernel.entity.manager_info;
import com.grpc.java.service.ManagerService;
import com.grpc.java.utils.EncryptHelper;
import com.manager.grpc.Manager;
import com.manager.grpc.ManagerRequest;
import com.manager.grpc.ManagerResponse;
import com.manager.grpc.ManagerServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.List;

/**
 * Created by wx on 2017/11/8.
 */

public class ManagerServerImpl extends ManagerServiceGrpc.ManagerServiceImplBase {
    private ManagerService managerService;

    public ManagerServerImpl(BeanContainer service) {
        this.managerService= service.managerService;
    }


    @Override
    public void login(ManagerRequest request, StreamObserver<ManagerResponse> responseStreamObserver){
        System.out.println("Received request: " + request);

        String username = request.getUsername();
        String password = request.getPassword();

        password = EncryptHelper.encodeByMD5(password);
        manager_info manager = this.managerService.getName(username);
        if (manager != null && manager.getPassword().equals(password))
        {
            System.out.print("okok");
            Manager.Builder aa=Manager.newBuilder();
            aa.setId(manager.getManagerId());
            aa.setUsername(manager.getUsername());
            aa.setAvatar(manager.getAvatar());
            aa.setIntroduction(manager.getIntroduction());
            aa.setToken(manager.getToken());
            ManagerResponse.Builder builder=ManagerResponse.newBuilder();
            builder.addManager(aa);
            ManagerResponse response =builder
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }
        else
        {
            System.out.print("fail");
            ManagerResponse response = ManagerResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void getInfo(ManagerRequest request,StreamObserver<ManagerResponse> responseStreamObserver){

        String token =request.getToken();

        manager_info manager=this.managerService.getToken(token);
        if(manager!=null){
            Manager.Builder aa=Manager.newBuilder();
            aa.setId(manager.getManagerId());
            aa.setUsername(manager.getUsername());
            aa.setAvatar(manager.getAvatar());
            aa.setIntroduction(manager.getIntroduction());
            aa.setToken(manager.getToken());
            ManagerResponse.Builder builder=ManagerResponse.newBuilder();
            builder.addManager(aa);
            ManagerResponse response =builder.setStatusId(0).build();
            responseStreamObserver.onNext(response);
        }
        else {
            ManagerResponse response = ManagerResponse.newBuilder().setStatusId(1).build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void listManager(ManagerRequest request,StreamObserver<ManagerResponse> responseStreamObserver){
        List<manager_info> manager = managerService.getAll();
        int status = -1;
        ManagerResponse.Builder builder=ManagerResponse.newBuilder();

        if(manager.size()>0){
            for(int i=0; i<manager.size(); i++){
                Manager.Builder bu=Manager.newBuilder();
                bu.setId(manager.get(i).getManagerId());
                bu.setUsername(manager.get(i).getUsername());
                bu.setIntroduction(manager.get(i).getIntroduction());
                builder.addManager(bu);
            }
            status =0;
        }
        else {
            status =1;
        }

        ManagerResponse response=builder.setStatusId(status).build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void modifyPassword(ManagerRequest request, StreamObserver<ManagerResponse> responseStreamObserver){

        System.out.println("Received request: " + request);

        String uname = request.getUsername();
        String pwd = request.getPassword();

        pwd = EncryptHelper.encodeByMD5(pwd);
        manager_info user = this.managerService.getName(uname);
        if(user!=null){
            user.setPassword(pwd);
            this.managerService.updatePassword(user);
            System.out.print("okok");
            ManagerResponse response = ManagerResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);

        }else{
            System.out.print("fail");
            ManagerResponse response = ManagerResponse.newBuilder()
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void addManager(ManagerRequest request, StreamObserver<ManagerResponse> responseStreamObserver){

        String name=request.getUsername();
        manager_info user=new manager_info();

        manager_info existUser =this.managerService.getName(name);
        if(existUser !=null){
            System.out.println("内容已存在");
            ManagerResponse response = ManagerResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);
        }
        else{
            user.setUsername(name);
            user.setPassword(request.getPassword());
            this.managerService.add(user);
            System.out.println("添加成功");
            ManagerResponse response = ManagerResponse.newBuilder()
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void removeManager(ManagerRequest request,StreamObserver<ManagerResponse> responseStreamObserver)
    {

    }

}
