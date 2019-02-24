package zh;

import java.io.File;
import java.io.IOException;

public class IOUtilTest3 {
    public static void main(String[] args) {
        try {
            IOUtil.copyFile(new File("D:\\下载\\coreJava\\src\\com\\imooc\\new.txt"),
                    new File("D:\\下载\\coreJava\\src\\com\\imooc\\copy.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
