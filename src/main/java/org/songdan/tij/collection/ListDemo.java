package org.songdan.tij.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    
    
    
    public ListDemo() {
        super();
        System.out.println("list demo create ...");
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"a","b","c","d"};
//        List<String> list = new ArrayList<>(Arrays.asList(strs));
        List<String> list = new LinkedList<>(Arrays.asList(strs));
        /*for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            iterator.remove();
            System.out.println(string);
        }*/
        List<String> b = new LinkedList<String>();
        b.add("c");
        b.add("d");
        System.out.println(indexOf(list,b));
        System.out.println(Collections.indexOfSubList(list, b));
    }
    
    static int indexOf(List<String> source ,List<String> target){
        int sourceSize =source.size();
        int targetSize = target.size();
        int range = sourceSize-targetSize;
        /*
         * 怎么判断一个集合时另一个集合的一部分呢，大的集合从从脚标0到（source-target）之间开始截取target的size如果能与target中每一个元素相等即可
         */
        out:for(int sIndex = 0;sIndex<=range;sIndex++){
            
            inner:for(int tIndex=0,j=sIndex;tIndex<targetSize;tIndex++,j++){
                if(!source.get(j).equals(target.get(tIndex))){
                    continue out;
                }
                
            }
            
            return sIndex;
            
        }
        return -1;
    }

    static int indexOf2(List<String> source ,List<String> target){
        int sourceSize =source.size();
        int targetSize = target.size();
        int range = sourceSize-targetSize;
        /*
         * 怎么判断一个集合时另一个集合的一部分呢，大的集合从从脚标0到（source-target）之间开始截取target的size如果能与target中每一个元素相等即可
         */
        for(int sIndex = 0;sIndex<=range;sIndex++){
            if(equalList(source.subList(sIndex, sIndex+targetSize),target)){
                return sIndex;
            }
        }
        return -1;
    }

    private static boolean equalList(List<String> subList, List<String> target) {
        for (int i = 0; i < target.size(); i++) {
            if(!subList.get(i).equals(target.get(i))){
                return false;
            }
        }
        return true;
    }
    
}
