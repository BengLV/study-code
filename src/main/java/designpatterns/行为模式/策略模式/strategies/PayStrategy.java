package designpatterns.行为模式.策略模式.strategies;

/**
 * 策略接口: 声明各个不同版本之间的公有操作.将一组行为转换为对象，并使其在原始上下文对象内部能够相互替换
 * 使用场景:
 *    1.想使用对象中各种不同的算法变体， 并希望能在运行时切换算法时
 *    2.有许多仅在执行某些行为时略有不同的相似类时
 *    3.如果算法在上下文的逻辑中不是特别重要，能将类的业务逻辑与其算法实现细节隔离开来
 *    4.当类中使用了复杂条件运算符以在同一算法的不同变体中切换时
 *    5.支付场景, 增扣库存等.
 *
 * java核心类库实例:
 *    1.对 java.util.Comparator#compare() 的调用来自 Collections#sort().
 *    2.javax.servlet.http.HttpServlet：  service()方法， 还有所有接受 HttpServletRequest和 HttpServletResponse对象作为参数的 doXXX()方法。
 *    3.javax.servlet.Filter#doFilter()
 *
 * 识别方法:可以通过允许嵌套对象完成实际工作的方法以及允许将该对象替换为不同对象的设置器来识别。
 */
public interface PayStrategy {

    boolean pay(int paymentAmount);

    void collectPaymentDetails();
}
