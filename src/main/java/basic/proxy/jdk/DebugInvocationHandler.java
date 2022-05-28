package basic.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: 动态代理类
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class DebugInvocationHandler implements InvocationHandler {

    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //可以定义自己的操作
        System.out.println("调用方法前,自定义操作");
        Object result = method.invoke(target, args);
        System.out.println("调用方法后,自定义操作");
        return result;
    }

}
