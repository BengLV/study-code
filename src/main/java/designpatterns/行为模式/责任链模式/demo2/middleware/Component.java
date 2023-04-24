package designpatterns.行为模式.责任链模式.demo2.middleware;

/**
 * @description: 简单组件的基础类。
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public abstract class Component implements ComponentWithContextualHelp{

    protected String toolTipText;

    protected Container container;

    @Override
    public void showHelp() {
        //如果组件设定了帮助文字，那它将会显示提示信息
        if (toolTipText != null) {
            System.out.println(toolTipText);
        } else {
            //如果组件没有帮助文字, 且其容器存在，那它会将调用传递给容器。
            if (container == null) {
                System.out.println("没有合适的提示!");
            } else {
                container.showHelp();
            }
        }
    }
}
