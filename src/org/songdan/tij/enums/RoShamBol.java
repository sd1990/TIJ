package org.songdan.tij.enums;

import java.util.Random;

/**
 * 剪刀石头布游戏的结果
 * @author SONGDAN
 *
 */
enum OutCome{/**胜利*/WIN,/**失败*/LOSE,/**相同*/EQUALS}

/**
 * 剪刀石头布的基类,有剪刀，石头，布都要比较，都有不同的比较结果
 * 所以要有三种重载的方法
 * @author SONGDAN
 *
 */
interface Item{
    /**
     * 和另一个物体比较
     * @param it
     * @return
     */
    OutCome compete(Item it);
    
    /**
     * 和布比较
     * @param paper
     * @return
     */
    OutCome eval(Paper paper);

    /**
     * 和石头比较
     * @param rock
     * @return
     */
    OutCome eval(Rock rock);
    
    /**
     * 和剪刀比较的方法
     * @param scissors
     * @return
     */
    OutCome eval(Scissors scissors);
}

/**
 * 布
 * @author SONGDAN
 *
 */
class Paper implements Item{

    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public OutCome eval(Paper paper) {
        return OutCome.EQUALS;
    }

    @Override
    public OutCome eval(Rock rock) {
        return OutCome.LOSE;
    }

    @Override
    public OutCome eval(Scissors scissors) {
        return OutCome.WIN;
    }
    
    @Override
    public String toString() {
        return "Paper";
    }
}

/**
 * 石头
 * @author SONGDAN
 *
 */
class Rock implements Item{
    
    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }
    
    @Override
    public OutCome eval(Paper paper) {
        return OutCome.WIN;
    }

    @Override
    public OutCome eval(Rock rock) {
        return OutCome.EQUALS;
    }

    @Override
    public OutCome eval(Scissors scissors) {
        return OutCome.LOSE;
    }
    
    @Override
    public String toString() {
        return "Rock";
    }
}

/**
 * 剪刀
 * @author SONGDAN
 *
 */
class Scissors implements Item{

    @Override
    public OutCome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public OutCome eval(Paper paper) {
        return OutCome.LOSE;
    }

    @Override
    public OutCome eval(Rock rock) {
        return OutCome.WIN;
    }

    @Override
    public OutCome eval(Scissors scissors) {
        return OutCome.EQUALS;
    }

    @Override
    public String toString() {
        return "Scissors";
    }
    
}

public class RoShamBol {
    
    private static int round = 20;

    private static Random rand = new Random(47);
    
    /**
     * 随机生成剪刀石头布任意的一项
     * @return
     */
    public static Item newItem(){
        int num = rand.nextInt();
        switch(num%3){
            default:    
            case 0:
                return new Scissors();
            case 1:
                return new Rock();
            case 2:
                return new Paper();
        }
    }
    
    public static void match(Item a,Item b){
        System.out.println(a+" VS "+b+" : "+a.compete(b));
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < round; i++) {
            match(newItem(), newItem());
        }
    }
}
