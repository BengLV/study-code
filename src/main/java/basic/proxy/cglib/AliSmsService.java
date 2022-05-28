package basic.proxy.cglib;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class AliSmsService {

    public String send (String msg) {
        System.out.println("阿里云发送短信:" + msg);
        return msg;
    }
}
