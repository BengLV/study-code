package designpatterns.创建型模式.简单工厂方法;

public class MiPhoneImpl implements Phone {

    public MiPhoneImpl() {
        this.makePhone();
    }


    @Override
    public void makePhone() {
        System.out.println("制造小米手机!");
    }
}
