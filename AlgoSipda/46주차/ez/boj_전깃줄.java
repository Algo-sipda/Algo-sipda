import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_전깃줄 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] dp = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, recur(arr, dp, i));
        }
        System.out.println(N - max);
    }

    private static int recur(int[][] arr, int[] dp, int idx) {
        if (dp[idx] == 0) {
            dp[idx] = 1;
            for (int i = idx + 1; i < dp.length; i++) {
                if (arr[idx][1] < arr[i][1]) {
                    dp[idx] = Math.max(dp[idx], recur(arr, dp, i) + 1);
                }
            }
        }
        return dp[idx];
    }
}
