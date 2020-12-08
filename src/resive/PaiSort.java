package resive;

import java.util.Arrays;

/**
 * @author Duanjianhui
 * @create 2020-10-26 11:31
 */
public class PaiSort {
    public static void main(String[] args) {
        int[] arr = {23, 43, 1, 5, 3, 78, 6};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        /*int[] result1 = PaiSort.zhejeiSort(arr);
        System.out.println("直接排序" + Arrays.toString(result1));
        int index = PaiSort.erfSort(result1, 23);
        System.out.println("二分法,索引为" + index);*/

       /* int[] result2=PaiSort.maopaiSort(arr);
        System.out.println("冒泡排序"+ Arrays.toString(result2));*/

/*

      /*  quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));*/
    }

    //直接排序,第一个与后面的阻隔比较，又叫选择排序
    public static int[] zhejeiSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    //冒泡排序，两个相邻的元素进行比较,nice
    public static int[] maopaiSort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    //二分法
    public static int erfSort(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;
        int mid = (min + max) / 2;
        while (true) {
            if (target > arr[mid]) {
                min = mid + 1;
            } else if (target < arr[mid]) {
                max = mid - 1;
            } else {
                return mid;
            }
            mid = (min + max) / 2;

            if (max < min) {
                return -1;
            }
        }
    }

    /**
     * 功能描述：快速排序
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static void quickSort(int[] arr, int left, int right) {
        //先判断数组是否为空
        if (arr == null || arr.length == 0) {
            return;
        }
        //当左指针>右指针，要结束
        if (left > right) {
            return;
        }
        //定义一个key值来当做中间值,基准数
        int key = arr[left];
        int l = left;
        int r = right;
        //移动指针
        while (l != r) {
            //先检索右边,如果检索的基准数小的元素，则停下
            //如果检索到比基准数大的则继续检索
            while (arr[r] > key && l < r) {
                r--;
            }
            //从左边检索，如果检索带比基准数小的
            while (arr[l] <= key && l < r) {
                l++;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        //如果l=r时，将基准数与相遇的时的元素的位置进行互换
        arr[left] = arr[l];
        arr[l] = key;
        //排序基准数左边
        quickSort(arr, left, l - 1);
        //牌右边
        quickSort(arr, r + 1, right);
    }

    //直接插入排序
    public static void insertSort(int[] arr) {
        //外层循环,从第二个元素开始
        for (int i = 1; i < arr.length; i++) {
            //待比较数值
            int temp = arr[i];
            int j = i - 1;
            //内层循环为待比较数值确定其最终位置,待比较数值比前一位置小，应插往前插一位
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j+1]=arr[j];
            }
            //待比较数值比前一位置大，最终位置无误
            arr[j+1]=temp;
        }
    }

}
