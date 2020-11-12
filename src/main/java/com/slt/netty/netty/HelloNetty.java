package com.slt.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * sync方法保证上一个方法变成同步方法。
 */
public class HelloNetty {
    public static void main(String[] args) {
        new NettyServer(8888).serverStart();
    }
}

class NettyServer {
    int port = 8888;
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public NettyServer(int port) {
        this.port = port;
    }

    public void serverStart() {
        //这两个group 可以理解成两个线程池
        //bossGroup 相当于selector 负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责连接以后的io处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //对server的配置
        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup, workerGroup)
                //通道的类型
                .channel(NioServerSocketChannel.class)
                //当每一个客户端连上来以后给它一个监听器 都看作是自己的一个孩子
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    //此时已经连接上了
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("ch: "+ch);
                        //监听器处理过程 就是 加一个对通道的处理器
                        //pipeline管道上加自己的处理器，类似责任链
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new Handler());
                    }
                });

        try {
            //这个sync是保证bing成功以后，在往下执行
            ChannelFuture f = b.bind(port).sync();
            System.out.println("server start");

            //如果没有人调用close方法closeFuture会一直等待。如果调用了close才会继续往下执行。
            f.channel().closeFuture().sync();
            System.out.println("3333");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}

class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyServer.channelGroup.add(ctx.channel());
    }
    /**
     * msg 数据已经读进来了
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);

        //实现群发
        NettyServer.channelGroup.writeAndFlush(msg);


//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println("server: channel read：" + buf.toString(CharsetUtil.UTF_8));
//        //写会给客户端
//        ctx.writeAndFlush(msg);
//
//        ctx.close();

//        ReferenceCountUtil.release(buf);
//        buf.release();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
