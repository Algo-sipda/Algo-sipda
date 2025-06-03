class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]).turn;
    }

    private Result dfs(int[][] board, int ax, int ay, int bx, int by) {
        if (board[ax][ay] == 0) return new Result(false, 0);

        boolean canWin = false;
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;

        board[ax][ay] = 0;

        for (int d = 0; d < 4; d++) {
            int nx = ax + dx[d];
            int ny = ay + dy[d];
            if (!isValid(board, nx, ny)) continue;

            Result next = dfs(board, bx, by, nx, ny);
            if (!next.win) {
                canWin = true;
                minTurn = Math.min(minTurn, next.turn + 1);
            } else {
                maxTurn = Math.max(maxTurn, next.turn + 1);
            }
        }

        board[ax][ay] = 1;

        if (canWin) return new Result(true, minTurn);
        return new Result(false, maxTurn);
    }

    private boolean isValid(int[][] board, int x, int y) {
        return 0 <= x && x < board.length && 0 <= y && y < board[0].length && board[x][y] == 1;
    }

    class Result {
        boolean win;
        int turn;

        Result(boolean win, int turn) {
            this.win = win;
            this.turn = turn;
        }
    }
}