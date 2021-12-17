package 多线程.day1;

public class Test6 {

    public static void main(String[] args) {
        Number t = new Number();
        new Thread(() -> {
            Number.a();}).start();
        new Thread(() -> {t.b();}).start();
    }


}

class Number{
    public static synchronized void a() {
        Sleeper.sleep(1000);
        System.out.println(1);
    }

    public synchronized void b() {
        System.out.println(2);
    }
}