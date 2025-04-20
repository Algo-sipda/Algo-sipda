// https://school.programmers.co.kr/learn/courses/30/lessons/181186
// 이해 안됨
public class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;

        // dp 배열과 check 배열 선언
        int[] dp = new int[Math.max(n + 1, 4)];
        int[] check = new int[3];

        // 초기값 세팅
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        check[0] = 8;  // i % 3 == 0
        check[1] = 0;  // i % 3 == 1
        check[2] = 2;  // i % 3 == 2

        // dp 테이블 채우기
        for (int i = 4; i <= n; i++) {
            int temp = i % 3;

            dp[i] = (int) ((check[temp] + dp[i - 1] + 2L * dp[i - 2] + 5L * dp[i - 3] + (temp == 0 ? 4 : 2)) % MOD);

            long update = (2L * dp[i - 1] + 2L * dp[i - 2] + 4L * dp[i - 3]) % MOD;
            check[temp] = (int) ((check[temp] + update) % MOD);
        }

        return dp[n];
    }
}
