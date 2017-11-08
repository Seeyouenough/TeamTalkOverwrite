package com.grpc.java.server;

import com.admin.grpc.AdminRequest;
import com.admin.grpc.AdminResponse;
import com.admin.grpc.AdminServiceGrpc;
import com.grpc.java.BeanContainer;
import com.grpc.java.kernel.entity.IMAdmin;
import com.grpc.java.service.IAdminService;
import com.grpc.java.service.implement.AdminServiceImpl;
import com.grpc.java.utils.EncryptHelper;
import com.grpc.java.utils.MyBatisUtil;
import io.grpc.stub.StreamObserver;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Resource;

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




}
