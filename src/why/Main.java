package why;

/**
 * ���ط������
 * Created by xuan on 2017/9/2.
 */
public class Main {
    public static void main(String[] args) {
        HelloWorldService helloWorldService =
                new HelloWorldServiceImpl();
        helloWorldService.sayHello("xuanxuan");
    }
}
