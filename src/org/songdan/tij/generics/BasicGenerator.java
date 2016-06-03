package org.songdan.tij.generics;


public class BasicGenerator<T> implements Generator<T>{

    private Class<T> type;
    
    
    
    public BasicGenerator(Class<T> type) {
        super();
        this.type = type;
    }



    @Override
    public T next() {
        try {
            return type.newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //静态方法不能访问类型参数,STATIC方法必须也是泛型方法
    public static <T> BasicGenerator<T> create(Class<T> type){
        return new BasicGenerator<>(type);
    }
    
}
