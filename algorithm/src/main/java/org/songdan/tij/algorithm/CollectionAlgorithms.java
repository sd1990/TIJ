package org.songdan.tij.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合算法
 * Created by SongDan on 2016/12/25.
 */
public class CollectionAlgorithms {

    /**
     * 取两个集合的并集
     *
     * @param firstList  第一个集合
     * @param secondList 第二个集合
     * @param <E>        类型参数
     * @return 合并的结果
     */
    public static <E> List<E> union(List<? extends E> firstList, List<? extends E> secondList) {
        List<E> resultList = new ArrayList<>(firstList);
        if (isEmpty(secondList)) {
            return resultList;
        }
        for (E e : secondList) {
            if (!resultList.contains(e)) {
                resultList.add(e);
            }
        }
        return resultList;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <E> List<E> unionRecursion(List<? extends E> firstList, List<? extends E>... otherLists) {
        if (otherLists.length > 1) {
            return unionRecursion(union(firstList, otherLists[0]), excludeByIndex(otherLists, 0));
        }
        else {
            return union(firstList, otherLists[0]);
        }
    }

    public static <E> List<? extends E>[] excludeByIndex(List<? extends E>[] lists, int index) {
        List<? extends E>[] result = (List<? extends E>[]) new List[lists.length - 1];
        for (int i = 0; i < lists.length; i++) {
            if (i < index) {
                result[i] = lists[i];
            }
            else if (i < lists.length - 1) {
                result[i] = lists[i + 1];
            }
        }
        return result;
    }

    /**
     * 合并所有的排序集合，不去重，返回的也是已经排序过的集合
     *
     * @param firstList  第一个列表
     * @param secondList 第二个列表
     * @param <E>        类型参数
     * @return 合并过后的排序集合
     */
    public static <E extends Comparable<E>> List<E> unionAllSortedList(List<? extends E> firstList, List<? extends E> secondList) {
        List<E> resultList = new ArrayList<>();
        int i = 0, j = 0;
        for (; i < firstList.size() && j < secondList.size(); ) {
            E firstE = firstList.get(i);
            E secondE = secondList.get(j);
            if (firstE.compareTo(secondE) <= 0) {
                resultList.add(firstE);
                i++;
            }else {
                resultList.add(secondE);
                j++;
            }
        }
        while (i < firstList.size()) {
            resultList.add(firstList.get(i));
            i++;
        }
        while (j < secondList.size()) {
            resultList.add(secondList.get(j));
            j++;
        }
        return resultList;
    }

    /**
     * 去除交集
     */
    public static <T> void removeInterSection(Collection<T> firstList,Collection<T> secondList){
        Collection<T> insertSection = getInsertSection(firstList,secondList);
        firstList.removeAll(insertSection);
        secondList.removeAll(insertSection);
    }

    /**
     * 获取交集
     * @param firstList
     * @param secondList
     * @param <T>
     * @return
     */
    public static <T> Collection<T> getInsertSection(Collection<T> firstList, Collection<T> secondList) {
        List<T> insertSection = new ArrayList<>();
        for (T t : firstList) {
            if (secondList.contains(t)) {
                insertSection.add(t);
            }
        }
        return insertSection;
    }

}
