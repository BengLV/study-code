package designpatterns.创建型模式.工厂方法模式.简单版;


public class TestFactory {

    public static void main(String[] args) {
        AbstractFactory appleFactory = new AppleFactory();
        AbstractFactory miFactory = new MiFactory();
        appleFactory.makePhone();
        miFactory.makePhone();
    }

}
