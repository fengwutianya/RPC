package soa;

/**
 * Created by xuan on 2017/9/3.
 */
public class ClassWays {
    private String className;
    private String method;
    private Class[] argumentTypes;
    private String ip;
    private int port;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class[] getArgumentTypes() {
        return argumentTypes;
    }

    public void setArgumentTypes(Class[] argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
