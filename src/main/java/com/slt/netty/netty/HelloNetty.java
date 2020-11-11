package com.slt.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class HelloNetty {
    public static void main(String[] args) {
        new NettyServer(8888).serverStart();
    }
}

class NettyServer {
    int port = 8888;

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
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //监听器处理过程 就是 加一个对通道的处理器
                        ch.pipeline().addLast(new Handler());
                    }
                });

        try {
            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}

class Handler extends ChannelInboundHandlerAdapter {
    /**
     * msg 数据已经读进来了
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //super.channelRead(ctx, msg);
        System.out.println("server: channel read");
        ByteBuf buf = (ByteBuf)msg;

        System.out.println(buf.toString(CharsetUtil.UTF_8));

        ctx.writeAndFlush(msg);

        ctx.close();

        //buf.release();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
