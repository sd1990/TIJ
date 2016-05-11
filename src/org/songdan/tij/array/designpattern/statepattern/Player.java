package org.songdan.tij.array.designpattern.statepattern;

/**
 * Sunny软件公司欲开发一款纸牌游戏软件，在该游戏软件中用户角色具有入门级(Primary)、熟练级(Secondary)、高手级(
 * Professional)和骨灰级(Final)四种等级，角色的等级与其积分相对应，游戏胜利将增加积分，失败则扣除积分。入门级具有最基本的游戏功能play
 * () ，熟练级增加了游戏胜利积分加倍功能doubleScore()，高手级在熟练级基础上再增加换牌功能changeCards()，
 * 骨灰级在高手级基础上再增加偷看他人的牌功能peekCards()。 试使用状态模式来设计该系统。 
 * 状态模式游戏系统上下文角色
 * 
 * @author SONGDAN
 */
public class Player {
    /**
     * 状态类
     */
    private PlayerState state ;
    
    private PlayerState primary;
    
    private PlayerState secondary;
    
    private PlayerState professional;
    
    private PlayerState finalState;
    
    public PlayerState getState() {
        return state;
    }
    
    public void setState(PlayerState state) {
        this.state = state;
        new SecondaryState(this);
    }
    /**
     * 用户的积分
     */
    private int score;
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    

    public Player() {
        //初始化游戏玩家
        score=0;
        primary = new PrimaryState(this);
        secondary = new SecondaryState(this);
        professional = new ProfessionalState(this);
        finalState = new FinalState(this);
        state=primary;
    }
    
    
    public void play(){
       this.state.play();
    }

    
    public PlayerState getPrimary() {
        return primary;
    }

    
    public void setPrimary(PlayerState primary) {
        this.primary = primary;
    }

    
    public PlayerState getSecondary() {
        return secondary;
    }

    
    public void setSecondary(PlayerState secondary) {
        this.secondary = secondary;
    }

    
    public PlayerState getProfessional() {
        return professional;
    }

    
    public void setProfessional(PlayerState professional) {
        this.professional = professional;
    }

    
    public PlayerState getFinalState() {
        return finalState;
    }

    
    public void setFinalState(PlayerState finalState) {
        this.finalState = finalState;
    }
    
    public static void main(String[] args) {
        Player player = new Player();
        for (int i = 0; i < 10; i++) {
            System.out.println("==============================================");
            player.play();
        }
    }
}
