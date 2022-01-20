package netty.c1;

import cn.hutool.json.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {
        //FileChannel
        //1.输入输出流    2.RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {//.twr快捷键 会关闭流
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);//分配10个字节的大小区域
            while (true) {
                //从channel读取数据, 向buffer中写入
                int len = channel.read(buffer);
                if (len == -1) {//是否为最后一个字节数据
                    break;
                }
                //打印buffer内容
                buffer.flip();//切换至读模式
                while (buffer.hasRemaining()) {//检查是否还有剩余未读的数据
                    System.out.println(JSONObject.toJSONString(buffer));
                    byte b = buffer.get();//读取一个字节的数据
                    System.out.println((char) b);
                }
                buffer.clear();//切换到写模式 or compact()
            }

        } catch (IOException e) {
        }
    }
}
