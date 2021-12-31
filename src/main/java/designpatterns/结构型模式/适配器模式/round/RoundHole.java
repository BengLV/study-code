package designpatterns.结构型模式.适配器模式.round;

/**
 * 圆孔
 */
public class RoundHole {

    //半径
    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        boolean result;
        result = (this.getRadius() >= peg.getRadius());
        return result;
    }
}
