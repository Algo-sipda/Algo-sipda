import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int mod = 9901;

        int a = 1;
        int b = 1;
        int c = 1;

        for (int i = 2; i <= n; i++) {
            int na = (a + b + c) % mod;
            int nb = (a + c) % mod;
            int nc = (a + b) % mod;
            a = na;
            b = nb;
            c = nc;
        }

        int ans = (a + b + c) % mod;
        System.out.println(ans);
    }
}
