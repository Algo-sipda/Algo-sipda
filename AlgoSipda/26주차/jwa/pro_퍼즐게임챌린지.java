class Solution {
    static int MAX_DIFF = 100_000;

    public int solution(int[] diffs, int[] times, long limit) {
        int n = diffs.length;
        int left = 1, right = MAX_DIFF;

        while (left <= right) {
            int level = (left + right) / 2;
            long total = times[0];

            for (int i = 1; i < n; i++) {
                if (diffs[i] <= level) {
                    total += times[i];
                } else {
                    total += (times[i] + times[i - 1]) * (diffs[i] - level) + times[i];
                }
            }

            if (total <= limit) {
                right = level - 1;
            } else {
                left = level + 1;
            }
        }

        return left;
    }
}
