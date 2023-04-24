package designpatterns.行为模式.责任链模式.demo2.components;

import designpatterns.行为模式.责任链模式.demo2.middleware.Component;

/**
 * @description: 原始组件应该可以使用帮助操作的默认实现...
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public class Button extends Component {

    private String buttonHelpText;

    @Override
    public void showHelp() {
        //默认调用父类的实现
        super.toolTipText = buttonHelpText;
        super.showHelp();
    }

    public String getButtonHelpText() {
        return buttonHelpText;
    }

    public void setButtonHelpText(String buttonHelpText) {
        this.buttonHelpText = buttonHelpText;
    }

    public Button(String buttonHelpText) {
        this.buttonHelpText = buttonHelpText;
    }
}
