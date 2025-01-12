class Solution {
    public int solution(int[][] board) {
        int answer = 1;

        int r = board.length;
        int c = board[0].length;

        int zeroCnt = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 1)
                    break;
                zeroCnt++;
            }
        }

        if (zeroCnt == r * c)
            return 0;

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (board[i][j] == 0)
                    continue;
                board[i][j] =
                        Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1])
                                + 1;
                if (board[i][j] > answer)
                    answer = board[i][j];
            }
        }

        answer = answer * answer;

        return answer;
    }
}
