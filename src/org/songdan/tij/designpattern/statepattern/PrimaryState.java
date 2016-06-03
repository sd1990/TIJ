package org.songdan.tij.designpattern.statepattern;

import java.util.Random;

/**
 * 入门级状态
 * @author SONGDAN
 *
 */
public class PrimaryState extends PlayerState {

    private Player player;
    
    public PrimaryState(Player player) {
        this.player=player;
    }

    @Override
    public void play() {
        int score = new Random().nextInt(2)%2==0?200:-100;
        player.setScore(player.getScore()+score);
        System.out.println("新手玩游戏:获取积分："+score);
        changeState();
    }

    /**
     * 根据条件切换状态
     */
    public void changeState(){
        int score = player.getScore();
        System.out.println("玩家的积分是："+score);
        if(score>=100&&score<500){
            //切换至熟练状态
            System.out.println("恭喜你，升级至熟练状态");
            player.setState(player.getSecondary());
        }else if(score>500&&score<1000){
            System.out.println("恭喜你，升级至高手状态");
            //切换至高手状态
            player.setState(player.getProfessional());
        }else if(score>=1000){
            System.out.println("恭喜你，升级至骨灰级状态");
            //切换至骨灰级状态
            player.setState(player.getFinalState());
        }
    }

}
