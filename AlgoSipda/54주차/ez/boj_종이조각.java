import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_종이조각 {

    static int N, M, res;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0);

        System.out.println(res);
    }

    private static void dfs(int row, int col) {
        if (row == N && col == 0) {
            res = Math.max(res, Math.max(calc1(), calc2()));
            return;
        }

        for (int i = row; i < N; i++) {
            for (int j = col; j < M; j++) {
                if (visited[i][j]) continue;
                visited[i][j] = true;
                dfs((j + 1 >= M) ? i + 1 : i, (j + 1) % M);
                visited[i][j] = false;
            }
        }
    }

    private static int calc1() {
        int num = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    num = num * 10 + map[i][j];
                }
                if (!visited[i][j] || j == M - 1) {
                    sum += num;
                    num = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[j][i]) {
                    num = num * 10 + map[j][i];
                }
                if (visited[j][i] || j == N - 1) {
                    sum += num;
                    num = 0;
                }
            }
        }

        return sum;
    }

    private static int calc2() {
        int num = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    num = num * 10 + map[i][j];
                }
                if (visited[i][j] || j == M - 1) {
                    sum += num;
                    num = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[j][i]) {
                    num = num * 10 + map[j][i];
                }
                if (!visited[j][i] || j == N - 1) {
                    sum += num;
                    num = 0;
                }
            }
        }

        return sum;
    }
}