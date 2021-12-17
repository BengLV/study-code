package designpatterns.访问者模式.shapes;

import designpatterns.访问者模式.visitor.Visitor;

public interface Shape {

    void move(int x, int y);

    void draw();

    String accept(Visitor visitor);
}