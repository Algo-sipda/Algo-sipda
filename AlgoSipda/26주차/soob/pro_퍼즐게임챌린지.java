import java.util.*;

class Solution {    
    public int solution(int[] diffs, int[] times, long limit) {
        return binarySearch(diffs, times, limit);
    }

    private int binarySearch(int[] diffs, int[] times, long limit) {
        int low = 1, high = 100000;
        while (low <= high) {
            int mid = (low + high) / 2;
            long totalTime = calculateTime(diffs, times, mid);
            if (totalTime > limit) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private long calculateTime(int[] diffs, int[] times, int level) {
        long total = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                total += times[i] + (long) (times[i] + times[Math.max(i - 1, 0)]) * (diffs[i] - level);
            }
        }
        return total;
    }
}
