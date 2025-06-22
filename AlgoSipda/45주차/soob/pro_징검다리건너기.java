class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int people) {
        int count = 0;
        for (int stone : stones) {
            if (stone - people < 0) count++;
            else count = 0;

            if (count >= k) return false;
        }
        return true;
    }
}
