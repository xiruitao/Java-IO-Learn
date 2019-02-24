package zh;

import java.io.*;

public class BrAndBwOrPwDemo {
    public static void main(String[] args) throws IOException {
        //对文件进行读写操作
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("D:\\下载\\coreJava\\src\\com\\imooc\\aa.txt"),"gbk"));
//        BufferedWriter bw = new BufferedWriter(
//                new OutputStreamWriter(
//                        new FileOutputStream("D:\\下载\\coreJava\\src\\com\\imooc\\aa2.txt")));

        PrintWriter pw = new PrintWriter("D:\\下载\\coreJava\\src\\com\\imooc\\aa3.txt");
//        PrintWriter pw1 = new PrintWriter(outputStream,boolean autoFlush);自动刷新缓冲区，后面就不用flush了

        String line;
        while ((line = br.readLine())!=null){
            System.out.println(line);//一次读一行，并不能识别换行，需使用println
//            bw.write(line);
//            //需单独写出换行操作
//            bw.newLine();
//            bw.flush();
            pw.println(line);
        }
        br.close();
//        bw.close();
        pw.close();
    }
}
