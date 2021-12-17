package designpatterns.创建型模式.工厂方法模式.复杂版.buttons;

public class HtmlButton implements Button {


    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
