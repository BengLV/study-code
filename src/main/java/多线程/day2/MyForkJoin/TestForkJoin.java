package 多线程.day2.MyForkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestForkJoin {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);//线程数
        System.out.println(pool.invoke(new MyTask(5)));
    }
}

@Slf4j
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + '}';
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            log.info("join {}", n);
            return 1;
        }

        //将任务进行拆分(fork)
        MyTask task = new MyTask(n - 1);
        task.fork();
        log.info("fork() {} + {}", n, task);

        //合并(join)结果
        int result = n + task.join();
        log.info("join() {} + {} = {}", n, task, result);
        return result;
    }

}