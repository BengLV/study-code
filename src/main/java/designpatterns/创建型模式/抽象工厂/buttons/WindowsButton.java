package designpatterns.创建型模式.抽象工厂.buttons;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}
