package org.songdan.tij.algorithm.queue;

/**
 *
 * @author: Songdan
 * @create: 2019-03-03 21:53
 **/
public interface Queue {

    boolean enqueue(int i);


    int dequeue();

}

class NormalQueue implements Queue{

    private int[] arr;

    private int length;

    private int head;

    private int tail;

    public NormalQueue(int length) {
        this.length = length;
        this.arr = new int[length];
        head = tail = 0;
    }

    @Override
    public boolean enqueue(int i) {
        if (tail < length) {
            arr[tail] = i;
            tail++;
            return true;
        }
        return false;
    }

    @Override
    public int dequeue() {
        if (head == tail) {
            throw new IllegalStateException();
        }
        int pop = arr[head];
        head++;
        return pop;
    }
}

class OptimizeQueue implements Queue{

    private int[] arr;

    private int length;

    private int head;

    private int tail;

    public OptimizeQueue(int length) {
        this.length = length;
        this.arr = new int[length];
        head = tail = 0;
    }

    @Override
    public boolean enqueue(int i) {
        //清除head之前的元素
        /*if (tail>=length) {
            return false;
        }*/
        if (tail >=length) {
            if (head == 0) {
                return false;
            }
            if (head > 0) {
                for (int j = head; j < length; j++) {
                    arr[j - head] = arr[j];
                }
                tail = tail - head;
                head = 0;
            }
        }
        if (tail < length) {
            arr[tail] = i;
            tail++;
            return true;
        }
        return false;
    }

    @Override
    public int dequeue() {
        if (head == tail) {
            throw new IllegalStateException("列表为空");
        }
        int pop = arr[head];
        head++;
        return pop;
    }
}
class CircularQueue implements Queue{

    private int[] arr;

    private int length;

    private int head;

    private int tail;

    public CircularQueue(int length) {
        this.length = length;
        this.arr = new int[length];
        head = tail = 0;
    }

    @Override
    public boolean enqueue(int i) {
        //清除head之前的元素
        if ((tail + 1) % length == head) {
            return false;
        }
        arr[tail] = i;
        tail=(tail+1)%length;
        return true;
    }

    @Override
    public int dequeue() {
        if (head == tail) {
            throw new IllegalStateException("列表为空");
        }
        int pop = arr[head];
        head = (head + 1) % length;
        return pop;
    }
}
