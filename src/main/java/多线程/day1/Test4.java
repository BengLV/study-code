package 多线程.day1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test4 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.info("洗水壶");
            Sleeper.sleep(100);
            log.info("烧水壶");
            Sleeper.sleep(600);
        }, "老王");

        Thread t2 = new Thread(() -> {
            log.info("洗茶壶");
            Sleeper.sleep(100);
            log.info("洗茶杯");
            Sleeper.sleep(200);
            log.info("拿茶叶");
            Sleeper.sleep(300);
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("泡茶");
        });
        t1.start();
        t2.start();
    }
}
