package dynamicProxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Created by xuan on 2017/9/2.
 */
public class Main {
    public static void main(String[] args) {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real)
        );

        proxySubject.doSomething();

        createProxyClassFile();
    }

    private static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name,
                new Class[]{Subject.class});
        try {
            FileOutputStream out = new FileOutputStream("ProxySubject.class");
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
