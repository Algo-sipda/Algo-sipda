import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class boj_미친로봇 {

    static int N;
    static double ans;
    static double[] dir;
    static boolean[][] visited;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dir = new double[4];
        for (int i = 0; i < 4; i++) {
            dir[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        visited = new boolean[31][31];
        visited[15][15] = true;
        dfs(0, 1, 15, 15);

        BigDecimal dec = new BigDecimal(ans);

        System.out.println(dec.toString());
    }

    private static void dfs(int cnt, double per, int r, int c) {
        if (cnt == N) {
            ans += per;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOut(nr, nc) || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(cnt + 1, per * dir[i], nr, nc);
            visited[nr][nc] = false;
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= 31 || nc >= 31;
    }
}
