import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder out = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int[] dpReverse = new int[n + 1];
        Arrays.fill(dpReverse, 1);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if(arr[i] > arr[j] && dpReverse[j] >= dpReverse[i]) {
                    dpReverse[i] = dpReverse[j] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(dp[i] + dpReverse[i] - 1, result);
        }

        System.out.println(result);
    }
}
