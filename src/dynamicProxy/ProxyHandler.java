package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuan on 2017/9/2.
 */
public class ProxyHandler implements InvocationHandler {
    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        //代理功能
        System.out.println("proxy");

        return method.invoke(proxied, args);
    }
}
