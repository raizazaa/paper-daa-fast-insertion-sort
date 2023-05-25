package paper;

import java.util.Arrays;

public class TimSort<T extends Comparable<T>> implements SortingAlgo<T> {
    private static final int MIN_MERGE = 32;

    @Override
    public void sort(T[] arr, int n) {
        if (n < 2) {
            return;
        }

        int minRun = calculateMinRun(n);

        for (int i = 0; i < n; i += minRun) {
            int end = Math.min(i + minRun - 1, n - 1);
            insertionSort(arr, i, end);
        }

        int size = minRun;
        while (size < n) {
            for (int start = 0; start < n; start += 2 * size) {
                int mid = Math.min(start + size - 1, n - 1);
                int end = Math.min(start + 2 * size - 1, n - 1);
                merge(arr, start, mid, end);
            }
            size *= 2;
        }
    }

    private void insertionSort(T[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        T[] leftArray = Arrays.copyOfRange(arr, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < len1) {
            arr[k++] = leftArray[i++];
        }

        while (j < len2) {
            arr[k++] = rightArray[j++];
        }
    }

    private int calculateMinRun(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    @Override
    public String name() {
        return "Tim Sort";
    }
}
