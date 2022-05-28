package basic.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description: 方法拦截器
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class DebugMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 代理对象(增强的对象)
     * @param method 被拦截的方法(需要增强的方法)
     * @param objects 方法入参
     * @param methodProxy 用于调用原始方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用方法前,自定义操作");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("调用方法后,自定义操作");
        return object;
    }
}
