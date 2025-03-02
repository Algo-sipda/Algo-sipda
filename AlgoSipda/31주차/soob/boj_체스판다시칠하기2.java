import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];
        for (int y = 0; y < N; y++) {
            board[y] = br.readLine().toCharArray();
        }
        int[][] dp1 = new int[N + 2][M + 2];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                dp1[y + 1][x + 1] = dp1[y][x + 1] + dp1[y + 1][x] - dp1[y][x];
                if ((y + x) % 2 == 0) {
                    if (board[y][x] != 'B')
                        dp1[y + 1][x + 1]++;
                } else {
                    if (board[y][x] != 'W')
                        dp1[y + 1][x + 1]++;
                }
            }
        }

        int answer = K * K;
        for (int y = 0; y <= N - K; y++) {
            for (int x = 0; x <= M - K; x++) {
                int n1 = dp1[y + K][x + K] - dp1[y][x + K] - dp1[y + K][x] + dp1[y][x];
                n1 = Math.min(n1, K * K - n1);
                answer = Math.min(answer, n1);
            }
        }
        System.out.println(answer);
    }
}