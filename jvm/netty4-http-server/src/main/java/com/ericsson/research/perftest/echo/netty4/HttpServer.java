package com.ericsson.research.perftest.echo.netty4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

public class HttpServer {

	private final int port;
	private final String host;
	private EventLoopGroup boss = new NioEventLoopGroup(16, new DefaultThreadFactory("nio-boss"));
	private EventLoopGroup worker = new NioEventLoopGroup(128, new DefaultThreadFactory("nio-worker"));

	public HttpServer(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(
				new HttpServerInitializer());
			setChannelOptions(bootstrap);

			// set to ipv4
			Channel ch = bootstrap.bind(host, port).sync().channel();

			System.out.format(">> startUp server %s", ch.localAddress().toString());

			ch.closeFuture().sync(); // blocked

		} finally {
			shutdown();
		}
	}

	protected void shutdown() {
		boss.shutdownGracefully();
		worker.shutdownGracefully();
	}

	protected void setChannelOptions(ServerBootstrap bootstrap) {
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
		bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
	}

	public static void main(String[] args) {

		String host;
		int port;

		if (args.length != 2){
			System.out.println("Expected host and port number as argument. Using 127.0.0.1:8080 by default");
			host = "127.0.0.1";
			port = 8080;
		}
		else {
			host = args[0];
			port = new Integer(args[1]);
		}

		try {
			new HttpServer(host, port).run();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
