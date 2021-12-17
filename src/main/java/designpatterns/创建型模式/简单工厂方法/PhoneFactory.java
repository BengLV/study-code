package designpatterns.创建型模式.简单工厂方法;

public class PhoneFactory {
    public Phone createPhone(String phoneType) {
        if (phoneType.equals("mi")) {
           return new MiPhoneImpl();
        } else if (phoneType.equals("apple")) {
            return new ApplePhoneImpl();
        }
        return null;
    }
}
