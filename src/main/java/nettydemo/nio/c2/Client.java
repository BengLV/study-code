package nettydemo.nio.c2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8081));
        sc.write(Charset.defaultCharset().encode("01234567890abc333"));
        System.in.read();
    }
}
