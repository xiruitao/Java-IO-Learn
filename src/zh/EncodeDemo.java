package zh;

import java.io.UnsupportedEncodingException;

public class EncodeDemo {
    public static void main(String[] args) throws Exception{
        String s = "慕课ABC";
        byte[] bytes1 = s.getBytes();//转换成字节序列，使用的是项目默认的编码UTF-8
        //utf-8编码中文占用3个字节，英文占用1个字节
        for (byte b:bytes1){
            //把字节（转换成了int）以16进制的方式显示
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }

        System.out.println();

        byte[] bytes2 = s.getBytes("gbk");
        //gbk编码中文占用2个字节，英文占用1个字节
        for (byte b:bytes2){
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }

        System.out.println();

        //java是双字节编码 utf-16be
        byte[] bytes3 = s.getBytes("utf-16be");
        //utf-16be编码中文占用2个字节，英文也占用2个字节
        for (byte b:bytes3){
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }

        System.out.println();

        /**
         * 当你的字节序列是某种编码时，这个时候想把字节序列变成字符串
         * 也需要使用这种编码方式，否则会出现乱码
         */
        String str1 = new String(bytes3);//用项目默认的编码
        System.out.println(str1);

        String str2 = new String(bytes3,"utf-16be");
        System.out.println(str2);

        /**
         * 文本文件，就是字节序列
         * 可以是任意编码的字节序列
         * 如果我们在中文机器上直接创建文本文件，那么该文本文件只认识ansi编码
         * 联通、联这是一种巧合，他们正好符合了utf-8编码的规则
         */
    }
}
