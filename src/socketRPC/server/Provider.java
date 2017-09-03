package socketRPC.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xuan on 2017/9/3.
 */
public class Provider {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ServerSocket serverSocket = new ServerSocket(12345);
        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            //类名
            String className = ois.readUTF();
            //方法名
            String methodName = ois.readUTF();
            //方法参数类型
            Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
            //参数值
            Object[] arguments = (Object[]) ois.readObject();

            //开始调用
            //load class
            Class serviceClass = Class.forName(className);
            //new service Object
            Object object = serviceClass.newInstance();
            //get method, method由类名，方法名，参数类型确定，重载
            Method method = serviceClass.getMethod(
                    methodName,
                    parameterTypes
            );
            //调用函数.获得结果
            Object result = method.invoke(object, arguments);
            //传回去
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            socket.close();
        }
    }
}
