package org.songdan.tij.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Songdan
 * @date 2017/4/21 14:02
 */
public class Test {

    private volatile Map<String, Integer> map;

    public Map<String, Integer> getMap() throws InterruptedException {
        build();
        return map;
    }

    private void build() throws InterruptedException {
        if (map == null) {
            synchronized (this) {
                if (map == null) {
                    Map map = new HashMap<>();
                    this.map = map;
                    Thread.sleep(1000);
                    map.put("a0", 1);
                    map.put("a1", 11);
                    map.put("a2", 111);
                    map.put("a3", 1111);
                    map.put("a4", 11111);
                }
            }
        }
    }

    public void test() {
        for(int i = 0; i < 10; i++) {
            final int n = i;
            new Thread() {
                public void run() {
                    Map<String, Integer> map = null;
                    try {
                        map = getMap();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int x = map.get("a" + n % 4);
                    System.out.println(x);
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test();
    }
}

