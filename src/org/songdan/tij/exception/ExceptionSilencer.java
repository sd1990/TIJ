package org.songdan.tij.exception;


public class ExceptionSilencer {

    @SuppressWarnings("finally")
    public static void main(String[] args) {
        try{
            throw new MyRuntimeException();
        }finally{
            return;
        }
    }
}

class MyRuntimeException extends RuntimeException{

    public MyRuntimeException() {
        super();
        System.out.println("hello runtime exception ...");
    }
    
}