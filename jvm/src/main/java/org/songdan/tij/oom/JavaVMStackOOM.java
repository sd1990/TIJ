package org.songdan.tij.oom;

/**
 * 栈内存溢出，不断的创建新线程，有系统假死的风险
 * Created by SongDan on 2016/10/11.
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(() -> {
                dontStop();
            }).start();
        }
    }

    public static void main(String[] args) {
        new JavaVMStackOOM().stackLeakByThread();
    }

}
