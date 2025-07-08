import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];
    static ArrayList<int[]> emptyList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
                if (board[i][j] == 0) emptyList.add(new int[]{i, j});
            }
        }
        solve(0);
    }

    static void solve(int idx) {
        if (idx == emptyList.size()) {
            printBoard();
            System.exit(0);
        }

        int[] pos = emptyList.get(idx);
        int x = pos[0], y = pos[1];

        for (int i = 1; i <= 9; i++) {
            if (isValid(x, y, i)) {
                board[x][y] = i;
                solve(idx + 1);
                board[x][y] = 0;
            }
        }
    }

    static boolean isValid(int x, int y, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == val || board[i][y] == val) return false;
        }

        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;

        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == val) return false;
            }
        }
        return true;
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int val : row) sb.append(val);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
