package designpatterns.结构型模式.适配器模式.adapters;

import designpatterns.结构型模式.适配器模式.round.RoundPeg;
import designpatterns.结构型模式.适配器模式.square.SquarePeg;

/**
 * 方钉到圆孔的适配器(中间类)
 *
 * 可以同时与客户端和服务交互的类： 它在实现客户端接口的同时封装了服务对象。
 * 适配器接受客户端通过适配器接口发起的调用， 并将其转换为适用于被封装服务对象的调用。
 */
public class SquarePegAdapter extends RoundPeg {

    //用于保存对于服务对象的引用
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        // Calculate a minimum circle radius, which can fit this peg.
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }
}