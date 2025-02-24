import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] volumes = new int[N];
        for (int i = 0; i < N; i++) {
            volumes[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        for (int i = 0; i < N; i++) {
            for (int v = 0; v <= M; v++) {
                if (dp[i][v]) {
                    if (v + volumes[i] <= M)
                        dp[i + 1][v + volumes[i]] = true;
                    if (v - volumes[i] >= 0)
                        dp[i + 1][v - volumes[i]] = true;
                }
            }
        }

        int maxVolume = -1;
        for (int v = 0; v <= M; v++) {
            if (dp[N][v]) 
                maxVolume = v;
        }

        System.out.println(maxVolume);
    }
}