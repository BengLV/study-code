package designpatterns.行为模式.状态模式;

import designpatterns.行为模式.状态模式.ui.Player;
import designpatterns.行为模式.状态模式.ui.UI;

public class Demo {

    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }

}
