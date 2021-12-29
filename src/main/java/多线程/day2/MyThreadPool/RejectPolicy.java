package 多线程.day2.MyThreadPool;


//拒绝策略
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}
