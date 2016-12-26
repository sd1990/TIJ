package org.songdan.tij.interfaces;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomWords implements Readable {

    private static Random rand = new Random(47);

    private static final char[] captitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char[] vowels = "aeiou".toCharArray();

    private AtomicInteger count;

    public RandomWords(AtomicInteger count) {
        super();
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count.getAndDecrement() == 0) {
            return -1;
        }
        cb.append(captitals[rand.nextInt(captitals.length)]);
        for (int i = 0; i < 4; i++) {
            cb.append(vowels[rand.nextInt(vowels.length)]);
            cb.append(lowers[rand.nextInt(lowers.length)]);
        }
        cb.append(" ");
        return 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new RandomWords(new AtomicInteger(10)));
        while (scanner.hasNext()) {
            String string = (String) scanner.next();
            System.out.println(string);
        }
    }

}
