import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 1;
        int right = distance;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int removeCount = 0;
            int prevPos = 0;

            for (int rock : rocks) {
                if (rock - prevPos < mid) {
                    removeCount++;
                } else {
                    prevPos = rock;
                }
            }

            if (distance - prevPos < mid) {
                removeCount++;
            }

            if (removeCount > n) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}