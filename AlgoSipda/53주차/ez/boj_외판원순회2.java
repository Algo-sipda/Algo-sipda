import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_외판원순회2 {

    static int N, min, start;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            start = i;
            backtracking(i, 0, 0);
        }

        System.out.println(min);
    }

    private static void backtracking(int cur, int cost, int cnt) {
        if (cnt == N) {
            if (cur == start) {
                min = Math.min(min, cost);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if (map[cur][i] == 0 || visited[i]) continue;
            visited[i] = true;
            backtracking(i, cost + map[cur][i], cnt + 1);
            visited[i] = false;
        }
    }
}
