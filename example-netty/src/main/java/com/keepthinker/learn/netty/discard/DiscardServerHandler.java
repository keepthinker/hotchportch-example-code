package com.keepthinker.example.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Hello world!
 *
 */
/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
		// Discard the received data silently.
		 ByteBuf in = (ByteBuf) msg;
		try {
			String requestData = in.toString(io.netty.util.CharsetUtil.US_ASCII);
//			String responseLine = "HTTP/1.1 200 OK\r\n"
//					+ "Content-Type:text/plain; charset=UTF-8\r\n"
//					+ "\r\n";
//			
//			String responseData = responseLine + requestData;
			System.out.print(requestData);
			/*while (in.isReadable()) { // (1)
				System.out.print((char) in.readByte());

				System.out.flush();
			}*/
			ctx.write(in); // (1)
	        ctx.flush();
		} finally {
//			ReferenceCountUtil.release(msg); // (2)
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}