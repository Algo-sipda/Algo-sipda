class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, x, y, c)) {
                            board[x][y] = c;
                            if (backtrack(board)) return true;
                            board[x][y] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int x, int y, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == c || board[i][y] == c) return false;
            int boxX = 3 * (x / 3) + i / 3;
            int boxY = 3 * (y / 3) + i % 3;
            if (board[boxX][boxY] == c) return false;
        }
        return true;
    }
}