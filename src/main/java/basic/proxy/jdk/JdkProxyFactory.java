package basic.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @description: 获取代理对象的工厂类
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class JdkProxyFactory {

   public static Object getProxy(Object object) {
       return Proxy.newProxyInstance(object.getClass().getClassLoader(),//目标类的加载
               object.getClass().getInterfaces(),//代理类需要实现的接口,可指定多个
               new DebugInvocationHandler(object));//代理对象
   }
}
