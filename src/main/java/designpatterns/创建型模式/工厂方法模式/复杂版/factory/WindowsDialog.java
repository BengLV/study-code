package designpatterns.创建型模式.工厂方法模式.复杂版.factory;

import designpatterns.创建型模式.工厂方法模式.复杂版.buttons.Button;
import designpatterns.创建型模式.工厂方法模式.复杂版.buttons.WindowsButton;

/**
 * 具体创建者-- 将生成 Windows 按钮
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

}
