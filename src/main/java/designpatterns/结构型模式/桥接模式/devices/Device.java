package designpatterns.结构型模式.桥接模式.devices;

/**
 * 所有设备的通用接口
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}
