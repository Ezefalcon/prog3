package TP1.e6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int [] list1 = new int[]{1,2,3,12,25,37,58,67,72,74,75};
        int [] list2 = new int[]{1,13,14,16,18,25,37,58,67,72,74,75};
        List<Integer> commonNumbers = new ArrayList<>();
        // A
        // With one list sorted the numbers will be sorted
        Arrays.sort(list1);

        // A & B
        for(int i = 0; i < list2.length; i++) {
            int found = binarySearchB(list1, list2[i], 0, list1.length -1);
            if(found >= 0) {
                commonNumbers.add(list1[found]);
            }
        }
        System.out.println("A result " + commonNumbers);
    }

    /**
     * This method will search the number in the arr param
     * Only works for ordered lists
     * @return will return the indexOf the number or -1 if it wasn't found
     */
    public static int binarySearchB(int[] arr, int number, int start, int end) {
        // If the end is lesser than the start number the element wasn't found
        if(end < start) {
            return -1;
        }
        // This will
        int half = (start + end) / 2;
        if(number > arr[half]) {
            return binarySearchB(arr, number, half+1, end);
        } else if(number < arr[half]) {
            return binarySearchB(arr, number, start, half-1);
        } else {
            return half;
        }

    }
}
