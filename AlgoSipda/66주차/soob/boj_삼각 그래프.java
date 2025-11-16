import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, -1};
    static int[] dy = {-1, -1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                System.out.println(stringBuilder);
                return;
            }
            int[][] arr = new int[N][3];
            for (int n = 0; n < N; n++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < 3; i++) {
                    arr[n][i] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[N][3];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], 10000000);
            }
            dp[0][1] = arr[0][1];

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < 3; x++) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= 3 || ny >= N)
                            continue;
                        dp[y][x] = Math.min(dp[y][x], dp[ny][nx] + arr[y][x]);
                    }
                }
            }

            stringBuilder.append(tc).append(". ").append(dp[N - 1][1]).append('\n');
            tc++;
        }
    }
}
