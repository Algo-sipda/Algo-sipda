import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public static int solution(int n, int[] money) {
        Arrays.sort(money);
        long[] ways = new long[n + 1];

        for (int i = 0; i <= n; i += money[0]) {
            ways[i] = 1;
        }

        for (int i = 1; i < money.length; i++) {
            for (int j = money[i]; j <= n; j++) {
                ways[j] = (ways[j] + ways[j - money[i]]) % MOD;
            }
        }

        return (int) ways[n];
    }
}