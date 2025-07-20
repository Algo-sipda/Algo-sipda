import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][M][3];
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
        }

        for (int x = 0; x < M; x++) {
            for (int d = 0; d < 3; d++) {
                dp[0][x][d] = map[0][x];
            }
        }

        for (int y = 1; y < N; y++) {
            for (int x = 0; x < M; x++) {
                for (int d = 0; d < 3; d++) {
                    int prevX = x + (d - 1);
                    if (prevX < 0 || prevX >= M) continue;
                    for (int pd = 0; pd < 3; pd++) {
                        if (pd == d) continue;
                        if (dp[y - 1][prevX][pd] != Integer.MAX_VALUE) {
                            dp[y][x][d] = Math.min(dp[y][x][d], dp[y - 1][prevX][pd] + map[y][x]);
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int x = 0; x < M; x++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[N - 1][x][d]);
            }
        }

        System.out.println(answer);
    }
}
