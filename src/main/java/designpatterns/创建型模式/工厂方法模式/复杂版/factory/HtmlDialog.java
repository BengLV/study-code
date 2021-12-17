package designpatterns.创建型模式.工厂方法模式.复杂版.factory;

import designpatterns.创建型模式.工厂方法模式.复杂版.buttons.Button;
import designpatterns.创建型模式.工厂方法模式.复杂版.buttons.HtmlButton;

/**
 * 具体创建者-- 将生成 HTML 按钮
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }

}
