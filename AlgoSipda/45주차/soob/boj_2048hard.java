import java.io.*;
import java.util.*;

public class Main {
    static int N, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
            }
        }

        dfs(0, board);
        System.out.println(max);
    }

    static void dfs(int depth, int[][] board) {
        if (depth == 10) return;

        for (int dir = 0; dir < 4; dir++) {
            int[][] moved = move(board, dir);
            if (!isSame(board, moved)) {
                dfs(depth + 1, moved);
            }
        }
    }

    static int[][] move(int[][] map, int dir) {
        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            int[] line = new int[N];
            int idx = 0;
            int last = 0;

            for (int j = 0; j < N; j++) {
                int val = 0;
                if (dir == 0) val = map[j][i]; // up
                if (dir == 1) val = map[N - 1 - j][i]; // down
                if (dir == 2) val = map[i][j]; // left
                if (dir == 3) val = map[i][N - 1 - j]; // right

                if (val == 0) continue;

                if (last == val) {
                    line[idx - 1] *= 2;
                    max = Math.max(max, line[idx - 1]);
                    last = 0;
                } else {
                    line[idx++] = val;
                    last = val;
                }
            }

            for (int j = 0; j < N; j++) {
                int val = line[j];
                if (dir == 0) newMap[j][i] = val;
                if (dir == 1) newMap[N - 1 - j][i] = val;
                if (dir == 2) newMap[i][j] = val;
                if (dir == 3) newMap[i][N - 1 - j] = val;
            }
        }

        return newMap;
    }

    static boolean isSame(int[][] a, int[][] b) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] != b[i][j]) return false;
        return true;
    }
}
