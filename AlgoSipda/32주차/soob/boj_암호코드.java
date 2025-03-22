import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();

        int len = n.length();
        int[] dp = new int[len + 1];
        final int MOD = 1000000;

        if (n.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= len; i++) {
            int oneDigit = n.charAt(i - 1) - '0';
            int twoDigits = (n.charAt(i - 2) - '0') * 10 + oneDigit;

            if (oneDigit >= 1) {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        System.out.println(dp[len]);
    }
}
