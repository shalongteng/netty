#0、视频url
    https://www.bilibili.com/video/BV1C4411D7TB?p=7
#1、java流式输入/输出原理
    像水流一样，从水桶通过一个管道 流向程序。
    各种不同的管道，就是不同的流。
#2、java流的分类
    1、节点流、处理流
        直接连接程序和文件的叫做 节点流
        套在其他 管道之上的 叫做处理流
    2、输入流、输出流
        站在程序的角度
    3、字符流、字节流
        一个字节8位。字符char 2个字节
#3、输入/输出流类    
    4个最基本的抽象类 
        字节流、输入流
            InputStream 
        字节流、输出流
            OutPutStream 
        字符流、输入流
            Reader
        字符流、输出流
            Writer
#4、常见的节点流
    InputStream
        read() 一次 读一个字节
        read(byte[] buffer) 
            先读到缓冲区，读满了，在做处理。
            p2p软件比较毁硬盘，就是读取硬盘 过于频繁。
        read(byte b[], int off, int len)
            读指定字节数，存到buffer中 去。
            
    OutPutStream
        write(int b)
        write(byte b[])
        write(byte b[], int off, int len)
        flush()
            在close之前，调用一下。
    
    Reader 字符流 char
        read()
        read(char cbuf[])
        read(char cbuf[], int off, int len)    
    
    Writer
        write(int c)
        write(char cbuf[])
        write(String str) 写一个字符串
        
    节点流类型：这个管道可以直接怼到水桶上。（file，内存中的数组，string，Pipe）
        file
            FileInputStream
            FileReader
#5、处理流 包在别的管道上的管道
    filteriing
        
    converting between bytes and char
    object serialzation
    data conversion
    counting
    peeking ahead
    printing
#6、缓冲流
    buffering 缓冲流 带缓冲区的，带小桶的。
         需要套接在相应的节点流上，对读写数据提供缓冲，提高读写效率。
         
         BufferedInputStream
         BufferedReader
#7、转换流
    OutputStreamWriter
        outputStream -> Writer
    InputStreamReader
        inputStream -> reader
#8、数据流
    DataInputStream 
        提供了存取基本类型的方法。
        readBoolean
        readInt
        readLong
    DataOutputStream
        之前将一个long类型写到文件，需要long转string转字节数组，然后写入。
        现在可以直接写了。
    ByteArrayInputStream
    ByteArrayOutputStream
        byteArray 字节数组 相当于一个字节数组被分配了，然后一个管道怼到这个字节数组上。
        
#9、Print流 
    只有输出流
    不会抛异常
    print流有自动的flush功能。
    
    PrintWriter
    PrintStream
    
#10、Object流 
    直接将Object写入或读出
    transient
    serialable
    Externalizable
        serialable子接口
        
        自己可以控制序列化过程。
        writeExternal(ObjectOutput out)
        readExternal(ObjectInput in)
    场景：
        画图程序画一个圆，一个方块。点击保存，下次打开图形还在原来的位置。
        
        方块是一个对象，会把方块的属性全部存下来。
            应该存下来方块的坐标，宽度，高度