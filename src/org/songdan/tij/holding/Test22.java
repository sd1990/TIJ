package org.songdan.tij.holding;

import java.util.*;

public class Test22 {
    public static void main(String[] args) {
        String[] strings=new String[]{"abc","abc","abc","bcd","bcd","bcd","dsfd","hjh"};
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        Set<WordCounter> set = new HashSet<>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            WordCounter wc = new WordCounter(string);
            if(set.contains(wc)){
                Iterator<WordCounter> setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    WordCounter wordCounter = (WordCounter) setIterator.next();
                    if(wordCounter.equals(wc)){
                        wordCounter.incCounter();
                    }
                }
            }else{
                set.add(wc);
            }
        }
        System.out.println(set);
        
        Map<WordCounter,String> map = new HashMap<>();
        WordCounter wc1= new WordCounter("abc");
        WordCounter wc2= new WordCounter("abc");
        //e.hash == hash && ((k = e.key) == key || key.equals(k))
        map.put(wc1, "abc");
        System.out.println(map.get(wc2));
    }
    
    
    static void testHash(WordCounter wc,Map<WordCounter,String> map){
        String string = map.get(wc);
        System.out.println(string);
    }
}

class WordCounter{
    private int count;
    
    private String word;

    public WordCounter(String word) {
        super();
        this.word = word;
        count=1;
    }
    
    public void incCounter(){
        count++;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof WordCounter && ((WordCounter)obj).word.equals(word); 
    }
    
    
    @Override
    public int hashCode() {
        return word.hashCode();
    }
    @Override
    public String toString() {
        return word+":"+count;
    }
}