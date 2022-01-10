package designpatterns.创建型模式.抽象工厂.app;

import designpatterns.创建型模式.抽象工厂.buttons.Button;
import designpatterns.创建型模式.抽象工厂.checkboxes.Checkbox;
import designpatterns.创建型模式.抽象工厂.factories.GUIFactory;

public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}