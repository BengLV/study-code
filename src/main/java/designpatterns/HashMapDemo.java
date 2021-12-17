package designpatterns;

import java.util.*;

class HashMapDemo {
    public static void main(String args[]) throws Exception {
        HashMap m = new HashMap();
        Demo d = new Demo();

        // puts an entry into HashMap
        m.put(d, " Hi ");

        System.out.println(m);
        d = null;

        // garbage collector is called
        System.gc();

        //thread sleeps for 4 sec
        Thread.sleep(1000);

        System.out.println(m);
    }
}

class Demo {
    @Override
    public String toString() {
        return "demo";
    }

    // finalize method

}
