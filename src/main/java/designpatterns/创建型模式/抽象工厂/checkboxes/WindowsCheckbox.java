package designpatterns.创建型模式.抽象工厂.checkboxes;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox.");
    }
}