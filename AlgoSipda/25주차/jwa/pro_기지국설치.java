class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        for (int i = 0; i <= stations.length; i++) {
            int dist = 0;

            if (i == 0) {
                dist = stations[i] - 1 - w;
            } else if (i == stations.length) {
                dist = n - stations[i - 1] - w;
            } else {
                dist = stations[i] - stations[i - 1] - (w * 2) - 1;
            }

            if (dist >= 0) {
                int count = dist / (w * 2 + 1);
                int remain = dist % (w * 2 + 1);

                answer += count;

                if (remain != 0)
                    answer++;
            }
        }
        return answer;
    }
}
