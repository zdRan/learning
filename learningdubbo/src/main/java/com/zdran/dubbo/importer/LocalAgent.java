package com.zdran.dubbo.importer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 本地代理 RPC客户端的具体实现
 * 将需要调用的服务接口类，序列化后通过socket把类名、方法名、参数类型、参数发送给 ProducerAgent
 * 并通过 socket 接收 ProducerAgent 发送过来的数据，反序列化生成结果对象
 * Create by ranzd on 2018/11/2
 *
 * @author ranzd@chinaunicom.cn
 */
public class LocalAgent<T> {
    public T importer(final Class<?> serviceClass, final InetSocketAddress addr) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class<?>[]{serviceClass.getInterfaces()[0]},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        ObjectOutputStream outputStream = null;
                        ObjectInputStream inputStream = null;
                        Socket socket = null;
                        try {
                            socket = new Socket();
                            socket.connect(addr);
                            outputStream = new ObjectOutputStream(socket.getOutputStream());

                            outputStream.writeUTF(serviceClass.getName());
                            outputStream.writeUTF(method.getName());
                            outputStream.writeObject(method.getParameterTypes());
                            outputStream.writeObject(args);

                            inputStream = new ObjectInputStream(socket.getInputStream());
                            return inputStream.readObject();
                        } finally {
                            socket.close();
                            outputStream.close();
                            inputStream.close();
                        }

                    }
                });
    }
}
