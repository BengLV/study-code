package nettydemo.nio.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TestByteBufferString {

    public static void main(String[] args) {
        //1.字符串转为ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());//处于写模式

        //2.Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");//会自动切换到读模式

        //3.wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());//会自动切换到读模式

        //反之
        buffer.flip();//切换到读模式
        String s = StandardCharsets.UTF_8.decode(buffer2).toString();//只能转读模式下的
        System.out.println(s);
    }
}
