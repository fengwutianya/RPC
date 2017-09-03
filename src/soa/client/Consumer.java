package soa.client;

import net.sf.json.JSONObject;
import soa.ClassWays;
import soa.ServiceRoute;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by xuan on 2017/9/3.
 */
public class Consumer {
    public Object reallyUse(String provideName, Object[] arguments) throws IOException, ClassNotFoundException {
        ServiceRoute serviceRoute = new ServiceRoute();
        String js = serviceRoute.NAME.get(provideName);
        JSONObject obj = new JSONObject().fromObject(js);
        ClassWays classWays = (ClassWays) JSONObject.toBean(obj, ClassWays.class);

        String classname = classWays.getClassName();
        String method = classWays.getMethod();
        Class[] argumentTypes = classWays.getArgumentTypes();
        Socket socket = new Socket(classWays.getIp(), classWays.getPort());

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        output.writeUTF(classname);
        output.writeUTF(method);
        output.writeObject(argumentTypes);
        output.writeObject(arguments);

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object result = input.readObject();
        socket.close();
        return result;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Consumer consumer = new Consumer();
        Object[] arguments = {"xuanxuan"};
        Object result = consumer.reallyUse("SayHello", arguments);
        System.out.println(result);
    }
}
