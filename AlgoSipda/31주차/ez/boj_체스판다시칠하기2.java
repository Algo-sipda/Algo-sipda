import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_체스판다시칠하기2 {

    static int N, M, K;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;
        map = new char[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        int[][] black = prefixSum('B');
        int[][] white = prefixSum('W');

        for (int i = 1; i <= N - K + 1; i++) {
            for (int j = 1; j <= M - K + 1; j++) {
                ans = Math.min(ans, Math.min(black[i + K - 1][j + K - 1] - black[i + K - 1][j - 1] - black[i - 1][j + K - 1] + black[i - 1][j - 1],
                        white[i + K - 1][j + K - 1] - white[i + K - 1][j - 1] - white[i - 1][j + K - 1] + white[i - 1][j - 1]));
            }
        }
        System.out.println(ans);
    }

    private static int[][] prefixSum(char color) {
        int[][] sum = new int[N + 1][M + 1];

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                int value = 0;
                if ((i + j) % 2 == 0) {
                    if (map[i][j] == color) {
                        value = 0;
                    } else {
                        value = 1;
                    }
                } else {
                    if (map[i][j] == color) {
                        value = 1;
                    } else {
                        value = 0;
                    }
                }
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + value;
            }
        }
        return sum;
    }
}
