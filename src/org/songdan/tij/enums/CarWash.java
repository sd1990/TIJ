package org.songdan.tij.enums;

import java.util.EnumSet;

/**
 * 洗车的例子<br/>
 * 每个顾客在洗车的时候，都有一个选择菜单，每个选择对应一个不同的动作。
 * Created by PC on 2016/4/4.
 */
public class CarWash {

    /**
     * 洗车的所有菜单
     */
    public enum Cycle{

        UNDERBODY{
            @Override
            public void action() {
                System.out.println("洗下盘");
            }
        },

        WHEELWASH{
            @Override
            public void action() {
                System.out.println("洗轮胎");
            }
        },

        PREWASH{
            @Override
            public void action() {
                System.out.println("洗之前的准备工作");
            }
        },

        BASIC{
            @Override
            public void action() {
                System.out.println("基本工作");
            }
        },
        HOTWAX{
            @Override
            public void action() {
                System.out.println("热风");
            }
        },
        RINSE{
            @Override
            public void action() {
                System.out.println("漂洗");
            }
        },

        BLOWDRY{
            @Override
            public void action() {
                System.out.println("风干");
            }
        };

        /**
         * 菜单对应执行的操作
         */
        public abstract void action();

    }

    private EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);

    public void add(Cycle cycle) {
        cycles.add(cycle);
    }

    public void washCar() {
        for (Cycle cycle : cycles) {
            cycle.action();
        }
    }

    public static void main(String[] args) {
        //创建一次洗车任务
        CarWash carWash = new CarWash();
        carWash.washCar();
        carWash.add(Cycle.PREWASH);
        carWash.add(Cycle.BLOWDRY);
        carWash.add(Cycle.PREWASH);
        carWash.add(Cycle.HOTWAX);
        carWash.washCar();
    }

}
