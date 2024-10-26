import java.io.*;
import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int maxT;
        int minT;
        int temp = 0;

        if(temperature > t2) {
            maxT = Math.abs(t1 - temperature);
            minT = Math.abs(t2 - temperature);
        } else {
            minT = Math.abs(t1 - temperature);
            maxT = Math.abs(t2 - temperature);
        }

        int[][] dp = new int[onboard.length][52];
        for(int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], 100 * 1000);
        }
        dp[0][0] = 0;
        for(int i = 1; i < onboard.length; i++) {
            for(int j = 0; j < 51; j++) {

                if(onboard[i] == 1 && (j > maxT || j < minT)) continue;

                if(0 <= j - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + a);
                }

                int min = dp[i - 1][j];
                if(0 != j) {
                    min += b;
                }
                dp[i][j] = Math.min(dp[i][j], min);

                if(j + 1 <= maxT) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + 1]);
                }
            }
        }

        int answer = 100 * 1000;
        for(int j = 0; j < 51; j++) {
            answer = Math.min(answer, dp[onboard.length - 1][j]);
        }
        return answer;
    }
}