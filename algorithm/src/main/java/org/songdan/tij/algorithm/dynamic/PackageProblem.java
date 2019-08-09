package org.songdan.tij.algorithm.dynamic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 背包问题
 *
 * @author: Songdan
 * @create: 2019-08-08 21:13
 **/
public class PackageProblem {

    private int totalWeight;

    private int[][] matrix;

    private List<Product> productList;

    /**
     * 背包问题 4磅
     * 吉他 1磅 1500
     * 笔记本 3磅 2000
     * 音响 4磅 3000
     */
    public PackageProblem(int totalWeight, List<Product> productList) {
        this.totalWeight = totalWeight;
        this.productList = productList;
        matrix = new int[productList.size()+1][totalWeight+1];
        for (int i = 0; i < totalWeight + 1; i++) {
            matrix[0][i] = 0;
        }

        for (int i = 0; i < productList.size() + 1; i++) {
            matrix[i][0] = 0;
        }
    }

    public int steal() {
        for (int i = 1; i < matrix.length; i++) {
            int[] arr = this.matrix[i];
            Product product = productList.get(i-1);
            for (int j = 1; j < arr.length; j++) {
                int newValue = matrix[i - 1][j];
                if (j - product.weight >= 0) {
                    newValue = matrix[Math.max(j - product.weight, 0)][i - 1] + product.value;
                }
                matrix[i][j] = Math.max(matrix[i - 1][j],newValue);
            }
        }
        return matrix[productList.size()][totalWeight];
    }

    public void print() {
        for (int[] arr : matrix) {
            System.out.print("|");
            for (int value : arr) {
                System.out.print(value+"|");
            }
            System.out.println();
        }
    }




    public static void main(String[] args) {
        PackageProblem packageProblem = new PackageProblem(4, Lists.newArrayList(new Product("吉他", 1, 1500), new Product("笔记本", 3, 2000), new Product("音响", 4, 3000),new Product("iphone",1,2000)));
        System.out.println(packageProblem.steal());
        packageProblem.print();
    }


    static class Product {
        private int weight;

        private int value;

        private String name;

        public Product(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }
}
