import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int attitude = scores[0][0];
        int peer = scores[0][1];
        int sum = attitude + peer;

        // 태도 내림차순, 평가 오름차순
        Arrays.sort(scores, (x, y) -> x[0] == y[0] ? x[1] - y[1] : y[0] - x[0]);

        int temp = 0;
        int answer = 1;

        for (int[] score : scores) {
            if (attitude < score[0] && peer < score[1]) {
                return -1; // -1
            }

            if (temp <= score[1]) {
                temp = score[1];

                if (score[0] + score[1] > sum) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
