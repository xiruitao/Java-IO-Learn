package zh;

import java.io.*;

public class IsrAndOswDemo {
    public static void main(String[] args) throws Exception{
        FileInputStream in = new FileInputStream("D:\\下载\\coreJava\\src\\com\\imooc\\aa.txt");
        InputStreamReader isr = new InputStreamReader(in,"gbk");//不填则为默认项目的编码

        FileOutputStream out = new FileOutputStream("D:\\下载\\coreJava\\src\\com\\imooc\\2.txt");
        OutputStreamWriter osw = new OutputStreamWriter(out,"gbk");
		/*int c ;
		while((c = isr.read())!=-1){
			System.out.print((char)c);
		}*/
        char[] buffer = new char[8*1024];
        int c;
        //批量读取，放入buffer这个字符数组，从第0个位置开始放置，最多放buffer.length
        while(( c = isr.read(buffer,0,buffer.length))!=-1){
            String s = new String(buffer,0,c);
            System.out.print(s);
            osw.write(buffer,0,c);
            osw.flush();
        }
        isr.close();
        osw.close();

    }
}
