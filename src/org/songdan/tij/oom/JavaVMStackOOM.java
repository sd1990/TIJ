package org.songdan.tij.oom;

/**
 *
 * Created by SongDan on 2016/10/11.
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(()-> {
                dontStop();
            }).start();
        }
    }

    public static void main(String[] args) {
        new JavaVMStackOOM().stackLeakByThread();
    }

}
