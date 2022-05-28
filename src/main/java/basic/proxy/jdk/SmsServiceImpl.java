package basic.proxy.jdk;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-05-28
 **/
public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String msg) {
        System.out.println("发送短信:" + msg);
        return msg;
    }

}
