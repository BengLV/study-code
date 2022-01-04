package designpatterns.结构型模式.桥接模式;

import designpatterns.结构型模式.桥接模式.devices.Device;
import designpatterns.结构型模式.桥接模式.devices.Radio;
import designpatterns.结构型模式.桥接模式.devices.Tv;
import designpatterns.结构型模式.桥接模式.remotes.AdvancedRemote;
import designpatterns.结构型模式.桥接模式.remotes.BasicRemote;

public class Demo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}