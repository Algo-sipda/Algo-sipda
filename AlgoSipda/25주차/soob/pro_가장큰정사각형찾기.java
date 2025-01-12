class Solution
{
    public int solution(int [][]board)
    {
                int h = board.length;       // 세로 길이
        int w = board[0].length;    // 가로 길이
        int[][] dp = new int[h][w];
        int maxSize = 0;            // 최대 정사각형 변의 길이

        // DP 초기화 및 계산
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (board[y][x] == 1) {
                    if (y > 0 && x > 0) {
                        dp[y][x] = Math.min(Math.min(dp[y - 1][x], dp[y][x - 1]), dp[y - 1][x - 1]) + 1;
                    } else {
                        dp[y][x] = 1; // 경계 값 초기화
                    }
                    maxSize = Math.max(maxSize, dp[y][x]); // 최대 크기 갱신
                }
            }
        }

        // 최대 정사각형의 넓이 반환
        return maxSize * maxSize;
    }
}