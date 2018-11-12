package com.zdran.dubbo.client;

import com.zdran.dubbo.importer.LocalAgent;
import com.zdran.dubbo.producer.RpcProducer;
import com.zdran.dubbo.registration.RegistrationCenter;
import com.zdran.dubbo.service.MyService;
import com.zdran.dubbo.service.impl.MyServiceImpl;

/**
 * 客户端：比如我们自己的项目。要使用 RPC 框架
 * Create by ranzd on 2018/11/2
 *
 * @author cm.zdran@gmail.com
 */
public class MyClient {
    public static void main(String[] args) {
        //创建注册中心
        RegistrationCenter center = new RegistrationCenter();
        //启动服务端
        startProduct(center);
        //客户端代码
        client(center);

    }

    private static void startProduct(RegistrationCenter center) {
        //通过一个线程启动服务端
        String host = "localhost";
        int port = 8878;

        new Thread(() -> {
            try {
                System.out.println("服务端启动........");
                RpcProducer.produce(host, port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //向注册中心注册服务
        center.register("MyService.sayHello", host, port);
    }

    private static void client(RegistrationCenter center) {
        LocalAgent<MyService> serviceLocalAgent = new LocalAgent<>();
        MyService myService = serviceLocalAgent.importer(MyServiceImpl.class,
                center.getService("MyService.sayHello"));

        System.out.println(myService.sayHello("RPC"));
    }
}
