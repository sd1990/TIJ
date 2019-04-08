package org.songdan.tij.algorithm;

/**
 * 二分查找
 *
 * @author: Songdan
 * @create: 2019-04-07 17:44
 **/
public class BinaryChop {

    /**
     * 找到任意一个等于指定数字的元素的下标，如果找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int simpleSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 找到最后一个等于指定数字元素的下标，如果找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int searchFirst(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != target) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 找到第一个大于等于指定数字元素的下标，如果找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int searchFirstGe(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                if (mid == 0 || arr[mid - 1] < target) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 找到最后一个等于指定数字元素的下标，如果找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int searchLast(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 找到最后一个小于等于指定数字元素的下标，如果找不到返回-1
     *
     * @param arr
     * @param target
     * @return
     */
    public static int searchLastLe(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] <= target) {
                if (mid == arr.length - 1 || arr[mid + 1] > target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,3,6,8,9};
        System.out.println(searchFirst(arr,2));
        System.out.println(searchLast(arr,2));
    }


}
