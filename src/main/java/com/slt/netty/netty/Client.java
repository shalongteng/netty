package com.slt.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * netty 基于事件模型
 * netty里所有的方法都是异步的
 */
public class Client {
    public static void main(String[] args) {
        new Client().clientStart();
    }

    private void clientStart() {
        //事件处理的线程池  connect read write
        EventLoopGroup workers = new NioEventLoopGroup(5);
        //辅助启动
        Bootstrap b = new Bootstrap();
        b.group(workers)
                .channel(NioSocketChannel.class)
                //channel 的初始化器
                .handler(new ChannelInitializer<SocketChannel>() {
                    //channel 初始化的时候调用initChannel，此时还没有连接上
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        System.out.println("channel initialized!");
                        //observer 套 observer
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });

        try {
            System.out.println("start to connect...");
            //connect 异步方法如果不调用sync方法，需要加一个监听器来回调  sync同步阻塞等待
            ChannelFuture future = b.connect("127.0.0.1", 8888);

            //异步的写法 加一个监听器listener
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("netty connected");
                    }else {
                        System.out.println("netty not connected");
                    }
                }
            });
            //不写同步等待，看不到异常的抛出
            future.sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            workers.shutdownGracefully();
        }

    }


}

class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel is activated.");

        final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("HelloNetty".getBytes()));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("msg send!");
                //ctx.close();
            }
        });


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf)msg;
            System.out.println(buf.toString());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
