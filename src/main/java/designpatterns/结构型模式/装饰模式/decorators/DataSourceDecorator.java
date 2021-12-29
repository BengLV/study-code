package designpatterns.结构型模式.装饰模式.decorators;

/**
 *  抽象基础装饰类(提高程序的扩展性)
 */
public class DataSourceDecorator implements DataSource {

    private DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        System.out.println(21321);
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
