import java.io.*;
import java.util.*;

public class Main
{
    static int n, m, k;
    static int[][] a;
    static boolean[][][] v;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행의 개수
        m = Integer.parseInt(st.nextToken()); // 열의 개수
        k = Integer.parseInt(st.nextToken()); // 부술 수 있는 벽의 개수
        a = new int[n][m];
        for (int i = 0; i < n; i++)
        {
            String s = br.readLine();
            for (int j = 0; j < m; j++) a[i][j] = s.charAt(j) - '0';
        }
        System.out.println(bfs());
    }

    static int bfs()
    {
        v = new boolean[n][m][k + 1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0, 1});
        v[0][0][0] = true;
        while (!q.isEmpty())
        {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], w = cur[2], d = cur[3];
            if (x == n - 1 && y == m - 1) return d;
            for (int dir = 0; dir < 4; dir++)
            {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (a[nx][ny] == 0)
                {
                    if (!v[nx][ny][w])
                    {
                        v[nx][ny][w] = true;
                        q.add(new int[]{nx, ny, w, d + 1});
                    }
                }
                else
                {
                    if (w < k && !v[nx][ny][w + 1])
                    {
                        v[nx][ny][w + 1] = true;
                        q.add(new int[]{nx, ny, w + 1, d + 1});
                    }
                }
            }
        }
        return -1;
    }
}
