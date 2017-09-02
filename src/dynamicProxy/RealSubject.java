package dynamicProxy;

/**
 * Created by xuan on 2017/9/2.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}
