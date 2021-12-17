package designpatterns.创建型模式.简单工厂方法;

public class TestFactory {

    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        phoneFactory.createPhone("mi");
        phoneFactory.createPhone("apple");

    }

}
