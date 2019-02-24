package zh;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutDemo1 {
    public static void main(String[] args) throws IOException {
        //如果该文件不存在，则直接创建，如果存在删除后创建，之前内容清空
        FileOutputStream out = new FileOutputStream("demo/out.dat");
        //如果该文件不存在，则直接创建，如果存在，则追加
        //FileOutputStream out = new FileOutputStream("demo/out.dat",true);
        out.write('A');//写出了'A'的低八位
        out.write('B');//写出了'B'的低八位
        int a = 10;//write只能写8位，写一个整数需要写四次，每次8位
        out.write(a >>> 24);
        out.write(a >>> 16);
        out.write(a >>> 8);
        out.write(a);
        byte[] gbk = "中国".getBytes("gbk");
        out.write(gbk);
        out.close();

        IOUtil.printHex("demo/out.dat");


    }
}
