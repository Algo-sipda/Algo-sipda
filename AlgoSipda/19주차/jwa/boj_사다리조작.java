import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h, answer;
    static int[][] ladder;
    static boolean finish = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        ladder = new int[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder[x][y] = 1;
            ladder[x][y + 1] = 2;
        }

        for (int i = 0; i < 4; i++) {
            answer = i;
            dfs(1, 0);
            if (finish)
                break;
        }

        System.out.println(finish ? answer : -1);
    }

    private static void dfs(int x, int count) {
        if (finish)
            return;

        if (answer == count) {
            if (check())
                finish = true;
            return;
        }
        for (int i = x; i < h + 1; i++) {
            for (int j = 1; j < n; j++) {
                if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
                    ladder[i][j] = 1;
                    ladder[i][j + 1] = 2;
                    dfs(i, count + 1);
                    ladder[i][j] = 0;
                    ladder[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= n; i++) {
            int x = 1, y = i;
            for (int j = 0; j < h; j++) {
                if (ladder[x][y] == 1)
                    y++;
                else if (ladder[x][y] == 2)
                    y--;
                x++;
            }
            if (y != i)
                return false;
        }
        return true;
    }
}
