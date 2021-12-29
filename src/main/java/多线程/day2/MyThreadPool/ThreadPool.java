package 多线程.day2.MyThreadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPool {

    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //核心线程数
    private int coreSize;

    //超时时间
    private long timeout;

    //时间单位
    private TimeUnit timeUnit;

    //拒绝策略
    private RejectPolicy rejectPolicy;

    //执行任务
    public void execute(Runnable task) {
        //当任务数没有超过coresize时,直接交给worker 对象执行
        //如果超过了,加入任务队列暂存
        synchronized (workers) {
            if(workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.info("新增 worker{}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                //taskQueue.put(task);
                //拒绝策略(策略模式)
                //1.死等
                //2.带超时等待
                //3.放弃任务执行
                //4.抛出异常
                //5.让调用者自己执行任务
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker( Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //执行任务
            //1.当task不为空时,执行任务
            //2.当task执行完毕,再从任务队列中获取任务并执行
            //注意,下方的take()是没有时间限制的等待
            //while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.info("正在执行... {}", task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                log.info("worker 被移除{}", this);
                workers.remove(this);
            }
        }
    }
}

