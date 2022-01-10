package designpatterns.创建型模式.抽象工厂.factories;

import designpatterns.创建型模式.抽象工厂.buttons.Button;
import designpatterns.创建型模式.抽象工厂.buttons.WindowsButton;
import designpatterns.创建型模式.抽象工厂.checkboxes.Checkbox;
import designpatterns.创建型模式.抽象工厂.checkboxes.WindowsCheckbox;

public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}