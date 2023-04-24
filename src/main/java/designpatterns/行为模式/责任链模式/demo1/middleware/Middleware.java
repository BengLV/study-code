package designpatterns.行为模式.责任链模式.demo1.middleware;

/**
 * @description: 基础验证接口,简单的组件基础类
 * @author: LuPeng
 * @create: 2022-08-31
 **/

/**
 * 1.当程序需要使用不同方式处理不同种类请求， 而且请求类型和顺序预先未知时， 可以使用责任链模式。
 *   该模式能将多个处理者连接成一条链。 接收到请求后， 它会 “询问” 每个处理者是否能够对其进行处理。 这样所有处理者都有机会来处理请求。
 * 2.当必须按顺序执行多个处理者时， 可以使用该模式。
 *   无论你以何种顺序将处理者连接成一条链， 所有请求都会严格按照顺序通过链上的处理者。
 * 3.如果所需处理者及其顺序必须在运行时进行改变， 可以使用责任链模式。
 *   如果在处理者类中有对引用成员变量的设定方法， 你将能动态地插入和移除处理者， 或者改变其顺序。
 */
public abstract class Middleware {


    /**
     * 组件基础类在处理者链中作为“下一个”链接。
     */
    private Middleware next;

    /**
     * 构建中间件对象链。
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * 子类将通过具体检查来实现此方法。
     */
    public abstract boolean check(String email, String password);

    /**
     * 对链中的下一个对象运行检查，如果我们在链中的最后一个对象中，则结束遍历。
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
