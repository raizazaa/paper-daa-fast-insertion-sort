package paper;

import java.util.Arrays;

public class FastInsertionSortNest<T extends Comparable<T>> implements SortingAlgo<T> {
    private static final int DEFAULT_H = 5;

    @Override
    public void sort(T[] arr, int n) {
        fastInsertionSortNest(arr, 0, n, DEFAULT_H);
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

    private void fastInsertionSortNest(T[] arr, int s, int n, int h) {
        if (n <= Math.pow(2, h - 1)) {
            h = (int) Math.floor(Math.log(n) / Math.log(2));
        }

        if (h == 0) {
            return;
        }

        double exp = (double) (h - 1) / h;
        int k = (int) Math.floor(Math.pow(n, exp));
        T[] t = Arrays.copyOf(arr, k);
        t[0] = arr[n - 1];

        for (int i = 0; i < n; i += k) {
            int b = Math.min(k, n - i);
            fastInsertionSortNest(arr, s + i, b, h - 1);
            insertBlock(arr, s, i, b, t);
        }
    }

    @Override
    public String name() {
        return "Fast Insertion Sort (Nest)";
    }
}
