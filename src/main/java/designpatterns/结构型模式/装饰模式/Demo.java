package designpatterns.结构型模式.装饰模式;

import designpatterns.结构型模式.装饰模式.decorators.*;

public class Demo {
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("C:\\Users\\Administrator\\Desktop\\test.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("C:\\Users\\Administrator\\Desktop\\te.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
