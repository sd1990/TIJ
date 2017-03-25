package org.songdan.tij.designpattern.adaptepattern;

/**
 * 充当类适配器角色的类
 * 不能适配AdapteeClass及其子类，但是可以重写specificRequest()
 * @author SONGDAN
 *
 */
public class AdapterClassForClass extends AdapteeClass implements TargetClass{

    @Override
    public void request() {
        System.out.println("request run ...");
        specificRequest();
        System.out.println("run over ...");
    }

}
