package netty.c1;

import java.nio.ByteBuffer;

public class TestByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[] {'a', 'b', 'c', 'd'});
        buffer.flip();
        System.out.println((char) buffer.get());
        buffer.rewind();//重头开始读
        System.out.println((char) buffer.get());

        //mark & reset
        //mark 做一个标记,记录position 位置, reset 是将position重置到mark位置
        buffer.mark();//加标记
        System.out.println((char) buffer.get());
        buffer.reset();//将position重置到标记的位置
        System.out.println((char) buffer.get());

        //get(i)不会改变读索引的位置
        System.out.println((char) buffer.get(0));
    }

}
