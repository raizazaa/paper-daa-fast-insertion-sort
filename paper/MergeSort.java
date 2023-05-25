package paper;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> implements SortingAlgo<T> {

    @Override
    public void sort(T[] arr, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, mid, n);

        sort(left, mid);
        sort(right, n - mid);

        merge(arr, left, right, mid, n - mid);
    }

    private void merge(T[] arr, T[] left, T[] right, int leftSize, int rightSize) {
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = left[i++];
        }

        while (j < rightSize) {
            arr[k++] = right[j++];
        }
    }

    @Override
    public String name() {
        return "Merge Sort";
    }
}
