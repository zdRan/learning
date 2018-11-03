package com.zdran.dubbo.registration;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by ranzd on 2018/11/2
 *
 * @author cm.zdran@gmail.com
 */
public class RegistrationCenter {

    private Map<String, InetSocketAddress> serviceMap = new HashMap<>();

    public void register(String serviceName, String host, int port) {
        serviceMap.put(serviceName, new InetSocketAddress(host, port));
    }

    public InetSocketAddress getService(String serviceName) {
        return serviceMap.get(serviceName);
    }
}
