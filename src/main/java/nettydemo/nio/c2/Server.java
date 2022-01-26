package nettydemo.nio.c2;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        //使用nio来理解阻塞模式, 单线程
        //0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//切换成非阻塞模式  ssc.accept()方法会变成非阻塞
        //2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8081));
        //3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            //4. accept 建立与客户端链接, socketChannel 用来与客户端进行通信
            SocketChannel sc = ssc.accept();//阻塞方法, 线程停止运行, 没有连接的时候, 为null
            if (sc != null) {
                log.info("connected... {}", sc);
                sc.configureBlocking(false);//非阻塞模式, channel.read()方法变成非阻塞
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                int read = channel.read(buffer);//阻塞方法, 如果没有读到数据, read 返回 0
                if (read > 0) {
                    buffer.flip();//切换到读模式
                    System.out.println(JSONObject.toJSONString(buffer));
                    buffer.clear();//切换到写模式
                    log.info("after read... {}", channel);
                }
            }
        }


    }
}
