package basic.proxy.jdk;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class Demo {

    public static void main(String[] args) {
        SmsService sms = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        sms.send("java");
    }
}
