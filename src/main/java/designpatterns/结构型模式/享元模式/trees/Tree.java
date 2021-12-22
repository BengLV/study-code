package designpatterns.结构型模式.享元模式.trees;

import java.awt.*;

/**
 * 每棵树的特殊(外在的,可变的)状态
 */
public class Tree {
    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}