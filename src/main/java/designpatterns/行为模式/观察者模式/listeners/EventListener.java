package designpatterns.行为模式.观察者模式.listeners;

import java.io.File;

public interface EventListener {
    void update(String eventType, File file);
}
