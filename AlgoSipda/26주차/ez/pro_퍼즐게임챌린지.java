import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;

        int left = 0;
        int right = Arrays.stream(diffs).max().getAsInt();
        while (left <= right) {
            int level = (left + right) / 2;
            if (level == 0) break;
            if (isSolved(diffs, times, limit, level)) {
                if (answer > level) {
                    answer = level;
                    right = level;
                } else {
                    break;
                }
            } else {
                left = level + 1;
            }
        }
        return answer;
    }

    private boolean isSolved(int[] diffs, int[] times, long limit, int level) {
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                limit -= times[i];
            } else {
                long wrong = (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
                limit -= wrong;
            }
            if (limit < 0) {
                return false;
            }
        }
        return true;
    }
}