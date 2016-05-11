package org.songdan.tij.array.designpattern.statepattern;

/**
 * 账户受限状态
 * @author SONGDAN
 *
 */
public class RestrictedState extends AccountState{
    
    public RestrictedState(Account acc) {
        this.acc=acc;
    }

    @Override
    public void withDrawMoney(int money) {
        System.out.println("账户受限,取钱失败");
    }

    @Override
    public void deposit(int money) {
        acc.setBalance(acc.getBalance()+money);
        stateCheck();
    }

    @Override
    public void computeInterest() {
        System.out.println("每日计算利息");
    }

    @Override
    public void stateCheck() {
        if(acc.getBalance()>=0){
            acc.setState(new NormalState(acc));
        }else if(acc.getBalance()>-2000){
            acc.setState(new OverdraftState(acc));
        }
    }

}
