package com.ericsson.research.perftest.echo.netty4;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.buffer.Unpooled.copiedBuffer;

public class HttpRequestDispatchHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception
	{
		if (msg instanceof FullHttpRequest)
		{
			final FullHttpRequest request = (FullHttpRequest) msg;

			final String responseMessage = request.content().toString(CharsetUtil.UTF_8);

			FullHttpResponse response = new DefaultFullHttpResponse(
					HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK,
					copiedBuffer(responseMessage.getBytes())
			);

			if (HttpHeaders.isKeepAlive(request))
			{
				response.headers().set(
						HttpHeaders.Names.CONNECTION,
						HttpHeaders.Values.KEEP_ALIVE
				);
			}
			response.headers().set(HttpHeaders.Names.CONTENT_TYPE,
					"text/plain");
			response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,
					responseMessage.length());

			ctx.writeAndFlush(response);
		}
		else
		{
			super.channelRead(ctx, msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx)
			throws Exception
	{
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx,
								Throwable cause) throws Exception
	{
		ctx.writeAndFlush(new DefaultFullHttpResponse(
				HttpVersion.HTTP_1_1,
				HttpResponseStatus.INTERNAL_SERVER_ERROR,
				copiedBuffer(cause.getMessage().getBytes())
		));
	}
}
