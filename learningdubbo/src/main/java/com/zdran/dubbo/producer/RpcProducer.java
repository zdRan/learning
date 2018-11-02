package com.zdran.dubbo.producer;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 提供服务（建立连接，使客户端和服务端通信）
 * Create by ranzd on 2018/11/2
 *
 * @author ranzd@chinaunicom.cn
 */
public class RpcProducer {
    private static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void produce(String host, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(host, port));
        try {
            while (true) {
                executor.execute(new ProducerAgent(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }
}
