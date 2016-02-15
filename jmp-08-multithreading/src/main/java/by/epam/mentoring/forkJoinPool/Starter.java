package by.epam.mentoring.forkJoinPool;

import java.util.concurrent.ForkJoinPool;

public class Starter {

    public static final int ARR_SIZE = 10000000;

    public static void main(String[] args) {
        int[] arr = new int[ARR_SIZE];
        Long sum = ForkJoinPool.commonPool().invoke(new ArraySum(arr));
        System.out.println(String.format("Sum of arr: %d", sum));
    }
}
