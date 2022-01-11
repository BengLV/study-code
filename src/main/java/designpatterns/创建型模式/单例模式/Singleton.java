package designpatterns.创建型模式.单例模式;


public class Singleton {

    private volatile static Singleton singleton;

    public Singleton() {
    }

    /**
     * 双重锁校验 DCL
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {//类级别的锁
                if (singleton == null) {//避免多线程并发时的问题
                    return new Singleton();
                }
            }
        }
        return singleton;
    }

}
