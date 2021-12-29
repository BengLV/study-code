package 多线程.day2.MyThreadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockingQueue 其实就是阻塞队列，是基于阻塞机制实现的线程安全的队列。
 * 通过在入队和出队时进行加锁，保证了队列线程安全 支持阻塞的入队和出队方法：
 * 当队列满时，会阻塞入队的线程，直到队列不满；当队列为空时，会阻塞出队的线程，直到队列中有元素。
 */
@Slf4j
public class BlockingQueue<T> {

    //任务队列(双向对列),允许两头都进，两头都出
    private Deque<T> queue = new ArrayDeque<>();

    //锁
    private ReentrantLock lock = new ReentrantLock();

    //生产者条件变量
    private Condition fullWaitSet = lock.newCondition();

    //消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //容量
    private int capcity;

    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //带超时的阻塞获取
    public T poll(long timeOut, TimeUnit unit) {
        lock.lock();
        try {
            //转换成纳秒值
            long nanos = unit.toNanos(timeOut);
            while (queue.isEmpty()) {
                try {
                    //此处存在虚假唤醒的问题,比如需要等两秒钟,但是在第一秒的时候就唤醒了,这个时候发现queue还是空的,又需要等一秒
                    //就会存在多等1秒的情况.
                    if (nanos <= 0) {
                        return null;
                    }
                    //返回的是剩余时间
                    nanos  = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }  finally {
            lock.unlock();
        }
    }

    //阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        }  finally {
            lock.unlock();
        }
    }

    //阻塞添加
    public void put(T task) {
        lock.lock();
        try {
            while (queue.size() == capcity) {
                try {
                    log.info("等待加入任务队列 {}", task);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //带超时时间的阻塞添加
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity) {
                try {
                    log.info("等待加入任务队列 {}.....", task);
                    if(nanos <= 0) {
                        return false;
                    }
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("加入任务队列 {}", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy rejectPolicy, T task) {
        lock.lock();
        try {
            //判断队列是否已满
            if (queue.size() == capcity) {
                rejectPolicy.reject(this, task);
            } else {
                log.info("加入任务队列 {}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
