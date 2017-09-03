package soa;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuan on 2017/9/3.
 */
public class ServiceRoute {
    public static Map<String, String> NAME = new HashMap<>();

    public ServiceRoute() {
        ClassWays classWays = new ClassWays();
        Class[] argumentTypes = {String.class};
        classWays.setArgumentTypes(argumentTypes);
        classWays.setClassName("socketRPC.server.SayHelloServiceImpl");

        classWays.setMethod("sayHello");
        classWays.setIp("127.0.0.1");
        classWays.setPort(12345);
        JSONObject js = JSONObject.fromObject(classWays);
        NAME.put("SayHello", js.toString());
    }
}
