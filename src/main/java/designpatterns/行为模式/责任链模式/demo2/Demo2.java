package designpatterns.行为模式.责任链模式.demo2;


import designpatterns.行为模式.责任链模式.demo2.components.Button;
import designpatterns.行为模式.责任链模式.demo2.components.Dialog;
import designpatterns.行为模式.责任链模式.demo2.components.Panel;

/**
 * @description:
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public class Demo2 {

    public static void main(String[] args) {
        /**
         * 请求不断向上传递到该元素所有的容器， 直至某个元素能够显示帮助信息
         */
        Button button = new Button(null);
        Panel panel = new Panel("null");
        Dialog dialog = new Dialog(null);

        panel.addChild(button);
        dialog.addChild(panel);
        button.showHelp();
    }
}
