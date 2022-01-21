package netty.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

@Slf4j
public class Service {

    public static void main(String[] args) throws IOException {
        //1. 创建 selector, 管理多个channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //2. 建立 selector 和 channel 的联系(注册)
        //selectionKey 就是在时间发生后, 通过它可以知道时间和哪个channel的事件关联
        SelectionKey sscKey = ssc.register(selector, 0, null);
        //key 只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.info("register key:{}", sscKey);

        ssc.bind(new InetSocketAddress(8081));
        while (true) {
            //3. select方法,没有事件发生时,线程阻塞,有事件,线程才会恢复运行.
            //当事件未处理时,不阻塞. 事件发生时,要么取消,要么处理,不能置之不理
            selector.select();
            //4. 处理事件, selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();// accept  read
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                //处理完key时,需要从集合中删除,否则,会报NPE;
                iter.remove();
                log.info("key:{}", key);
                //5. 区分事件类型
                if (key.isAcceptable()) {//accept
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    //将第一个byteBuffer作为附件关联到selectionKey上
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("{}", sc);
                } else if (key.isReadable()) {//read
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        //获取关联的附件.
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);//如果是正常断开, 返回-1
                        if (read == -1) {
                            key.cancel();
                        } else {
                            split(buffer);
                            //System.out.println(Charset.defaultCharset().decode(buffer));
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();//因为客户端断开,需要取消.
                    }
                }

                //key.cancel();//取消事件,会阻塞
            }
        }
    }

    private static void split(ByteBuffer source) {
        source.flip();//切换到读模式
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                //将这条完整的消息存入新的ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                //从source读, 向target写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                target.flip();
                System.out.println(Charset.defaultCharset().decode(target));
            }
        }

        source.compact();//切换到写模式,不会清除未读完的数据
    }
}
