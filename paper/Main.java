package paper;

import java.util.Arrays;
import java.util.Random;


public class Main {

    private static final int NUM_RUNS = 100;

    private static final FastInsertionSortRec<Integer> SORT = new FastInsertionSortRec<>();

    public static void main(String[] args) {
        System.out.println(String.format("%s\nExecution Time (ns)", SORT.name()));
        for (int i = 1; i <= 20; i++) {
            int size = (int) Math.pow(2, i);

            long totalExecutionTime = 0;

            for (int j = 0; j < NUM_RUNS; j++) {
                Integer[] array = generateRandomArray(size);

                long startTime = System.nanoTime();

                SORT.sort(array, array.length);
                long endTime = System.nanoTime();

                long executionTime = endTime - startTime;
                totalExecutionTime += executionTime;


            }

            long averageExecutionTime = totalExecutionTime / NUM_RUNS;
            System.out.println(averageExecutionTime);
        }

    }

    public static Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

    public static Integer[] generateReversedSortedArray(int size) {
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }

        return array;
    }


}

