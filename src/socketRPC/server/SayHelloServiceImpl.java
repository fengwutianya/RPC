package socketRPC.server;

/**
 * Created by xuan on 2017/9/3.
 */
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String sayHello(String helloArg) {
        if (helloArg.equals("exit")) {
            return "exit";
        } else {
            return "hello, " + helloArg;
        }
    }
}
