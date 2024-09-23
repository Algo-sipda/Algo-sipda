import java.util.*;
import java.io.*;

public class Main {

    static int R, C, res;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0);

        System.out.println(res);
    }

    private static void dfs(int row, int col) {
        if (row == R - 1 && col == C - 1) {
            res++;
            return;
        }

        for (int i = row + 1; i < R; i++) {
            for (int j = col + 1; j < C; j++) {
                if (map[row][col] != map[i][j]) {
                    dfs(i, j);
                }
            }
        }
    }
}