package designpatterns.创建型模式.抽象工厂.factories;

import designpatterns.创建型模式.抽象工厂.buttons.Button;
import designpatterns.创建型模式.抽象工厂.checkboxes.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
