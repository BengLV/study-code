package designpatterns.行为模式.访问者模式.visitor;

import designpatterns.行为模式.访问者模式.shapes.Circle;
import designpatterns.行为模式.访问者模式.shapes.CompoundShape;
import designpatterns.行为模式.访问者模式.shapes.Dot;
import designpatterns.行为模式.访问者模式.shapes.Rectangle;

/**
 *  访问者模式基于双分派的原则创建， 但这并不是其主要目的。
 *  访问者的目的是让你能为整个类层次结构添加 “外部” 操作， 而无需修改这些类的已有代码。
 *
 *  适用场景:
 *  1. 需要对一个复杂对象结构 （例如对象树） 中的所有元素执行某些操作.
 *  2. 清理辅助行为的业务逻辑(将所有非主要的行为抽取到一组访问者类中， 使得程序的主要类能更专注于主要的工作)
 *  3. 某个行为仅在类层次结构中的一些类中有意义， 而在其他类中没有意义
 *
 *  缺点: 访问者不是常用的设计模式， 因为它不仅复杂， 应用范围也比较狭窄。
 */
public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}