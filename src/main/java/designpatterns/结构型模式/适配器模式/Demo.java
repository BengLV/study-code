package designpatterns.结构型模式.适配器模式;

import designpatterns.结构型模式.适配器模式.adapters.SquarePegAdapter;
import designpatterns.结构型模式.适配器模式.round.RoundHole;
import designpatterns.结构型模式.适配器模式.round.RoundPeg;
import designpatterns.结构型模式.适配器模式.square.SquarePeg;

public class Demo {
    public static void main(String[] args) {
        // Round fits round, no surprise.
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("半径为5的圆钉适合半径为5的圆孔");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);

        //适配器
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("方钉长度为2的适合圆孔半径为5的");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("方钉长度为20的不适合圆孔半径为5的");
        }
    }
}
