#### 文件编码

项目中，将一个对象转换成字节序列，使用的是项目默认的编码

java使用双字节编码 utf-16be，utf-16be编码中文占用2个字节，英文也占用2个字节

当你的字节序列是某种编码时，这个时候想把字节序列变成字符串，也需要使用这种编码方式，否则会出现乱码

utf-8编码中文占用3个字节，英文占用1个字节

gbk编码中文占用2个字节，英文占用1个字节

文本文件，就是字节序列，可以是任意编码的字节序列。如果我们在中文机器上直接创建文本文件，那么该文本文件只认识ansi编码，联通、联这是一种巧合，他们正好符合了utf-8编码的规则。(在电脑上创建联.txt，联通.txt，联想.txt，再次打开，联、联通会显示乱码，联想正常显示)

将一个文本文件从一个项目复制或移动到另一个项目，如果两个项目的编码不一致，则会出现乱码。将项目中的文件复制到电脑桌面，则能正常显示，由于编码不同，占用字节不同。直接复制文本到另一个项目，会自动转换编码，不会出现乱码。



#### `java.io.File`类

java.io.File类用于表示文件（目录）

File类只用于表示文件（目录）的信息（名称、大小等），不能用于文件内容的访问

**创建File对象**

File file = new File(`pathname:`" ");

**File对象常用API**

boolean exists() 判断文件(文件和文件夹)是否存在

boolean isDriectory() 判断是否是一个文件夹(目录)

boolean isFile()判断是否是一个文件

String getName() 获取文件的名称

String getParentFile() 如果没有父目录返回null

boolean mkdir() 创建目录

boolean mkdirs() 创建多级目录

boolean createNewFile() 创建文件，如果存在指定目录就返回true否则返回false

**File类的使用**

```java
//dir为File对象

String[] filenames = dir.list();//返回字符串数组，直接子的名称，不包含子目录下的内容
for (String filename : filenames) {
    System.out.println(dir+"\\"+filename);
}

//如果要遍历子目录下的内容，就需要构造成File对象做递归操作，File提供了直接返回File对象的API
File[] files = dir.listFiles();//返回的是直接子目录（文件）的抽象
if (files!=null&&files.length>0){
    for (File file : files) {
        if (file.isDirectory()){
            listDirectory(file);
        }else {
            System.out.println(file);
        }
    }
}
```



#### RandomAccessFile

`RandomAccessFile`——Java提供的对文件内容的访问，既可以读文件，也可以写文件。

`RandomAccessFile`支持随机访问文件，可以访问文件的任意位置

##### 序列化与基本类型序列化

1）将类型int 转换成4 byte或将其他数据类型转换成byte的过程叫**序列化**

数据---->n byte

2)**反序列化**

将n个byte 转换成一个数据的过程

 n byte ---> 数据

3)`RandomAccessFile`提供基本类型的读写方法，可以将基本类型数据序列化到文件或者将文件内容反序列化为数据

**(1)Java文件模型**

  在硬盘上的文件是byte byte byte存储的,是数据的集合

**(2)打开文件**

有两种模式"rw"(读写)  "r"（只读)

```java
RandomAccessFile raf = new RandomeAccessFile(file,"rw")
```

包含文件指针，打开文件时指针在开头 pointer = 0;

**(3) 写方法**

raf.write(int)--->只写一个字节（后8位),同时指针指向下一个位置，准备再次写入，例：

```java
int i = 0x7fffffff;
//用write方法每次只能写一个字节，如果要把i写进去就得写4次
raf.write(i >>> 24);//高8位
raf.write(i >>> 16);
raf.write(i >>> 8);
raf.write(i);
```

也可以直接写一个int

```java
raf.writeInt(i);
```

**(4)读方法**

int b = raf.read()--->读一个字节

```java
//读文件，必须把指针移到头部
raf.seek(0);
//一次性读取，把文件中的内容都读到字节数组中
byte[] buf = new byte[(int)raf.length()];
raf.read(buf);
```

**(5)文件读写完成以后一定要关闭（Oracle官方说明）**

```java
raf.close();
```



####  IO流(输入流、输出流)

 **字节流**    **字符流**

#####  字节流

 1)InputStream、OutputStream

InputStream抽象了应用程序读取数据的方式

OutputStream抽象了应用程序写出数据的方式 

2)EOF = End   读到-1就读到结尾

