class Solution {
    public long solution(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[] memo = new long[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;

        return pibo(n, memo);
    }

    private long pibo(int n, long[] memo) {
        if (memo[n] != 0) {
            return memo[n];
        }

        memo[n] = (pibo(n - 1, memo) + pibo(n - 2, memo)) % 1234567;
        return memo[n];
    }
}