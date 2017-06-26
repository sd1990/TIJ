package org.songdan.tij.designpattern.filters;

/**
 * 业务的完成者
 * Created by SongDan on 2017/6/18.
 */
public class RealWorker implements Worker {

    public void work(){
        System.out.println("i'm working");
    }

    public static void main(String[] args) {
        RealWorker realWorker = new RealWorker();
        new DubboWorkerFilterWrapper(realWorker).work();

        new HttpWorkerFilterWrapper(new RealWorker()).work();
    }

}
