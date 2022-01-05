package designpatterns.结构型模式.外观模式.some_complex_media_library;

import java.io.File;

/**
 * 混音器
 */
public class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}
