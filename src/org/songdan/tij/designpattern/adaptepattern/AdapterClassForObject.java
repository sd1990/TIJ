package org.songdan.tij.designpattern.adaptepattern;

/**
 * 对象适配器
 * 可以适配AdapteeClass及其子类，但是不方便重写specificRequest()方法
 * @author SONGDAN
 *
 */
public class AdapterClassForObject implements TargetClass{

    private AdapteeClass adaptee;
    

    public AdapterClassForObject(AdapteeClass adaptee) {
        super();
        this.adaptee = adaptee;
    }


    @Override
    public void request() {
        System.out.println("request run ...");
        adaptee.specificRequest();
        System.out.println("request run over ...");
    }

}
