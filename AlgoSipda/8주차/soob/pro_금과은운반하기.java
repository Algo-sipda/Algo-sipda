import java.util.*;

class Solution {

    public boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = g.length;
        long total = 0;
        long totalG = 0;
        long totalS = 0;

        for (int i = 0; i < n; i++) {
            long cnt = time / (2 * t[i]);
            if (time % (2 * t[i]) >= t[i])
                cnt++;

            long temp = Math.min(cnt * w[i], g[i] + s[i]);
            total += temp;
            totalG += Math.min(temp, g[i]);
            totalS += Math.min(temp, s[i]);
        }

        if (total >= a + b && totalG >= a && totalS >= b)
            return true;

        return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long max = (long) (10e9 * 2 * 10e5 * 2);
        long min = 0;

        while (min + 1 < max) {
            long mid = (min + max) / 2;

            if (isPossible(mid, a, b, g, s, w, t))
                max = mid;
            else
                min = mid;
        }
        return max;
    }
}