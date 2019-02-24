package zh;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FrAndFwDemo {
    public static void main(String[] args) throws IOException {
        //无法设置编码
        FileReader fr = new FileReader("D:\\下载\\coreJava\\src\\com\\imooc\\aa.txt");
        FileWriter fw = new FileWriter("D:\\下载\\coreJava\\src\\com\\imooc\\aa1.txt");
        //FileWriter fw = new FileWriter("D:\\下载\\coreJava\\src\\com\\imooc\\aa1.txt",true);//追加内容
        char[] buffer = new char[2056];
        int c ;
        while((c = fr.read(buffer,0,buffer.length))!=-1){
            fw.write(buffer,0,c);
            fw.flush();
        }
        fr.close();
        fw.close();
    }
}
