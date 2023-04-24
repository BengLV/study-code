package designpatterns.行为模式.责任链模式.demo2.components;

import designpatterns.行为模式.责任链模式.demo2.middleware.Container;

/**
 * @description: 复杂组件可能会对默认实现进行重写。
 *  如果无法以新的方式来提供帮助文字，那组件总是还能调用基础实现的
 * @author: LuPeng
 * @create: 2022-08-31
 **/
public class Dialog extends Container {

    private String wikiPageURL;

    @Override
    public void showHelp() {
        if (wikiPageURL != null) {
            System.out.println(wikiPageURL + "dialog");
        } else {
            super.showHelp();
        }
    }

    public String getWikiPageURL() {
        return wikiPageURL;
    }

    public void setWikiPageURL(String wikiPageURL) {
        this.wikiPageURL = wikiPageURL;
    }

    public Dialog(String wikiPageURL) {
        this.wikiPageURL = wikiPageURL;
    }
}
