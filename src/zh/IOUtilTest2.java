package zh;

import java.io.IOException;

public class IOUtilTest2 {
    public static void main(String[] args) {
        try {
            IOUtil.printHexByByteArray("D:\\下载\\coreJava\\src\\com\\imooc\\io\\FileDemo.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
