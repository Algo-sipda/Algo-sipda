import java.util.*;

class pro_징검다리 {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int left = 0;
        int right = distance;

        while (left <= right) {
            int mid = (left + right) / 2;

            int remove = 0;
            int before = 0;
            for (int i = 0; i < rocks.length; i++) {
                if (rocks[i] - before < mid) {
                    remove++;
                    continue;
                }
                before = rocks[i];
            }
            if (distance - before < mid) {
                remove++;
            }

            if (remove <= n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }
}