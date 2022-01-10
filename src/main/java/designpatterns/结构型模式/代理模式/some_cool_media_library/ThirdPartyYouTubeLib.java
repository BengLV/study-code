package designpatterns.结构型模式.代理模式.some_cool_media_library;

import java.util.HashMap;

/**
 * 服务接口
 * 代理必须遵循该接口才能伪装成服务对象
 */
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}