package basic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 非泛型类中的泛型方法
 * @author: LuPeng
 * @create: 2022-04-25
 **/
public class Test_GenericMethod {

    public static void main(String[] args) {
        Test_GenericMethod test_genericMethod = new Test_GenericMethod();
        Generic01<Integer> generic01 = new Generic01<>(123);

        Generic01<String> generic02 = new Generic01<>("AAAAA");

        test_genericMethod.genericMethod_test01(generic01);
        test_genericMethod.genericMethod_test02(generic02, "我是T");

        test_genericMethod.Method01(generic01);
        print("123",753,123.12);


        Apple apple = new Apple();
        Person person = new Person();

        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        //apple是Fruit的子类，所以这里可以
        generateTest.show_1(apple);
        //编译器会报错，因为泛型类型实参指定的是Fruit，而传入的实参类是Person
        //generateTest.show_1(person);

        //使用这两个方法都可以成功
        generateTest.show_2(apple);
        generateTest.show_2(person);

        //使用这两个方法也都可以成功
        generateTest.show_3(apple);
        generateTest.show_3(person);
    }

    /**
     * 说明：
     * 1、public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2、只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3、<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4、<T> 后面的这个T，代表这个方法的返回值类型
     * 4、与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(T a) {

        return a;
    }


    /**
     * 1、这才是一个真正的泛型方法。
     * 2、首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T。
     * 3、这个T可以出现在这个泛型方法的任意位置.泛型的数量也可以为任意多个
     */
    public <T> T genericMethod_test01(Generic01<T> generic01) {
        System.out.println("我是genericMethod_test01：" + generic01.getKey());
        T test = generic01.getKey();
        return test;
    }

    public <T, V> T genericMethod_test02(Generic01<T> generic01, V value) {
        System.out.println("我是genericMethod_test02：" + generic01.getKey() + "==> value：" + value);

        T test = generic01.getKey();
        return test;
    }


    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
    public void Method01(Generic01<? extends Number> generic01) {
        System.out.println(generic01.getKey());
    }


    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
    public void Method02(Generic01<?> generic01) {
        System.out.println(generic01.getKey());
    }

    /**
     * 这个方法是有问题的，编译器会为我们提示错误信息："UnKnown class 'E' "
     * 虽然我们声明了<T>,也表明了这是一个可以处理泛型的类型的泛型方法。
     * 但是只声明了泛型类型T，并未声明泛型类型E，因此编译器并不知道该如何处理E这个类型。
     */
//    public <T> T showKeyName(Generic01<E> generic01, T t) {
//        return t;
//    }

    public static <T> void print(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }


}


class Generic01<T> {
    private T key;

    public Generic01(T key) {
        this.key = key;
    }


    /**
     * 不是一个泛型方法。这只是类中一个普通的成员方法
     *
     * 只不过他的返回值是在声明泛型类已经声明过的泛型。所以在这个方法中才
     * 可以继续使用 T 这个泛型。
     */
    public T getKey() {
        return key;
    }

    /**
     * 1、这个方法显然是有问题的，在编译器会给我们提示这样的错误信息"cannot reslove symbol E"
     * 因为在类的声明中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
     */
//    public E setKey(E key) {
//        this.key = key;
//    }

    /**
     * 1、如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 2、泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。换句话说，
     * 泛型方法所属的类是不是泛型类都没有关系。
     * 3、泛型方法，可以声明为静态的。原因：泛型参数是在调用方法时确定的。并非在初始化类时确定,所以无所谓
     */
    public static <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();

        for(E e : arr){
            list.add(e);
        }
        return list;

    }

    //静态方法中不能使用类的泛型。
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

}


class Fruit {
    @Override
    public String toString() {
        return "fruit";
    }
}

class Apple extends Fruit {
    @Override
    public String toString() {
        return "apple";
    }
}

class Person {
    @Override
    public String toString() {
        return "Person";
    }
}

class GenerateTest<T> {

    /**
     * 非泛型方法, 参数类型需要泛型类中一致
     */
    public void show_1(T t) {
        System.out.println(t.toString());
    }


    /**
     * 1、在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
     * 2、由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别
     * 泛型方法中识别的泛型。
     */
    public <E> void show_3(E t) {
        System.out.println(t.toString());
    }


    /**
     * 1、在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T
     * 不是同一种类型。也就是说main函数中使用的时候也可以是不一样的泛型类型
     */
    public <T> void show_2(T t) {
        System.out.println(t.toString());
    }

}
