import java.util.*;
class pro_징검다리건너기 {
    public int solution(int[] stones, int k) {
        int[] copy = stones.clone();
        Arrays.sort(copy);
        int left = 0;
        int right = copy[copy.length - 1];

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(stones, k, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean isPossible(int[] stones, int k, int mid) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            if (stones[i] - mid <= 0) {
                cnt++;
            } else {
                cnt = 0;
            }

            if (cnt >= k) {
                return false;
            }
        }
        return true;
    }
}