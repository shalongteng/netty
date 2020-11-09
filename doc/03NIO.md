#0、资料地址
    1、博客地址：
        http://ifeve.com/overview/
#1、Java NIO 由以下几个核心部分组成：
    Channels
    Buffers
    Selectors
#2、Channel 
    几乎所有的IO 在NIO 中都从一个Channel 开始。
    数据可以从Channel读到Buffer中，也可以从Buffer 写到Channel中。
    
    Java NIO的通道类似流，但又有些不同：
        既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
        通道可以异步地读写。
        通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。
        
    JAVA NIO中的一些主要Channel的实现：
        FileChannel  
            从文件中读写数据。
        DatagramChannel
            能通过UDP读写网络中的数据。
        SocketChannel 
            能通过TCP读写网络中的数据。
        ServerSocketChannel
            监听新进来的TCP连接，像Web服务器那样。对每一个连接都会创建一个SocketChannel。
        
#3、Buffer 缓冲区
    Java NIO中的Buffer用于和NIO通道进行交互。数据是从通道读入缓冲区，从缓冲区写入到通道中的。
    
    Java NIO里关键的Buffer实现：
        ByteBuffer
        CharBuffer
        DoubleBuffer
        FloatBuffer
        IntBuffer
        LongBuffer
        ShortBuffer
    
    使用Buffer读写数据一般遵循以下四个步骤：
        写入数据到Buffer
        调用flip()方法
        从Buffer中读取数据
        调用clear()方法或者compact()方法
        
    Buffer的capacity,position和limit
        capacity
            容量（单位byte，char，short...)
        position
            表示当前的位置
            当将Buffer从写模式切换到读模式，position会被重置为0
        limit
            在写模式下，Buffer的limit表示你最多能往Buffer里写多少数据。 写模式下，limit等于Buffer的capacity。
            在读模式下：
                 limit表示你最多能读到多少数据，limit会被设置成写模式下的position值。
#3、Selector 选择器
    Selector能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
    一个单独的线程可以管理多个channel，从而管理多个网络连接。
    
    将Channel注册到Selector中，才能使用Selector。select方法会阻塞直到某个通道就绪。
    
    单个线程来处理多个Channels的好处是，
        只需要更少的线程来处理通道。可以只用一个线程处理所有的通道。减少线程上下文切换带来的开销
        每个线程都要占用系统的一些资源（如内存）。因此，使用的线程越少越好。
#3、Channel

#3、Selector
