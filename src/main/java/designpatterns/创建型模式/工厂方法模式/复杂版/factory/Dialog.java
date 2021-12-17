package designpatterns.创建型模式.工厂方法模式.复杂版.factory;

import designpatterns.创建型模式.工厂方法模式.复杂版.buttons.Button;

/**
 * 基础创建者--拥有不同产品的核心业务逻辑
 */
public abstract class Dialog {

    /**
     * 渲染窗口,无需重写
     */
    public void renderWindow() {
        // ... other code ...
        Button okButton = createButton();
        okButton.render();
    }

    /**
     * 子类将覆盖此方法以创建特定按钮对象
     */
    public abstract Button createButton();

}
