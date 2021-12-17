package 多线程.day1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination two = new TwoPhaseTermination();
        two.start();
        Thread.sleep(4500);
        System.out.println(Thread.currentThread().isInterrupted());
        two.stop();
    }
}

@Slf4j
class TwoPhaseTermination {

    private Thread monitor;

    //启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.info("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);//情况1
                    log.info("执行监控记录");//情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //重新设置打断标记
                    current.interrupt();
                }
            }
        }, "t2");
        monitor.start();
    }
    //停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}