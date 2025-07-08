import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_스도쿠 {

    static final int SIZE = 9;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            String input = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        dfs(0, 0);
    }

    private static boolean dfs(int row, int col) {
        if (row == SIZE - 1 && col == SIZE) {
            printMap();
            return true;
        }

        if (col == SIZE) {
            col = 0;
            row++;
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= SIZE; i++) {
                if (!isOk(row, col, i)) continue;
                map[row][col] = i;
                if (dfs(row, col + 1)) {
                    return true;
                }
                map[row][col] = 0;
            }
        } else {
            if (dfs(row, col + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOk(int row, int col, int num) {
        // 가로
        int width = 1 << num;

        for (int i = 0; i < SIZE; i++) {
            if (map[row][i] == 0) continue;
            if ((1 << map[row][i] & width) != 0) {
                return false;
            }
            width |= (1 << map[row][i]);
        }

        // 세로
        int height = 1 << num;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][col] == 0) continue;
            if ((1 << map[i][col] & height) != 0) {
                return false;
            }
            height |= (1 << map[i][col]);
        }

        // 3x3
        int square = 1 << num;
        int nr = (row / 3) * 3;
        int nc = (col / 3) * 3;
        for (int i = nr; i < nr + 3; i++) {
            for (int j = nc; j < nc + 3; j++) {
                if (map[i][j] == 0) continue;
                if ((1 << map[i][j] & square) != 0) {
                    return false;
                }
                square |= (1 << map[i][j]);
            }
        }

        return true;
    }

    private static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}