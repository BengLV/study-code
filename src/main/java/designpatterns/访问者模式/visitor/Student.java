package designpatterns.访问者模式.visitor;

import java.io.*;

public class Student implements Serializable {

    private String name;

    private Integer height;

    //性别不进行序列化
    private transient String sex;

    public Student(String name, Integer height, String sex) {
        this.name = name;
        this.height = height;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", sex='" + sex + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
             ObjectInputStream ios = new ObjectInputStream(new FileInputStream("student.txt"))) {
            Student student = new Student("序列化", 180, "男");
            System.out.println(student);
            oos.writeObject(student);
            Student student1 = (Student) ios.readObject();
            System.out.println(student1);
        }
    }

//    Student{name='序列化', height=180, sex='男'}
//    Student{name='序列化', height=180, sex='null'}
}