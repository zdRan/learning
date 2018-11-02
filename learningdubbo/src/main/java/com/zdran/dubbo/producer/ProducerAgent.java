package com.zdran.dubbo.producer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 服务通信的具体实现（服务端的具体实现）
 * 接收 LocalAgent 发送过来的数据，通过反射调用对应的方法，并将结果序列化之后发送给 LocalAgent
 * Create by ranzd on 2018/11/2
 *
 * @author ranzd@chinaunicom.cn
 */
public class ProducerAgent implements Runnable {
    Socket client = null;

    public ProducerAgent(Socket accept) {
        client = accept;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {

            inputStream = new ObjectInputStream(client.getInputStream());

            String interfaceName = inputStream.readUTF();
            Class<?> service = Class.forName(interfaceName);
            String methodName = inputStream.readUTF();
            Class<?>[] paramTypes = (Class<?>[]) inputStream.readObject();
            Object[] args = (Object[]) inputStream.readObject();

            Method method = service.getMethod(methodName, paramTypes);
            Object result = method.invoke(service.newInstance(), args);
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
