package designpatterns.创建型模式.简单工厂方法;

public class ApplePhoneImpl implements Phone {
    public ApplePhoneImpl() {
        this.makePhone();
    }

    @Override
    public void makePhone() {
        System.out.println("制造苹果手机！");
    }
}
