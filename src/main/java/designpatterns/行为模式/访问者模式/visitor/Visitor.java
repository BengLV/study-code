package designpatterns.行为模式.访问者模式.visitor;

import designpatterns.行为模式.访问者模式.shapes.Circle;
import designpatterns.行为模式.访问者模式.shapes.CompoundShape;
import designpatterns.行为模式.访问者模式.shapes.Dot;
import designpatterns.行为模式.访问者模式.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}