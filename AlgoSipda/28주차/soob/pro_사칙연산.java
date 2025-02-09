import java.util.*;

class Solution {
    public int solution(String[] arr) {
        int n = arr.length / 2 + 1;
        int[][] maxDP = new int[n][n];
        int[][] minDP = new int[n][n];

        int[] nums = new int[n];
        char[] ops = new char[n - 1];

        for (int i = 0, numIdx = 0, opIdx = 0; i < arr.length; i++) {
            if (i % 2 == 0) nums[numIdx++] = Integer.parseInt(arr[i]);
            else ops[opIdx++] = arr[i].charAt(0);
        }

        for (int i = 0; i < n; i++) {
            maxDP[i][i] = nums[i];
            minDP[i][i] = nums[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                maxDP[i][j] = Integer.MIN_VALUE;
                minDP[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    char op = ops[k];

                    int max1 = maxDP[i][k], max2 = maxDP[k + 1][j];
                    int min1 = minDP[i][k], min2 = minDP[k + 1][j];

                    int[] candidates = {
                        calculate(max1, max2, op),
                        calculate(max1, min2, op),
                        calculate(min1, max2, op),
                        calculate(min1, min2, op)
                    };

                    for (int res : candidates) {
                        maxDP[i][j] = Math.max(maxDP[i][j], res);
                        minDP[i][j] = Math.min(minDP[i][j], res);
                    }
                }
            }
        }

        return maxDP[0][n - 1];
    }

    private int calculate(int a, int b, char op) {
        return op == '+' ? a + b : a - b;
    }
}
