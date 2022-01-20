package netty.c1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFilesCopy {

    public static void main(String[] args) throws IOException {
        String source = "D:\\data";
        String target = "D:\\dataaa";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                if (Files.isDirectory(path)) {//是目录
                    Files.createDirectories(Paths.get(targetName));
                } else if (Files.isRegularFile(path)) {//是普通文件
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
