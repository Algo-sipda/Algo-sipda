import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_넴모넴모 {

    static int N, M, res;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];

        dfs(0, 0);

        System.out.println(res);
    }

    private static void dfs(int cnt, int start) {
        if (isNem(cnt)) {
            res += 1;
        }

        if (cnt == N * M) {
            return;
        }

        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if (visited[r][c]) continue;
            visited[r][c] = true;
            dfs(cnt + 1, i + 1);
            visited[r][c] = false;
        }
    }

    private static boolean isNem(int cnt) {
        if (cnt < 4) {
            return true;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (visited[i][j] && visited[i][j + 1] && visited[i + 1][j] && visited[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
