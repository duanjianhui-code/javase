package resive;

import java.util.Arrays;

/**
 * @author Duanjianhui
 * @create 2020-10-26 11:31
 */
public class PaiSort {
    public static void main(String[] args) {
        int[] arr={23,43,1,5,3,78,6};

        int[] result1=PaiSort.zhejeiSort(arr);
        System.out.println("直接排序"+Arrays.toString(result1));

       /* int[] result2=PaiSort.maopaiSort(arr);
        System.out.println("冒泡排序"+Arrays.toString(result2));
        */

        int index=PaiSort.erfSort(result1,23);
        System.out.println("二分法"+index);

    }
    //直接排序,第一个与后面的阻隔比较
    public static int[] zhejeiSort(int[] arr){
        int temp=0;
        for(int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }
    //冒泡排序
    public static int[] maopaiSort(int[] arr){
        int temp=0;
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]<arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }
    //二分法
    public static int erfSort(int[] arr,int target){
        int min=0;
        int max=arr.length-1;
        int mid=(min+max)/2;
        while (true){
            if (target>arr[mid]){
                min=mid+1;
            }else if (target<arr[mid]){
                max=mid-1;
            }else {
                return mid;
            }
            mid=(min+max)/2;

            if (max<min){
                return -1;
            }
        }
    }
}
