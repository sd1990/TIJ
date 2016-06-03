package org.songdan.tij.designpattern.statepattern;


public abstract class AccountState {
    
    protected Account acc;
    
    /**
     * 取款方法
     * @param money
     */
    public abstract void withDrawMoney(int money);
    
    /**
     * 存款方法
     * @param money
     */
    public abstract void deposit(int money);

    public abstract void computeInterest() ;
    
    /**
     * 检查状态
     */
    public abstract void stateCheck();

}
