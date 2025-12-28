import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 수열 길이 n
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); // 수열 원소
        }

        answer = 0;
        dfs(0, n, 0);

        System.out.println(answer);
    }

    static void dfs(int l, int r, int sum) {
        int len = r - l;

        if (len == 1) {
            answer = Math.max(answer, sum + arr[l]);
            return;
        }

        int mid = l + len / 2;

        int leftGcd = rangeGcd(l, mid);
        dfs(mid, r, sum + leftGcd);

        int rightGcd = rangeGcd(mid, r);
        dfs(l, mid, sum + rightGcd);
    }

    static int rangeGcd(int l, int r) {
        int g = arr[l];
        for (int i = l + 1; i < r; i++) {
            g = gcd(g, arr[i]);
            if (g == 1) {
                break;
            }
        }
        return g;
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
