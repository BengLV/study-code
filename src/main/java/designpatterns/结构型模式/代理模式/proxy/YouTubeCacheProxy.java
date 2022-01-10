package designpatterns.结构型模式.代理模式.proxy;

import designpatterns.结构型模式.代理模式.some_cool_media_library.ThirdPartyYouTubeClass;
import designpatterns.结构型模式.代理模式.some_cool_media_library.ThirdPartyYouTubeLib;
import designpatterns.结构型模式.代理模式.some_cool_media_library.Video;

import java.util.HashMap;

/**
 * 代理 （Proxy） 类包含一个指向服务对象的引用成员变量。
 * 代理完成其任务 （例如延迟初始化、 记录日志、 访问控制和缓存等） 后会将请求传递给服务对象。
 * 通常情况下， 代理会对其服务对象的整个生命周期进行管理。
 */
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private HashMap<String, Video> cachePopular = new HashMap<String, Video>();
    private HashMap<String, Video> cacheAll = new HashMap<String, Video>();

    public YouTubeCacheProxy() {
        this.youtubeService = new ThirdPartyYouTubeClass();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = youtubeService.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = youtubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cachePopular.clear();
        cacheAll.clear();
    }
}