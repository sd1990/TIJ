package org.songdan.tij.thread.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * 订阅的主题
 * Created by SongDan on 2016/9/25.
 */
public class ObservableSet<E> {

    private List<ObserveSet<E>> observeSets = new ArrayList<>();

    /**
     * 添加订阅者
     * @param observeSet 订阅者
     */
    public void add(ObserveSet<E> observeSet) {
        synchronized (this) {
            observeSets.add(observeSet);
        }
    }

    /**
     * 移除订阅者
     * @param observeSet 需要被移除的订阅者
     */
    public void remove(ObserveSet<E> observeSet) {
        synchronized (this) {
            observeSets.remove(observeSet);
        }
    }

    private void notifyObserver(E e) {
        synchronized (this) {
            for (ObserveSet<E> observeSet : observeSets) {
                observeSet.added(this, e);
            }
        }
    }

    public void updateTopic(E e) {
        notifyObserver(e);
    }

    public static void main(String[] args) {
        /*ObservableSet<String> observableSet = new ObservableSet<>();
        observableSet.add((observableSet1, str) -> {
            System.out.println(str);
        });
        observableSet.updateTopic("hello observe");*/

        /*
        这个问题跟同步没有关系，因为synchronized锁是对单个线程来说是可重入的，此问题展示的是在遍历过程中，移除列表中的元素，导致ConcurrentModificationException
         */
        /*ObserveSet[] obset = new ObserveSet[1];
        obset[0] = (observeSet1,str)->{
            observeSet1.remove(obset[0]);
        };
        observableSet.add(obset[0]);
        observableSet.updateTopic("hello observe ConcurrentModificationException");*/

        /*
        此问题展示的线程饥饿死锁，主线程持有锁并等待第二个线程完成，而第二个线程获取不到锁（因为锁被第一个线程持有，必须等到第一个线程执行完毕才能获取锁）
         */
        /*observableSet.add(new ObserveSet<String>() {

            @Override
            public void added(ObservableSet<String> observableSet, String s) {
                ObserveSet<String> observer = this;
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                try {
                    executorService.submit(()-> {
                        observableSet.remove(observer);
                    }).get();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }
                executorService.shutdown();
            }
        });
        observableSet.updateTopic("hello deadlock");*/
    }
}
