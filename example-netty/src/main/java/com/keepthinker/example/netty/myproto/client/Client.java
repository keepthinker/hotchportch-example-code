package com.keepthinker.example.netty.myproto.client;

import com.keepthinker.example.netty.myproto.codec.Decoder;
import com.keepthinker.example.netty.myproto.codec.Encoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	private static Client client;
	public static Client getDefaultSingleton(){
		if(client == null){
			String host = "localhost";
			int port = 8080;
			client = new Client(host, port);
		}
		return client;
	}

	private String serverHost;
	private int serverPort;
	private  Bootstrap bootstrap;
	private EventLoopGroup workerGroup;
	private static ExecutorService executorService = Executors.newFixedThreadPool(10);

	private List<Worker> workers = new ArrayList<>();

	/** 
	 * For Future use, check channel active or not
	 */
	private CopyOnWriteArrayList<Channel> channelList = new CopyOnWriteArrayList<>();

	public static void main(String[] args){
		String host;
		int port;
		if(args.length > 0){
			host = args[0];
			port = Integer.parseInt(args[1]);
		}else{
			host = "localhost";
			port = 8080;
		}

		Client client = new Client(host, port);

		client.addWorker(new KeyboardWorker());
		client.addWorker(new IntegerIncrementWorker());

		client.start();

	}

	public void addWorker(Worker worker){
		workers.add(worker);
	}

	public void start() {
		for(Worker worker : workers){
			worker.setChannel(this.connect());
			executorService.execute(worker);
		}
	}

	public Client(String serverHost, int serverPort){
		this.serverHost = serverHost;
		this.serverPort = serverPort;

		init();
	}

	private void init(){
		workerGroup = new NioEventLoopGroup();

		bootstrap = new Bootstrap(); // (1)
		bootstrap.group(workerGroup); // (2)
		bootstrap.channel(NioSocketChannel.class); // (3)
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.TCP_NODELAY, true); // (4)
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new Encoder("Cilent Encoder"), new Decoder("Cilent Decoder"), new ClientInboundHandler(workers));
			}
		});
	}

	public Channel connect(){
		// Start the client.
		Channel channel = null;
		try {
			ChannelFuture f = bootstrap.connect(serverHost, serverPort).sync(); // (5)
			channel = f.channel();
			channelList.add(channel);
			// Wait until the connection is closed.
			//			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//			workerGroup.shutdownGracefully();
		}
		return channel;
	}
}
