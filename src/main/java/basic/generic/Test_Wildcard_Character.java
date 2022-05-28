package basic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 泛型通配符
 * @author: LuPeng
 * @create: 2022-04-28
 **/
public class Test_Wildcard_Character {

    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<>();
        test(dogList);
        //test1(dogList);
    }

    static void test(List<? extends Animal> animals) {
        System.out.println("test输出:");
        for (Animal animal : animals) {
            System.out.print(animal.toString() + "-");
        }
    }

    static void test1(List<Animal> animals) {
        System.out.println("test1输出:");
        for (Animal animal : animals) {
            System.out.print(animal.toString() + "-");
        }
    }

    /**
     *类A是类B的父类，G<A>和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    public void test3() {
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;
        //编译通过
//        print(list1);
//        print(list2);


        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(写入)：对于List<?>就不能向其内部添加数据。
        //除了添加null之外。
//        list.add("DD");
//        list.add('?');

        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }


}

class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}

class Dog extends Animal {
    @Override
    public String toString() {
        return "Dog";
    }

}
