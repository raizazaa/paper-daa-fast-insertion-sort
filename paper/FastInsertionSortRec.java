package paper;

import java.util.Arrays;

public class FastInsertionSortRec<T extends Comparable<T>> implements SortingAlgo<T> {
    private static final int DEFAULT_C = 5;

    @Override
    public void sort(T[] arr, int n) {
        fastInsertionSortRec(arr, 0, n, DEFAULT_C);
    }

    private void insertionSort(T[] arr, int s, int n) {
        for (int i = s + 1; i < s + n; i++) {
            T key = arr[i];
            int j = i - 1;

            while (j >= s && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private void insertBlock(T[] arr, int s, int i, int k, T[] t) {
        for (int j = 0; j < k; j++) {
            t[j] = arr[s + i + j];
        }

        int l = k - 1;
        int j = i - 1;
        while (l >= 0) {
            while (j >= 0 && arr[s + j].compareTo(t[l]) > 0) {
                arr[s + j + l + 1] = arr[s + j];
                j--;
            }
            arr[s + j + l + 1] = t[l];
            l--;
        }
    }

    private void fastInsertionSortRec(T[] arr, int s, int n, int c) {
        int h = (int) Math.floor(Math.log(n) / Math.log(c));
        double exp = (double) (h - 1) / h;
        int k = (int) Math.floor(Math.pow(n, exp));

        if (n <= k || k <= 5 || h <= 1) {
            insertionSort(arr, s, n);
            return;
        }

        T[] t = Arrays.copyOf(arr, k);
        t[0] = arr[n - 1];

        for (int i = 0; i < n; i += k) {
            int b = Math.min(k, n - i);
            fastInsertionSortRec(arr, s + i, b, c);
            insertBlock(arr, s, i, b, t);
        }
    }
    @Override
    public String name() {
        return "Fast Insertion Sort (Rec)";
    }
}
