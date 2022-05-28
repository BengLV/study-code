package basic.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class Demo {

    public static void main(String[] args) {
        AliSmsService sms = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        sms.send("java");
    }

}
