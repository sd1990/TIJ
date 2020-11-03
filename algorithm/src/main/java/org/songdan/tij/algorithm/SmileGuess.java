package org.songdan.tij.algorithm;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 猜笑脸
 *
 * 找笑脸游戏，规则是：方格中的数是几，就表示周围的八个方格里有几张笑脸，请你把所有的笑脸都找出来
 *
 * @author: Songdan
 * @create: 2020-05-23 18:03
 **/
public class SmileGuess {

    private int rows;

    private int columns;

    private int[][] table;

    public SmileGuess(int rows,int columns,Map<Location,Integer> locations) {
        this.rows = rows;
        this.columns = columns;
        table = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                table[i][j] = -1;
            }
        }
        Set<Map.Entry<Location, Integer>> entries = locations.entrySet();
        for (Map.Entry<Location, Integer> entry : entries) {
            Location location = entry.getKey();
            table[location.x][location.y] = entry.getValue();
        }
    }

    public void guess() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int n = table[i][j];
                if (n > 0) {
                    int count = count(i, j);
                    if (n!=count) {
                        //TODO
                    }
                }




            }
        }
    }

    private int count(int i,int j) {
        int count = 0;
        if (i - 1 >= 0 && j - 1 >= 0) {
            int e1 = table[i - 1][j - 1];
            if (e1 == 0) {
                count += 1;
            }
        }
        if (i - 1 >= 0) {
            int e = table[i - 1][j];
            if (e == 0) {
                count += 1;
            }
        }
        if (i - 1 >= 0 && j + 1 < columns) {
            int e = table[i - 1][j+1];
            if (e == 0) {
                count += 1;
            }
        }
        if (j - 1 >= 0) {
            int e = table[i][j-1];
            if (e == 0) {
                count += 1;
            }
        }
        if (j + 1 < columns) {
            int e = table[i][j+1];
            if (e == 0) {
                count += 1;
            }
        }
        if (i + 1 < rows && j - 1 >= 0) {
            int e = table[i + 1][j - 1];
            if (e == 0) {
                count += 1;
            }
        }
        if (i + 1 < rows) {
            int e = table[i + 1][j];
            if (e == 0) {
                count += 1;
            }
        }
        if (i + 1 < rows && j+1 < columns) {
            int e = table[i + 1][j - 1];
            if (e == 0) {
                count += 1;
            }
        }
        return count;
    }

    class Location{
        private int x;

        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }

}
