package org.songdan.tij.designpattern.sington;

/**
 * @author Songdan
 * @date 2017/3/1 9:18
 */
public class DCLSingleton {

    static class A{

        private static volatile A instance = null;

        private A() {

        }

        public static A getInstance() {
            if (instance == null) {
                synchronized (A.class) {
                    if (instance ==null) {
                        instance = new A();
                    }
                }
            }
            return instance;
        }
    }

    static class B{

        private static B instance = null;

        private B() {

        }

        public static B getInstance() {
            if (instance == null) {
                synchronized (B.class) {
                    if (instance ==null) {
                        instance = new B();
                    }
                }
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        A.getInstance();
        B.getInstance();
    }

}
