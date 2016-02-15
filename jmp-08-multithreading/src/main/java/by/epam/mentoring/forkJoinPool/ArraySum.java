package by.epam.mentoring.forkJoinPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ArraySum extends RecursiveTask<Long> {
    private static final long serialVersionUID = 1L;
    private int[] list;
    List<ArraySum> taskList;

    public ArraySum() {
    }

    public ArraySum(int[] list) {
        this.taskList = new ArrayList<>();
        this.list = list;
    }

    @Override
    protected Long compute() {
        int sum = 0;
        if (list.length <= 100) {
            for (int i : list) {
                sum += i;
            }
        } else {
            int midpoint = list.length / 2;
            int[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            int[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
            ArraySum st1 = new ArraySum(l1);
            st1.fork();
            ArraySum st2 = new ArraySum(l2);
            st2.fork();
            taskList.add(st1);
            taskList.add(st2);
        }
        return addResultFromSubTask(sum, taskList);
    }

    private Long addResultFromSubTask(int sum, List<ArraySum> taskList) {
        return taskList.stream().mapToLong(ForkJoinTask::join).sum() + sum;
    }
}
