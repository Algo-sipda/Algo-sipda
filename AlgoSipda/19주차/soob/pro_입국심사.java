import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        Arrays.sort(times);
        long right = (long) n * times[times.length - 1];
        long mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;
            long sum = 0;

            for (int time : times) {
                sum += mid / time;
            }

            if (sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}