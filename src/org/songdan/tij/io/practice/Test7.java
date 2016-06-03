package org.songdan.tij.io.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by PC on 2016/3/27.
 */
public class Test7 {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader("D:\\workplace\\songdanDemo\\src\\com\\songdan\\demo\\io\\practice\\Test2.java"),
                    2048);
            String line = "";
            while ((line=reader.readLine())!=null) {
                System.out.println(line);
                list.add(line);
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        Collections.reverse(list);
        list.listIterator();
        ListIterator<String> stringListIterator = list.listIterator(list.size());
        while (stringListIterator.hasPrevious()){
            System.out.println(stringListIterator.previous());
        }


    }
}
