package designpatterns.结构型模式.组合模式.shapes;

import java.awt.*;

/**
 * 组件接口(简单声明组合中简单和复杂对象的通用操作)
 */
public interface Shape {

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void move(int x, int y);

    boolean isInsideBounds(int x, int y);

    void select();

    void unSelect();

    boolean isSelected();

    void paint(Graphics graphics);

}
