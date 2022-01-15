package designpatterns.行为模式.模板方法模式.networks;

/**
 * 模板方法: 基于继承, 抽象类.
 * 使用场景:
 *    1. 只希望客户端扩展某个特定算法步骤， 而不是整个算法或其结构时
 *       说明: 模板方法将整个算法转换为一系列独立的步骤， 以便子类能对其进行扩展， 同时还可让超类中所定义的结构保持完整。
 *    2. 当多个类的算法除一些细微不同之外几乎完全一样时.但是,只要算法发生变化， 你就可能需要修改所有的类。
 *       说明: 在将算法转换为模板方法时， 你可将相似的实现步骤提取到超类中以去除重复代码。 子类间各不同的代码可继续保留在子类中。
 *
 *  识别方法: 基类中定义的 “默认” 行为。
 *
 * java核心库实例:
 *    1. java.io.InputStream、 java.io.OutputStream、 java.io.Reader 和 java.io.Writer 的所有非抽象方法。
 *    2. java.util.AbstractList、 java.util.AbstractSet 和 java.util.AbstractMap 的所有非抽象方法。
 */
public abstract class Network {
    String userName;
    String password;

    Network() {}

    /**
     * 模板方法(可以有多个), 定义了算法的框架,不允许被子类重写.
     */
    public boolean post(String message) {
        // Authenticate before posting. Every network uses a different
        // authentication method.
        if (logIn(this.userName, this.password)) {
            // Send the post data.
            boolean result =  sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    abstract boolean logIn(String userName, String password);
    abstract boolean sendData(byte[] data);
    abstract void logOut();
}
