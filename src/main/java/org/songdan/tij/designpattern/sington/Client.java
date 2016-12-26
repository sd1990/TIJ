package org.songdan.tij.designpattern.sington;


public class Client {
    
    private static Client c = new Client();
    
    private static class Inner{
        static{
            System.out.println("client innere class load");
        }
        private static Client c = new Client();
    }
    
    public Client() {
        super();
        System.out.println("client run ...");
    }

    public static void main(String[] args) {
        SingletonA sa1,sa2;
        sa1=SingletonA.getInstance();
        sa2=SingletonA.getInstance();
        System.out.println(sa1==sa2);
        System.out.println(Client.Inner.c);
    }
}
