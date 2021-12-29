package designpatterns.结构型模式.组合模式;

import designpatterns.结构型模式.组合模式.editor.ImageEditor;
import designpatterns.结构型模式.组合模式.shapes.Circle;
import designpatterns.结构型模式.组合模式.shapes.CompoundShape;
import designpatterns.结构型模式.组合模式.shapes.Dot;
import designpatterns.结构型模式.组合模式.shapes.Rectangle;

import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}
