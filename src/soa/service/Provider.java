package soa.service;

import net.sf.json.JSONObject;
import soa.ClassWays;
import soa.ServiceRoute;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xuan on 2017/9/3.
 */
public class Provider {
    public void reallyUse() {
        ClassWays classWays = new ClassWays();
        Class[] argumentsType = {String.class};
        classWays.setArgumentTypes(argumentsType);
        classWays.setClassName("socketRPC.server.SayHelloServiceImpl");

        classWays.setMethod("sayHello");
        classWays.setIp("127.0.0.1");
        classWays.setPort(12345);

        JSONObject js = JSONObject.fromObject(classWays);

        ServiceRoute.NAME.put("SayHello", js.toString());
    }

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(12345);
        //实际中，这个地方应该调用如下方法，但是因为简单的模拟服务的注册，将注册的信息硬编码在ServiceRoute类中，这个类的构造方法里面会自动注册服务的相关信息。
        //server.reallyUse();
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            String classname = input.readUTF();
            String methodName = input.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
            Object[] arguments = (Object[]) input.readObject();

            Class serviceclass = Class.forName(classname);

            Object object = serviceclass.newInstance();

            Method method = serviceclass.getMethod(methodName, parameterTypes);

            Object result = method.invoke(object, arguments);

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
            socket.close();
        }
    }
}
