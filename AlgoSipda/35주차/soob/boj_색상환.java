import java.util.Scanner;

public class Main {

    static final int MOD = 1_000_000_003;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        if (k == 0) {
            System.out.println(1);
            return;
        }

        if (n < 2 * k) {
            System.out.println(0);
            return;
        }

        int[][] dp1 = new int[n + 1][k + 1];
        int[][] dp2 = new int[n + 1][k + 1];

        dp1[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp1[i][j] = dp1[i - 1][j] % MOD;
                if (i > 2) {
                    dp1[i][j] = (dp1[i][j] + dp1[i - 2][j - 1]) % MOD;
                }
            }
        }

        dp2[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp2[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp2[i][j] = dp2[i - 1][j];
                if (i > 1) {
                    dp2[i][j] = (dp2[i][j] + dp2[i - 2][j - 1]) % MOD;
                }
            }
        }

        int result = (dp1[n - 1][k] + dp2[n][k]) % MOD;
        System.out.println(result);
    }
}
