package paper;

public interface SortingAlgo<T extends Comparable<T>>{
    void sort(T[] arr, int n);
    String name();
}
