package 多线程.day2.MyThreadPool;

import lombok.extern.slf4j.Slf4j;
import 多线程.day1.Sleeper;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestPool {

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(1, 1000, TimeUnit.MILLISECONDS, 1, (queue, task) -> {
            //1.死等
            //queue.put(task);
            //2.带超时等待
            //queue.offer(task, 1500, TimeUnit.MILLISECONDS);
            //3.让调用者放弃任务执行
            //log.info("放弃{}", task);
            //4.让调用者抛出异常
            //throw new RuntimeException("任务执行失败" + task);
            //5.让调用者自己执行任务
            task.run();
        });
        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.execute(() -> {
                Sleeper.sleep(1000L);
              log.info("{}", j);
            });
        }
    }
    
}




