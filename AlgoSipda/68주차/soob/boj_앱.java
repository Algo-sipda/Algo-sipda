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
        int[] memoryArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memoryArr[i] = Integer.parseInt(st.nextToken());
        }
        int[] costArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N * 100];
        int answer = N * 100;
        for (int i = 0; i < N; i++) {
            int memory = memoryArr[i];
            int cost = costArr[i];
            for (int j = N * 100 - 1; j >= cost; j--) {
                dp[j] = Math.max(dp[j - cost] + memory, dp[j]);
                if (dp[j] >= M)
                    answer = Math.min(answer, j);
            }
        }

        System.out.println(answer);
    }
}