package org.songdan.tij.holding;

import java.util.*;

public class PrintingContainer {
    public static Collection fill(Collection<String> collection){
        long start = System.nanoTime();
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        System.out.println("fill takes :"+(System.nanoTime()-start));
        return collection;
    }
    
    public static Map fill(Map<String,String> map){
        long start = System.nanoTime();
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spot");
        System.out.println("fill takes :"+(System.nanoTime()-start));
        return map;
    }
    public static void main(String[] args) {
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String,String>()));
        System.out.println(fill(new TreeMap<String,String>()));
        System.out.println(fill(new LinkedHashMap<String,String>()));
    }
}
