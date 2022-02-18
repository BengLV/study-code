package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import nettydemo.netty.itcast.message.LoginRequestMessage;
import nettydemo.netty.itcast.protocol.MessageCodec;

public class TestMessageCodec {

    public static void main(String[] args) throws Exception {
        //用于测试的channel
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                //解决黏包半包问题
                new LengthFieldBasedFrameDecoder(
                1024, 12, 4, 0, 0),
                new MessageCodec());

        //encode
        LoginRequestMessage message = new LoginRequestMessage("zhangshan", "123");
        channel.writeOutbound(message);

        // decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);
        channel.writeInbound(buf);
    }
}
