package org.songdan.tij.array.designpattern.statepattern;

/**
 * 正常状态
 * @author SONGDAN
 *
 */
public class NormalState extends AccountState {

    public NormalState(Account account) {
        this.acc=account;
    }

    @Override
    public void withDrawMoney(int money) {
        acc.setBalance(acc.getBalance()-money);
        stateCheck();
    }

    @Override
    public void deposit(int money) {
        acc.setBalance(acc.getBalance()+money);
        stateCheck();
    }

    @Override
    public void computeInterest() {
        System.out.println("正常状态，无需支付利息");
    }

    @Override
    public void stateCheck() {
        //一定要把账号给传进去
        if(acc.getBalance()>=-2000 && acc.getBalance() < 0){
            acc.setState(new OverdraftState(this.acc));
        }else if(acc.getBalance() <= -2000){
            acc.setState(new RestrictedState(this.acc));
        }
    }
    
    
    
}
