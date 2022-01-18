package netty.day1;

import java.nio.ByteBuffer;

public class TestByteBufferExam {

    public static void main(String[] args) {
        /*
         原始数据有三条为: Hello,world\n
                  I'm zhangsan\n
                  How are you\n
         现数据编程了两个byteBuffer(黏包(提升效率),半包(服务器缓冲区大小限制))
                Hello,world\nI'm zhangsan\nHo
                w are you\n
         需求:还原成原始数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you\n".getBytes());
        split(source);
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
            }
        }

        source.compact();//切换到写模式,不会清除未读完的数据
    }
}
