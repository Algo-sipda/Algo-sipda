import java.util.*;

class Solution {
    public static int solution(int n, int[] cores) {
    int min = 0;
    int max = 10000 * n;
    int time = 0;
    int remainingTasks = 0;

    while (min <= max) {
        int mid = (min + max) / 2;
        int processedTasks = calculate(mid, cores);

        if (processedTasks >= n) {
            max = mid - 1;
            time = mid;
            remainingTasks = processedTasks - n;
        } else {
            min = mid + 1;
        }
    }

    for (int i = cores.length - 1; i >= 0; i--) {
        if (time % cores[i] == 0) {
            if (remainingTasks == 0) {
                return i + 1;
            }
            remainingTasks--;
        }
    }

    return -1;
}

private static int calculate(int time, int[] cores) {
    if (time == 0) return cores.length;

    int totalTasks = cores.length;
    for (int coreTime : cores) {
        totalTasks += (time / coreTime);
    }
    return totalTasks;
}
}