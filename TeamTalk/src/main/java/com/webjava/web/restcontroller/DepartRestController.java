package com.webjava.web.restcontroller;

/**
 * Created by wx on 2017/10/27.
 */

import com.depart.grpc.Depart;
import com.depart.grpc.DepartRequest;
import com.depart.grpc.DepartResponse;
import com.depart.grpc.DepartServiceGrpc;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.webjava.kernel.entity.IMDepart;
import com.webjava.kernel.service.IDepartService;
import com.webjava.model.IDList;
import com.webjava.model.IDObject;
import com.webjava.utils.HttpUtils;
import com.webjava.utils.ResponseInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/users")
public class DepartRestController {
    public static Logger logger= LogManager.getLogger(DepartRestController.class.getName());
    @Resource
    private IDepartService departService;


    private static final String HOST = "localhost";
    private static final int PORT = 50051;

    @RequestMapping(value = "/depart/list",method = RequestMethod.GET)
    public void listDepart(HttpServletRequest request, HttpServletResponse response) throws InvalidProtocolBufferException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        DepartServiceGrpc.DepartServiceBlockingStub stub =
                DepartServiceGrpc.newBlockingStub(channel);

        // Create a request
        DepartRequest listDepartRequest = DepartRequest.newBuilder().build();

        // Send the request using the stub
        System.out.println("Client sending request");
        DepartResponse departResponse = stub.listDepart(listDepartRequest);


        if(departResponse.getStatusId()==1){

            String data= JsonFormat.printer().includingDefaultValueFields().preservingProtoFieldNames().print(departResponse);

            HttpUtils.setJsonBody(response,new ResponseInfo(1,"显示所有用户",data));
        }else
        {
            System.out.println("nothing");
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"无内容"));
        }

    }

    @RequestMapping(value = "/depart/add",method = RequestMethod.POST)
    public void addDepart(HttpServletRequest request, HttpServletResponse response){

        String  strJson= HttpUtils.getJsonBody(request);

        Gson gson=new Gson();

        IMDepart depart=gson.fromJson(strJson,IMDepart.class);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        DepartServiceGrpc.DepartServiceBlockingStub stub =
                DepartServiceGrpc.newBlockingStub(channel);

        // Create a request
        DepartRequest addDepartRequest = DepartRequest.newBuilder()
                .setDepartname(depart.getDepartname())
                .setPriority(depart.getPriority())
                .setParentid(depart.getParentid())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        DepartResponse departResponse = stub.addDepart(addDepartRequest);

        if(departResponse.getStatusId()==0){
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"添加成功"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"内容存在"));
        }


    }

    @RequestMapping(value = "/depart/remove",method = RequestMethod.POST)
    public void removeDepart(HttpServletRequest request,HttpServletResponse response ) {

        String strjson = HttpUtils.getJsonBody(request);
        Gson gson = new Gson();
        IDList IDs = gson.fromJson(strjson, IDList.class);


        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        DepartServiceGrpc.DepartServiceBlockingStub stub =
                DepartServiceGrpc.newBlockingStub(channel);

        DepartRequest.Builder builder = DepartRequest.newBuilder();
        // Create a request
        for (IDObject id: IDs.getParams()) {
            Depart.Builder bu = Depart.newBuilder();
            bu.setId(id.getId());
            Depart depart =bu.build();

            builder.addDepart(depart);
        }

        DepartRequest removeDepartRequest = builder.build();

        // Send the request using the stub
        System.out.println("Client sending request");
        DepartResponse departResponse = stub.removeDepart(removeDepartRequest);

        if (departResponse.getStatusId()==1) {
            HttpUtils.setJsonBody(response, new ResponseInfo(1, "删除成功！"));
        } else {
            HttpUtils.setJsonBody(response, new ResponseInfo(0, "部分信息未找到或未删除！"));
        }

    }

    @RequestMapping(value = "/depart/update",method = RequestMethod.POST)
    public void updateDepart (HttpServletRequest request,HttpServletResponse response){
        String strData =HttpUtils.getJsonBody(request);
        Gson gson=new Gson();
        IMDepart depart = gson.fromJson(strData,IMDepart.class);


        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, PORT)
                .usePlaintext(true)
                .build();

        // Create a blocking stub with the channel
        DepartServiceGrpc.DepartServiceBlockingStub stub =
                DepartServiceGrpc.newBlockingStub(channel);

        // Create a request
        DepartRequest modifyDepartRequest = DepartRequest.newBuilder()
                .setId(depart.getId())
                .setDepartname(depart.getDepartname())
                .setPriority(depart.getPriority())
                .setParentid(depart.getParentid())
                .build();

        // Send the request using the stub
        System.out.println("Client sending request");
        DepartResponse departResponse = stub.modifyDepart(modifyDepartRequest);

        if(departResponse.getStatusId()==1){
            HttpUtils.setJsonBody(response,new ResponseInfo(1,"修改成功"));
        }else
        {
            HttpUtils.setJsonBody(response,new ResponseInfo(0,"内容不存在"));
        }


    }


}