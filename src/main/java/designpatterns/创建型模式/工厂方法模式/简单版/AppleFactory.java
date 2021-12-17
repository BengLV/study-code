package designpatterns.创建型模式.工厂方法模式.简单版;

import designpatterns.创建型模式.简单工厂方法.ApplePhoneImpl;
import designpatterns.创建型模式.简单工厂方法.Phone;

public class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new ApplePhoneImpl();
    }
}
