package zh;

import java.io.File;
import java.io.IOException;

public class IOUtilTest4 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
//            IOUtil.copyFileByBuffer(new File("D:\\下载\\coreJava\\src\\com\\imooc\\m.mp3"),
//                    new File("D:\\下载\\coreJava\\src\\com\\imooc\\n.mp3"));//13477

            IOUtil.copyFile(new File("D:\\下载\\coreJava\\src\\com\\imooc\\m.mp3"),
                    new File("D:\\下载\\coreJava\\src\\com\\imooc\\u.mp3"));//15
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
