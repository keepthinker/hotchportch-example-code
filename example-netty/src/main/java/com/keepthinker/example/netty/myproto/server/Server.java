package com.keepthinker.example.netty.myproto.server;

import com.keepthinker.example.netty.myproto.codec.Decoder;
import com.keepthinker.example.netty.myproto.codec.Encoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

	private final int port;

	private ServerBootstrap bootstrap;

	private EventLoopGroup bossGroup;

	private EventLoopGroup workerGroup;

	public static void main(String[] args){
		int port;
		if(args.length > 0){
			port = Integer.parseInt(args[1]);
		}else{
			port = 8080;
		}

		Server server = new Server(port);
		server.start();

	}

	public Server(int port){
		this.port = port;
		init();
	}

	private void init(){
		bossGroup = new NioEventLoopGroup(1); // (1)
		workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
		bootstrap = new ServerBootstrap(); // (2)
		bootstrap.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class) // (3)
		.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new Encoder("Server Encoder"), new Decoder("Server Decoder"), new ServerInboundHandler());
			}
		})
		.option(ChannelOption.SO_BACKLOG, 128)          // (5)
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.childOption(ChannelOption.TCP_NODELAY, true)		; // (6)

	}

	private void start(){
		try {
			// Bind and start to accept incoming connections.
			ChannelFuture f = bootstrap.bind(port).sync(); // (7)
			
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		}catch(Exception e){ 
			e.printStackTrace();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}


}
