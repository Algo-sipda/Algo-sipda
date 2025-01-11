class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        answer += getDistance(1, stations[0] - w - 1, w);
        for (int i = 1; i < stations.length; i++) {
            answer += getDistance(stations[i - 1] + w + 1, stations[i] - w - 1, w);
        }
        answer += getDistance(stations[stations.length - 1] + w + 1, n, w);

        return answer;
    }

    private int getDistance(int start, int end, int w) {
        int dis = end - start + 1;

        if (dis <= 0) return 0;
        if (dis % (2 * w + 1) == 0) return dis / (2 * w + 1);
        return dis / ((2 * w + 1)) + 1;
    }
}