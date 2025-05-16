import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());   // 가게에 있는 사탕 종류의 수
            String str = st.nextToken().replace(".", "");
            int m = Integer.parseInt(str);   // 상근이가 가지고 있는 돈의 양

            if (n == 0 && m == 0)
                return;

            int[] dp = new int[m + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());   // 사탕의 칼로리
                str = st.nextToken().replace(".", "");
                int p = Integer.parseInt(str);   // 사탕의 가격

                for (int j = p; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j - p] + c);
                }
            }

            System.out.println(dp[m]);
        }
    }
}
