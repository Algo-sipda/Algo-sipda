import java.io.*;
import java.util.*;

public class Main {
    static int N = 10, answer;
    static int[] count;
    static int[][] paper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = new int[] {0, 5, 5, 5, 5, 5};
        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        dfs(0, 0, 0);

        if (answer == Integer.MAX_VALUE)
            answer = -1;
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int cnt) {
        if (x == N) {
            answer = Math.min(answer, cnt);
            return;
        }
        if (cnt > answer)
            return;
        if (y == N) {
            dfs(x + 1, 0, cnt);
            return;
        }
        if (paper[x][y] == 1) {
            for (int k = 5; k > 0; k--) {
                if (count[k] == 0 || !checkSquare(x, y, k))
                    continue;
                attach(x, y, k);
                count[k]--;
                dfs(x, y + 1, cnt + 1);
                detach(x, y, k);
                count[k]++;
            }
        } else {
            dfs(x, y + 1, cnt);
        }

    }

    private static boolean checkSquare(int x, int y, int k) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                if (i < 0 || i >= N || j < 0 || j >= N)
                    return false;
                if (paper[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    private static void attach(int x, int y, int k) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                paper[i][j] = 0;
            }
        }
    }

    private static void detach(int x, int y, int k) {
        for (int i = x; i < x + k; i++) {
            for (int j = y; j < y + k; j++) {
                paper[i][j] = 1;
            }
        }
    }

}
