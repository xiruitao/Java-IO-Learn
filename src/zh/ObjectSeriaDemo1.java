package zh;

import java.io.*;

public class ObjectSeriaDemo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = "demo/obj.dat";
        //1.对象的序列化
        /*ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file));
        Student stu = new Student("10001","张三",20);
        oos.writeObject(stu);
        oos.flush();
        oos.close();*/

        //反序列化
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file));
        Student stu = (Student)ois.readObject();
        //toString()是重写显示Student类属性的方法，如果不重写，直接System.out.println(stu)显示的将是地址值
        System.out.println(stu.toString());
        ois.close();
    }
}
