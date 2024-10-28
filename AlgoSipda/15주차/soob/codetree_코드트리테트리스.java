import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_N = 25;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n, m;
    static int[][] board = new int[MAX_N][MAX_N];
    static boolean[][] vis = new boolean[MAX_N][MAX_N];
    static List<int[]> v = new ArrayList<>();
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                v.add(new int[]{i, j});
                vis[i][j] = true;

                searchMax();

                v.remove(v.size() - 1);
                vis[i][j] = false;
            }
        }

        System.out.println(ans);
    }

    static boolean isOutrange(int x, int y) {
        return !(1 <= x && x <= n && 1 <= y && y <= m);
    }

    static void searchMax() {
        if (v.size() == 5) {
            int mx = 0;

            for (int i = 0; i < 2; i++) {
                int val = 0;
                for (int j = 0; j < 5; j++) {
                    if (v.get(i)[0] == v.get(j)[0]) val++;
                }
                mx = Math.max(mx, val);

                val = 0;
                for (int j = 0; j < 5; j++) {
                    if (v.get(i)[1] == v.get(j)[1]) val++;
                }
                mx = Math.max(mx, val);
            }

            if (mx >= 4) return;

            int sum = 0;
            for (int[] pos : v) {
                sum += board[pos[0]][pos[1]];
            }
            ans = Math.max(ans, sum);
            return;
        }

        int ed = v.size() * 4;
        for (int i = 0; i < ed; i++) {
            int x = v.get(i / 4)[0];
            int y = v.get(i / 4)[1];
            int dir = i % 4;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (isOutrange(nx, ny) || vis[nx][ny]) continue;

            vis[nx][ny] = true;
            v.add(new int[]{nx, ny});

            searchMax();

            vis[nx][ny] = false;
            v.remove(v.size() - 1);
        }
    }
}
