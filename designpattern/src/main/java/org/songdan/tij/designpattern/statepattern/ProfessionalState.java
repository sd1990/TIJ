package org.songdan.tij.designpattern.statepattern;

import java.util.Random;

public class ProfessionalState extends PlayerState {

    private Player player;
    

    public ProfessionalState(Player player) {
        this.player=player;
    }

    @Override
    public void play() {
        int score = new Random().nextInt(2)%2==0?200:-100;
        if(score>=0){
            score*=2;
        }
        System.out.println("高手境界：游戏可以换牌。积分"+score);
        player.setScore(player.getScore()+score);
        changeState();
    }
    /**
     * 根据条件切换状态
     */
    public void changeState(){
        int score = player.getScore();
        System.out.println("玩家的积分是："+score);
        if(score<100){
            System.out.println("很难过，您将降到新手状态");
            player.setState(player.getPrimary());
        }else if(score<500){
            System.out.println("很难过，您将降到熟练状态");
            player.setState(player.getSecondary());
        }else if(score>=1000){
            System.out.println("恭喜您，您将升至骨灰级状态");
            player.setState(player.getFinalState());
        }
    }

}