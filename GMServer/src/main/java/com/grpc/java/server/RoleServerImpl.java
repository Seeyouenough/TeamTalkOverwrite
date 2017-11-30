package com.grpc.java.server;

import com.grpc.java.kernel.entity.role_info;
import com.grpc.java.service.RoleService;
import com.role.grpc.Role;
import com.role.grpc.RoleRequest;
import com.role.grpc.RoleResponse;
import com.role.grpc.RoleServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.List;


/**
 * Created by wx on 2017/11/8.
 */

public class RoleServerImpl extends RoleServiceGrpc.RoleServiceImplBase {
    private RoleService roleService;

    public RoleServerImpl(BeanContainer service) {
        this.roleService= service.roleService;
    }

    @Override
    public void addRole(RoleRequest request, StreamObserver<RoleResponse> responseStreamObserver){
        String name=request.getRoleName();
        int status=-1;

        role_info role=roleService.getName(name);
        if(role!=null){
            status=1;
        }else {
            role.setRoleName(name);
            roleService.add(role);
            status=0;
        }

        RoleResponse response = RoleResponse.newBuilder()
                .setStatusId(status)
                .build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void modifyRole(RoleRequest request, StreamObserver<RoleResponse> responseStreamObserver){
        int id=request.getRoleId();
        int status=-1;

        role_info role=roleService.getId(id);
        if(role!=null){
            role.setRoleName(request.getRoleName());
            roleService.update(role);
            status=0;
        }
        else {
            status=1;
        }

        RoleResponse response=RoleResponse.newBuilder()
                .setStatusId(status)
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void listRole(RoleRequest request, StreamObserver<RoleResponse> responseStreamObserver){
        List<role_info> data =roleService.getAll();
        RoleResponse.Builder builder=RoleResponse.newBuilder();
        int status=-1;

        if(data.size()>0){
            for(role_info ri :data) {
                Role.Builder bu = Role.newBuilder();
                bu.setRoleId(ri.getRoleId());
                bu.setRoleName(ri.getRoleName());

                builder.addRole(bu);
            }
            status=0;
        }
        else
        {
            status=1;
        }

        RoleResponse response=builder.setStatusId(status).build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

    @Override
    public void removeRole(RoleRequest request,StreamObserver<RoleResponse> responseStreamObserver){
        List<Role> ids=request.getRoleList();
        int status=-1;
        int conunt=0;

        for(Role id : ids){
            role_info role =roleService.getId(id.getRoleId());
            if(role!=null){
                roleService.delete(role.getRoleId());
                conunt++;
            }else {
                System.out.println("数据库无此id:" + role.getRoleId() + "对应的信息!");
            }
        }
        if(conunt==ids.size()){
            status=0;
        }else {
            status=1;
        }

        RoleResponse response=RoleResponse.newBuilder()
                .setStatusId(status)
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }


}
