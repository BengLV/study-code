package designpatterns.行为模式.状态模式.states;

import designpatterns.行为模式.状态模式.ui.Player;

/**
 * 状态对象: 对象所有的可能状态, 所有状态对应的行为.
 * 使用场景: 1.如果对象需要根据自身当前状态进行不同行为， 同时状态的数量非常多且与状态相关的代码会频繁变更的话.
 *         2.如果某个类需要根据成员变量的当前值改变自身行为， 从而需要使用大量的条件语句时.
 *         3.当相似状态和基于条件的状态机转换中存在许多重复代码时
 *
 * 识别方法: 可通过受外部控制且能根据对象状态改变行为的方法来识别。
 */
public abstract class State {

    /**
     * 状态对象可存储对于上下文对象的反向引用。
     * 状态可以通过该引用从上下文处获取所需信息， 并且能触发状态转移。
     */
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
