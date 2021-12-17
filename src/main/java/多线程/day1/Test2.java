package 多线程.day1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.info("run::", "通过runnable方式创建线程");
            }
        };
        Thread t = new Thread(r);
        t.setName("run");
        t.start();

        Runnable r2 = () -> log.info("通过lambda方式创建线程");
        Thread t2 = new Thread(r2);
        t2.setName("run");
        t2.start();

        FutureTask task = new FutureTask<>(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                log.info("通过callable方式创建线程");
                Thread.sleep(1000);
                return true;
            }
        });
        Thread t3 = new Thread(task, "t3");
        System.out.println(t3.isInterrupted());
        //获取状态信息
        System.out.println(t3.getState());
        t3.start();
        System.out.println(t3.getState());
        //获取结果
        System.out.println(task.get());

    }


}
