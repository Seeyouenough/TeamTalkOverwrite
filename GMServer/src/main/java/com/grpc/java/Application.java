package com.grpc.java;

import com.grpc.java.kernel.mybatis.mapper.IMAdminMapper;
import com.grpc.java.server.AdminServerImpl;
import com.grpc.java.service.IAdminService;
import com.grpc.java.service.implement.AdminServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by wx on 2017/11/8.
 */
public class Application {
    private static final int SERVER_PORT = 50051;

    public static void main(String args[])throws IOException, InterruptedException{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring/spring-mvc.xml");
        BeanContainer service = (BeanContainer) ctx.getBean("beanService");

        // Build server
        io.grpc.Server server = ServerBuilder.forPort(SERVER_PORT)
                .addService(new AdminServerImpl(service))
                .build();

        // Start server
        System.out.println("Starting GreeterServer on port " + SERVER_PORT);
        server.start();

        // Keep it running
        System.out.println("GreeterServer started!");
        server.awaitTermination();

    }

}
