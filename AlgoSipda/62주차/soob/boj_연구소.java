import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] a;
    static ArrayList<int[]> empties = new ArrayList<>();
    static ArrayList<int[]> viruses = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int simulateWithWalls(int[] w1, int[] w2, int[] w3) {
        int[][] g = new int[n][m];
        for (int x = 0; x < n; x++) {
            g[x] = a[x].clone();
        }
        g[w1[0]][w1[1]] = 1;
        g[w2[0]][w2[1]] = 1;
        g[w3[0]][w3[1]] = 1;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < viruses.size(); i++) {
            int[] v = viruses.get(i);
            q.add(new int[]{v[0], v[1]});
        }

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (g[nx][ny] != 0)
                    continue;
                g[nx][ny] = 2;
                q.add(new int[]{nx, ny});
            }
        }

        int cnt = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (g[x][y] == 0)
                    cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < m; y++) {
                a[x][y] = Integer.parseInt(st.nextToken());
                if (a[x][y] == 0) {
                    empties.add(new int[]{x, y});
                } else if (a[x][y] == 2) {
                    viruses.add(new int[]{x, y});
                }
            }
        }

        int e = empties.size();
        int ans = 0;
        for (int i = 0; i < e; i++) {
            for (int j = i + 1; j < e; j++) {
                for (int k = j + 1; k < e; k++) {
                    int cur = simulateWithWalls(empties.get(i), empties.get(j), empties.get(k));
                    if (cur > ans)
                        ans = cur;
                }
            }
        }
        System.out.println(ans);
    }
}
