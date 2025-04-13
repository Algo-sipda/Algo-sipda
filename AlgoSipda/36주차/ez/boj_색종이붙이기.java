import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_색종이붙이기 {
    static final int SIZE = 10;
    static int[] paper = { 5, 5, 5, 5, 5 };
    static int[][] map;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        res = Integer.MAX_VALUE;
        pastePaper(0, 0, 0);

        if (res == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    private static void pastePaper(int row, int col, int cnt) {
        if (row == SIZE && col == 0) {
            if (isComplete()) {
                res = Math.min(res, cnt);
            }
            return;
        }

        int nr = row;
        int nc = col + 1;
        if (nc == SIZE) {
            nr += 1;
            nc = 0;
        }

        if (map[row][col] == 1) {
            for (int i = 5; i > 0; i--) {
                if (!isInMap(row, col, i) || !canPaste(row, col, i) || paper[i - 1] == 0) {
                    continue;
                }
                paper[i - 1]--;
                paste(row, col, i, 0);
                pastePaper(nr, nc, cnt + 1);
                paper[i - 1]++;
                paste(row, col, i, 1);
            }
        } else {
            pastePaper(nr, nc, cnt);
        }

    }

    private static boolean isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInMap(int row, int col, int size) {
        if (row + size > SIZE || col + size > SIZE) {
            return false;
        }
        return true;
    }

    private static boolean canPaste(int row, int col, int size) {
        for (int i = row, rlen = row + size; i < rlen; i++) {
            for (int j = col, clen = col + size; j < clen; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void paste(int row, int col, int size, int color) {
        for (int i = row, rlen = row + size; i < rlen; i++) {
            for (int j = col, clen = col + size; j < clen; j++) {
                map[i][j] = color;
            }
        }
    }
}