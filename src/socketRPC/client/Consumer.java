package socketRPC.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by xuan on 2017/9/3.
 */
public class Consumer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String className = "socketRPC.server.SayHelloServiceImpl";
        String methodName = "sayHello";
        Class<?>[] parameterTypes = new Class<?>[]{String.class};
        Object[] arguments = new String[]{"xuanxuan"};

        Socket socket = new Socket("localhost", 12345);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        out.writeUTF(className);
        out.writeUTF(methodName);
        out.writeObject(parameterTypes);
        out.writeObject(arguments);

        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        Object result = input.readObject();
        System.out.println(result);
        socket.close();
    }
}
