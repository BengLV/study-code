package designpatterns.行为模式.状态模式.ui;

import designpatterns.行为模式.状态模式.states.ReadyState;
import designpatterns.行为模式.状态模式.states.State;

import java.util.ArrayList;
import java.util.List;


/**
 * 上下文保存了对于一个具体状态对象的引用， 并会将所有与该状态相关的工作委派给它。
 * 上下文通过状态接口与状态对象交互， 且会提供一个设置器用于传递新的状态对象。
 */
public class Player {

    /**
     * 对状态对象的引用,
     */
    private State state;

    private boolean playing = false;
    private List<String> playlist = new ArrayList<>();
    private int currentTrack = 0;

    public Player() {
        this.state = new ReadyState(this);
        setPlaying(true);
        for (int i = 1; i <= 12; i++) {
            playlist.add("Track " + i);
        }
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isPlaying() {
        return playing;
    }

    public String startPlayback() {
        return "Playing " + playlist.get(currentTrack);
    }

    public String nextTrack() {
        currentTrack++;
        if (currentTrack > playlist.size() - 1) {
            currentTrack = 0;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String previousTrack() {
        currentTrack--;
        if (currentTrack < 0) {
            currentTrack = playlist.size() - 1;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        this.currentTrack = 0;
    }

}
