package TP2.e1;

public class SortedArray {
    public static boolean isSorted(int [] array, int currentPosition) {
        if(currentPosition < array.length - 2) {
            if(array[currentPosition] > array[currentPosition + 1]) {
                return false;
            } else {
                return isSorted(array, currentPosition+1);
            }
        }
        return true;
    }

    public static boolean isSorted(int [] arr) {
        return isSorted(arr,0);
    }

    public static int findRecursively(int[] arr, int number, int currentPosition) {
        if(currentPosition < arr.length) {
            if(arr[currentPosition] != number) {
                return findRecursively(arr, number, currentPosition+1);
            } else {
                return currentPosition;
            }
        }
        return -1;
    }

    public static int binarySearch(int [] arr, int number, int start, int end) {
        if(end < start) {
            return -1;
        }
        int half = (start + end) / 2;
        if(number > arr[half]) {
            return binarySearch(arr, number, half+1, end);
        } else if(number < arr[half]) {
            return binarySearch(arr, number, start, half-1);
        } else {
            return half;
        }
    }

    public static int[] bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i; j < arr.length - 1;j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j+1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static int[] selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for(int j = i+1; j < arr.length;j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static void swap(int arr[], int pos) {
        int temp = arr[pos+1];
        arr[pos + 1] = arr[pos];
        arr[pos] = temp;
    }

    public static void main(String[] args) {
        int arr [] = {1,2,34,4,54,2,2,2};
        int arr2 [] = {1,2,3,4,5,56,575,5555};
        System.out.println(isSorted(arr));
        System.out.println(isSorted(arr2));
        selectionSort(arr);
        printArray(arr);
        System.out.println(findRecursively(arr, 2, 0));
    }
}
