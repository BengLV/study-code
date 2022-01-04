package designpatterns.结构型模式.桥接模式.remotes;

/**
 * 所有远程控制器的通用接口
 */
public interface Remote {

    void power();

    void volumeDown();

    void volumeUp();

    void channelDown();

    void channelUp();
}
