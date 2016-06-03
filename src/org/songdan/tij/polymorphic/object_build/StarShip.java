package org.songdan.tij.polymorphic.object_build;


public class StarShip {
    private AlertStatus alert = new AlertStatus();
    
    public void changeAlert(AlertStatus alert){
        this.alert=alert;
    }
    
    public void alert(){
        alert.alert();
    }
    
    public static void main(String[] args) {
        StarShip ship = new StarShip();
        ship.alert();
        ship.changeAlert(new AlertStatus1());
        ship.alert();
    }
}

class AlertStatus{
    public void alert(){
        System.out.println("alert ...");
    }
}

class AlertStatus1 extends AlertStatus{
    @Override
    public void alert() {
        System.out.println("alert one ");
    }
}

class AlertStatus2 extends AlertStatus{
    @Override
    public void alert() {
        System.out.println("alert two");
    }
}

class AlertStatus3 extends AlertStatus{
    @Override
    public void alert() {
        System.out.println("alert three");
    }
}