3)输入流基本方法
   int  b = in.read();读取一个字节无符号填充到int低八位.-1是 EOF
   in.read(byte[] buf) ;读取数据填充到字节数组buf
   in.read(byte[] buf,int start,int size);读取数据填充到字节数组buf，从buf的start位置开始存放到size长度的数据

```java
//读取指定文件内容，按16进制输出到控制台
public static void printHexByByteArray(String fileName)throws IOException{
    FileInputStream in = new FileInputStream(fileName);
    byte[] buf = new byte[8 * 1024];
    /*从in中批量读取字节，放入buf这个字节数组中，
    * 从第0个位置开始放，最多放buf.length个，
    * 返回的是读到的字节的个数
    */
    int bytes = 0;
    int j = 1;
    while((bytes = in.read(buf,0,buf.length))!=-1){
        for(int i = 0 ; i < bytes;i++){
            //byte8位，int类型32位，为了避免数据转换错误，通过&0xff将高24位清零
            System.out.print(Integer.toHexString(buf[i] & 0xff)+"  ");
            if(j++%10==0){
                System.out.println();
            }
        }
    }
    in.close();
}
```



4)输出流基本方法
  out.write(int b)  写出一个byte到流，b的低8位
  out.write(byte[] buf)将buf字节数组都写入到流
  out.write(byte[] buf,int start,int size)

 5)FileInputStream--->具体实现了在文件上读取数据

 6)FileOutputStream 实现了向文件中写出byte数据的方法

```java
//如果该文件不存在，则直接创建，如果存在删除后创建，之前内容清空
FileOutputStream out = new FileOutputStream("demo/out.dat");
//如果该文件不存在，则直接创建，如果存在，则追加
FileOutputStream out = new FileOutputStream("demo/out.dat",true);
```





```java
/**
 *批量字节读取拷贝文件，速度最快
 */
public static void copyFile(File srcFile, File destFile)throws IOException{
    if(!srcFile.exists()){
        throw new IllegalArgumentException("文件:"+srcFile+"不存在");
    }
    if(!srcFile.isFile()){
        throw new IllegalArgumentException(srcFile+"不是文件");
    }
    FileInputStream in = new FileInputStream(srcFile);
    FileOutputStream out = new FileOutputStream(destFile);
    byte[] buf = new byte[8*1024];
    int b ;
    while((b = in.read(buf,0,buf.length))!=-1){
        out.write(buf,0,b);
        out.flush();//最好加上
    }
    in.close();
    out.close();
}
```



 7)DataOutputStream/DataInputStream

 对"流"功能的扩展，可以更加方面的读取int、long、字符等类型数据
   DataOutputStream多的方法：writeInt()/writeDouble()/writeUTF()

```java
DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
dos.writeInt(10);
dos.writeInt(-10);
dos.writeLong(10l);
dos.writeDouble(10.5);
//采用utf-8编码写出
dos.writeUTF("中国");
//以utf-16be写出
dos.writeChars("中国");
dos.close();

DataInputStream dis = new DataInputStream(new FileInputStream(file));
int i = dis.readInt();
System.out.println(i);//10
i = dis.readInt();
System.out.println(i);//-10
long l = dis.readLong();
System.out.println(l);//10
double d = dis.readDouble();
System.out.println(d);//10.5
String s = dis.readUTF();
System.out.println(s);//中国
dis.close();
```

 8)BufferedInputStream&BufferedOutputStream

 这两个流类位IO提供了带缓冲区的操作，一般打开文件进行写入或读取操作时，都会加上缓冲，这种流模式提高了IO的性能。

从应用程序中把输入放入文件，相当于将一缸水倒入到另一个缸中:

 FileOutputStream--->write()方法相当于一滴一滴地把水“转移”过去

 DataOutputStream-->writeXxx()方法会方便一些，相当于一瓢一瓢把水“转移”过去

 BufferedOutputStream--->write方法更方便，相当于一飘一瓢先放入桶中，再从桶中倒入到另一个缸中，性能提高了

```java
/**
* 进行文件的拷贝，利用带缓冲的字节流
*/
public static void copyFileByBuffer(File srcFile,File destFile)throws IOException{
        if(!srcFile.exists()){
            throw new IllegalArgumentException("文件:"+srcFile+"不存在");
        }
        if(!srcFile.isFile()){
            throw new IllegalArgumentException(srcFile+"不是文件");
        }
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));
        int c ;
        while((c = bis.read())!=-1){
            bos.write(c);
            bos.flush();//刷新缓冲区
        }
        bis.close();
        bos.close();
    }
```



