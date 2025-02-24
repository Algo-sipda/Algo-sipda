
import java.io.*;
import java.util.*;

public class Main {
    static int N, S, M;
    static boolean[][] dp;
    static int[] volume;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new boolean[N][M + 1];
        volume = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }

        if (S - volume[0] >= 0) {
            dp[0][S - volume[0]] = true;
        }

        if (S + volume[0] <= M) {
            dp[0][S + volume[0]] = true;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i][j]) {
                    if (j - volume[i + 1] >= 0) {
                        dp[i + 1][j - volume[i + 1]] = true;
                    }

                    if (j + volume[i + 1] <= M) {
                        dp[i + 1][j + volume[i + 1]] = true;
                    }
                }
            }
        }

        int answer = -1;

        for (int i = 0; i <= M; i++) {
            if (dp[N - 1][i]) {
                answer = Math.max(answer, i);
            }
        }

        System.out.println(answer);
    }

}
