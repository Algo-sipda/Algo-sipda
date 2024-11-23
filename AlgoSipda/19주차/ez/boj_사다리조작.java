import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_사다리조작 {
    static int N, M, H, res;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 2][N + 2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        res = Integer.MAX_VALUE;

        if (solve()) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (combi(1, 0, i)) {
                return;
            }
        }
        System.out.println(-1);

    }

    private static boolean combi(int start, int cnt, int finish) {
        if (cnt == finish) {
            if (solve()) {
                System.out.println(finish);
                return true;
            }
            return false;
        }

        for (int i = start; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (map[i][j] == 1) continue;

                map[i][j] = 1;
                if (combi(i, cnt+1, finish)) {
                    return true;
                }
                map[i][j] = 0;
            }
        }
        return false;
    }

    private static boolean solve() {
        for (int i = 1; i <= N; i++) {
            int x = i;
            for (int j = 1; j <= H; j++) {
                if (map[j][x - 1] == 1) x--;
                else if (map[j][x] == 1) x++;
            }
            if (x != i) {
                return false;
            }
        }
        return true;
    }
}
