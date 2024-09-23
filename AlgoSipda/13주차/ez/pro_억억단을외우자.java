import java.util.*;

class Solution {

    static int[] prime;
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[][] dp = new int[e + 1][2];
        prime = new int[e + 1];
        getCalc(e);
        int start = Integer.MAX_VALUE;
        for (int i = 0; i < starts.length; i++) {
            start = Math.min(start, starts[i]);
        }
        dp[e][0] = prime[e];
        dp[e][1] = e;

        for (int i = e - 1; i >= start; i--) {
            int pri = prime[i];
            if (dp[i + 1][0] <= pri) {
                dp[i][0] = pri;
                dp[i][1] = i;
            } else {
                dp[i][0] = dp[i + 1][0];
                dp[i][1] = dp[i + 1][1];
            }
        }

        for (int i = 0; i < starts.length; i++) {
            answer[i] = dp[starts[i]][1];
        }
        return answer;
    }

    private static void getCalc(int e) {
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e; j++) {
                if (i * j > e) break;
                prime[i * j]++;
            }
        }
    }
}