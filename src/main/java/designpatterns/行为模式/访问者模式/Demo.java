package designpatterns.行为模式.访问者模式;

import designpatterns.行为模式.访问者模式.shapes.*;
import designpatterns.行为模式.访问者模式.visitor.XMLExportVisitor;

/**
 * 将形状导出为 XML 文件
 * 在本例中， 我们希望将一系列几何形状导出为 XML 文件。 重点在于我们不希望直接修改形状代码， 或者至少能确保最小程度的修改。
 *
 * 最终， 访问者模式建立了一个框架， 允许我们在不修改已有类的情况下向形状层次结构中添加新的行为。
 */
public class Demo {
    public static void main(String[] args) {
        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape c = new CompoundShape(5);
        c.add(dot);
        compoundShape.add(c);

        export(circle, compoundShape);
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }

}
