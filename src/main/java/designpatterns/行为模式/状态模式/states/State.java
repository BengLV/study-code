package designpatterns.行为模式.状态模式.states;

import designpatterns.行为模式.状态模式.ui.Player;

/**
 * https://refactoringguru.cn/design-patterns/state
 * 特征:状态能够传递
 */
public abstract class State {

    Player player;

    /**
     * Context passes itself through the state constructor. This may help a
     * state to fetch some useful context data if needed.
     */
    State(Player player) {
        this.player = player;
    }


    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();

}
