package com.grpc.java.server;

import com.admin.grpc.AdminRequest;
import com.admin.grpc.AdminResponse;
import com.admin.grpc.AdminServiceGrpc;
import com.grpc.java.kernel.entity.IMAdmin;
import com.grpc.java.service.IAdminService;
import com.grpc.java.utils.EncryptHelper;
import io.grpc.stub.StreamObserver;

/**
 * Created by wx on 2017/11/8.
 */

public class AdminServerImpl extends AdminServiceGrpc.AdminServiceImplBase {
    private IAdminService adminService;

    public AdminServerImpl(BeanContainer service) {
        this.adminService= service.adminService;
    }
    @Override
    public void login(AdminRequest request, StreamObserver<AdminResponse> responseStreamObserver){
        System.out.println("Received request: " + request);

        String uname = request.getUname();
        String pwd = request.getPwd();

        pwd = EncryptHelper.encodeByMD5(pwd);

        IMAdmin user = this.adminService.getAdminByName(uname);
        if (user != null && user.getPwd().equals(pwd))
        {
            System.out.print("okok");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }
        else
        {
            System.out.print("fail");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void modifyPassword(AdminRequest request, StreamObserver<AdminResponse> responseStreamObserver){

        System.out.println("Received request: " + request);

        String uname = request.getUname();
        String pwd = request.getPwd();

        pwd = EncryptHelper.encodeByMD5(pwd);
        IMAdmin user = this.adminService.getAdminByName(uname);
        if(user!=null){
            user.setPwd(pwd);
            this.adminService.updatePassword(user);
            System.out.print("okok");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);

        }else{
            System.out.print("fail");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

    @Override
    public void addAdmin(AdminRequest request, StreamObserver<AdminResponse> responseStreamObserver){

        String name=request.getUname();
        IMAdmin user=new IMAdmin();

        IMAdmin existUser =this.adminService.getAdminByName(name);
        if(existUser !=null){
            System.out.println("内容已存在");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(1)
                    .build();
            responseStreamObserver.onNext(response);
        }
        else{
            user.setUname(name);
            user.setPwd(request.getPwd());
            this.adminService.addAdmin(user);
            System.out.println("添加成功");
            AdminResponse response = AdminResponse.newBuilder()
                    .setStatusId(0)
                    .build();
            responseStreamObserver.onNext(response);
        }

        responseStreamObserver.onCompleted();
    }

}
