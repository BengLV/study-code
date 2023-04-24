package designpatterns.行为模式.责任链模式.demo2.middleware;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 容器可以将简单组件和其他容器作为其子项目。链关系将在这里建立
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public abstract class Container extends Component {

    /**
     * 无作用
     */
    private List<Component> childes = new ArrayList<>();

    public void addChild(Component child) {
        childes.add(child);
        //建立链接关系
        child.container = this;
    }
}
