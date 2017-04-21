package org.songdan.tij.oom;

/**
 * StackOverFlowError
 * 在单个线程，无论是栈帧太大还是虚拟机栈内存太小，当内存无法分配时，虚拟机抛出的都是StackOverflowError
 * 在多线程的情况下，如果建立过多线程导致的内存溢出，在不能减少线程的情况下或者更换64位虚拟机的情况下，就只能通过
 * 减少最大堆和减少栈容量来换更多的线程。
 *
 * vm args：-Xss128k
 * Created by SongDan on 2016/10/11.
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {

            stackSOF.stackLeak();
        }
        catch (Throwable throwable) {
            System.out.println(stackSOF.stackLength);
            throw throwable;
        }
    }
}
