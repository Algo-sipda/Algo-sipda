
/*
다시 풀어보기
*/

class pro_최적의행렬곱셈 {

    static int[][] matrix, dp;

    public int solution(int[][] matrix_sizes) {
        dp = new int[matrix_sizes.length + 1][matrix_sizes.length + 1];
        matrix = matrix_sizes;
        return recur(0, matrix_sizes.length);
    }

    private int recur(int start, int end) {
        if (end - start == 1) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int i = start + 1; i < end; i++) {
            int left = find(start, i);
            int right = find(i, end);
            int cur = matrix[start][0] * matrix[i][0] * matrix[end - 1][1];
            min = Math.min(min, left + right + cur);
        }
        return min;
    }

    private int find(int start, int end) {
        if (dp[start][end] == 0) {
            dp[start][end] = recur(start, end);
        }
        return dp[start][end];
    }
}