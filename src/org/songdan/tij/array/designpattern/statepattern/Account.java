package org.songdan.tij.array.designpattern.statepattern;

/**状态模式演示类
 * 银行账号类
 * @author SONGDAN
 *
 */
public class Account {
    /**
     * 账户状态类
     */
    private AccountState state ;
    /**
     * 账户余额
     */
    private int balance=0;
    
    
    
    public AccountState getState() {
        return state;
    }
    
    public void setState(AccountState state) {
        this.state = state;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    /**
     * 账户名
     */
    private String owner;
    
    
    public Account(String owner,int init) {
        this.owner = owner;
        this.balance = init;
        this.state=new NormalState(this);
        System.out.println(this.owner+"开户，初始金额为"+this.balance+"，账户状态是"+this.state.getClass().getName());
    }
    /**
     * 取款方法
     * @param money
     */
    public void withDrawMoney(int money){
        System.out.println(this.owner+"取款"+money+"元");
        this.state.withDrawMoney(money);
        System.out.println("账户余额"+this.balance+"元");
        System.out.println("账户状态为"+this.state.getClass().getName());
        System.out.println("========================================");
    }
    /**
     * 存款方法
     * @param money
     */
    public void deposit(int money){
        System.out.println(this.owner+"存款"+money+"元");
        this.state.deposit(money);
        System.out.println("账户余额"+this.balance+"元");
        System.out.println("账户状态为"+this.state.getClass().getName());
        System.out.println("========================================");
    }
    public void computeInterest(){
        this.state.computeInterest();
    }
    
    public static void main(String[] args) {
        Account acc = new Account("songdan", 0);
        acc.deposit(1000);
        acc.withDrawMoney(2000);
        acc.withDrawMoney(2000);
        acc.withDrawMoney(1000);
    }
}
