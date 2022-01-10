package designpatterns.创建型模式.抽象工厂.factories;

import designpatterns.创建型模式.抽象工厂.buttons.Button;
import designpatterns.创建型模式.抽象工厂.buttons.MacOSButton;
import designpatterns.创建型模式.抽象工厂.checkboxes.Checkbox;
import designpatterns.创建型模式.抽象工厂.checkboxes.MacOSCheckbox;

public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}