import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] area, dp;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static class Node {
        int x, y, bamboo;

        Node(int x, int y, int bamboo) {
            this.x = x;
            this.y = y;
            this.bamboo = bamboo;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        area = new int[N][N];
        dp = new int[N][N];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j);
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }

    static int dfs(int x, int y) {
        if (dp[x][y] != 0)
            return dp[x][y];

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;

            if (area[x][y] < area[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];
    }
}