##### 字符流

 1) 编码问题

 2)认识文本和文本文件

 java的文本(char)是16位无符号整数，是字符的unicode编码（双字节编码)

 文件是byte byte byte ...的数据序列

文本文件是文本(char)序列按照某种编码方案(utf-8,utf-16be,gbk)序列化为byte的存储结果

3)字符流(Reader Writer)---->操作的是文本文本文件

字符的处理，一次处理一个字符

字符的底层任然是基本的字节序列

字符流的基本实现

InputStreamReader   完成byte流解析为char流,按照编码解析

OutputStreamWriter  提供char流到byte流，按照编码处理

```java
	/**
	*读取并输出aa.txt，并将aa.txt中的内容复制到1.txt(没有则会新建)
	*/
	public static void main(String[] args) throws Exception{
        FileInputStream in = new FileInputStream("D:\\下载\\coreJava\\src\\com\\imooc\\aa.txt");
        InputStreamReader isr = new InputStreamReader(in,"gbk");//不填则为默认项目的编码

        FileOutputStream out = new FileOutputStream("D:\\下载\\coreJava\\src\\com\\imooc\\1.txt");
        OutputStreamWriter osw = new OutputStreamWriter(out,"gbk");
		
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
```



   FileReader/FileWriter

```java
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
```



 字符流的过滤器

BufferedReader   ---->readLine 一次读一行

 BufferedWriter/PrintWriter   ---->写一行    

```java
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
```



##### 对象的序列化，反序列化

1)对象序列化，就是将Object转换成byte序列，反之叫对象的反序列化 

2)序列化流(ObjectOutputStream),是(字节的)过滤流----writeObject

 反序列化流(ObjectInputStream)----readObject

```java
//对象的序列化
ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("demo/obj.dat"));
Student stu = new Student("10001","张三",20);
oos.writeObject(stu);
oos.flush();
oos.close();

 //反序列化
ObjectInputStream ois = new ObjectInputStream(
    	new FileInputStream(file));
Student stu = (Student)ois.readObject();
System.out.println(stu.toString());
ois.close();
```

3)序列化接口(Serializable)

对象必须实现序列化接口 ，才能进行序列化，否则将出现异常

这个接口，没有任何方法，只是一个标准

4) transient关键字，有些时候可以提高程序性能

​    private void writeObject(java.io.ObjectOutputStream s)
​		        throws java.io.IOException
​	private void readObject(java.io.ObjectInputStream s)
​		        throws java.io.IOException, ClassNotFoundException

   分析ArrayList源码中序列化和反序列化的问题

```java
import java.io.Serializable;

public class Student implements Serializable {
    private String stuno;
    private String stuname;
    //使用了关键字transient，该元素不会进行jvm默认的序列化
    //可以自己完成这个元素的序列化
    private transient int stuage;
    
    public Student(){}
    public Student(String stuno, String stuname, int stuage) {
        super();
        this.stuno = stuno;
        this.stuname = stuname;
        this.stuage = stuage;
    }
    
    public String getStuno() {
        return stuno;
    }
    public void setStuno(String stuno) {
        this.stuno = stuno;
    }
    public String getStuname() {
        return stuname;
    }
    public void setStuname(String stuname) {
        this.stuname = stuname;
    }
    public int getStuage() {
        return stuage;
    }
    public void setStuage(int stuage) {
        this.stuage = stuage;
    }

    //toString()是重写显示Student类属性的方法
    // 如果不重写，直接System.out.println(stu)显示的将是地址值
    @Override
    public String toString() {
        return "Student{" +
                "stuno='" + stuno + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                '}';
    }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException{
        s.defaultWriteObject();//把jvm能默认序列化的元素进行序列化操作
        s.writeInt(stuage);//自己完成stuage的序列化
    }

    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException{
        s.defaultReadObject();//把jvm能默认反序列化的元素进行反序列化操作
        this.stuage = s.readInt();//自己完成stuage的反序列化操作
    }
}

```



5)序列化中 子类和父类构造函数的调用问题

如果一个类实现了序列化接口，那么其子类都可以进行序列化

对子类对象进行反序列化操作时，如果其父类没有实现序列化接口,那么其父类的构造函数会被调用