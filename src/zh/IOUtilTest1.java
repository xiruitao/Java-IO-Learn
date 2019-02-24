package zh;

import java.io.IOException;

public class IOUtilTest1 {
    public static void main(String[] args) {
        try {
            IOUtil.printHex("D:\\下载\\coreJava\\src\\com\\imooc\\io\\FileDemo.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
