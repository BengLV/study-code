package 多线程.day1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {

    public static void main(String[] args) {
        Thread t = new Thread(() -> log.info("run::"));
        t.setName("线程1");
        t.start();
        log.info("running");
    }
}
