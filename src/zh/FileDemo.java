package zh;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        //了解构造函数的情况，查看帮助
        File file = new File("D:\\下载\\coreJava\\src\\com\\imooc");
//        File file1 = new File("e:"+File.separator);//设置分隔符
//        System.out.println(file.exists());
        if(!file.exists())
            file.mkdir(); //file.mkdirs()，创建多级目录
        else
            file.delete();

        //是否是一个目录，如果是目录返回true，如果不是目录或不存在返回false
        System.out.println(file.isDirectory());
        //是否是一个文件
        System.out.println(file.isFile());

        File file2 = new File("D:\\下载\\coreJava\\src\\com\\imooc\\1.txt");
        //File file2 = new File("D:\\下载\\coreJava\\src\\com\\imooc","1.txt");
        if(!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            file2.delete();

        //常用的File对象的API
        System.out.println(file);//file.toString()的内容
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        System.out.println(file2.getName());
        System.out.println(file.getParent());
        System.out.println(file2.getParent());
        System.out.println(file.getParentFile().getAbsolutePath());
    }
}
