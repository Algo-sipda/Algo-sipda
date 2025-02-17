import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, maxSum;
    static int[][] board;
    static boolean[][] visited;
    static int maxValue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        maxValue = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxValue = Math.max(maxValue, board[i][j]);
            }
        }

        maxSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
                checkTShape(i, j);
            }
        }
        System.out.println(maxSum);
    }

    static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }
        // 남은 칸에 최대값을 곱해도 현재 최대 합보다 작으면 중단
        if (sum + (4 - depth) * maxValue <= maxSum) return;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, sum + board[nx][ny], depth + 1);
            visited[nx][ny] = false;
        }
    }

    // T자 모양 체크
    static void checkTShape(int x, int y) {
        int center = board[x][y];
        int sum = center;
        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            count++;
            sum += board[nx][ny];
            min = Math.min(min, board[nx][ny]);
        }

        if (count < 3) return;
        if (count == 4) sum -= min;
        maxSum = Math.max(maxSum, sum);
    }
}