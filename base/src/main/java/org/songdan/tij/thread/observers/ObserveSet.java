package org.songdan.tij.thread.observers;

/**
 * 订阅者,订阅某个主题，当主题发生变化时触发回调方法
 * Created by SongDan on 2016/9/25.
 */
public interface ObserveSet<E> {

    /**
     * 当主题发生变化时的回调函数
     *
     * @param e 订阅的内容
     */
    public void added(ObservableSet<E> observableSet, E e);

}
