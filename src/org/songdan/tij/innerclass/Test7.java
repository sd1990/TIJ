package org.songdan.tij.innerclass;


public class Test7 {
    
    public static class Outer{
        private String name;
        
        public class Inner{
            public void setOuterName(String name){
                Outer.this.name=name; 
            }
            
            public String getOuterName(){
                return Outer.this.name;
            }
        }
        
        public Inner getInner(){
            return new Inner();
        }
        
        public static void main(String[] args) {
            Outer out = new Outer();
            Inner in=out.getInner();
            in.setOuterName("zhangsan");
            System.out.println(in.getOuterName());
        }
    }
    
    
}
