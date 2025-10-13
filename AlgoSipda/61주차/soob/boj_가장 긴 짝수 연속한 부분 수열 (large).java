import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] a;

    static void input(BufferedReader br) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        a = new int[N];
        int i = 0;
        st = new StringTokenizer(br.readLine());
        while (i < N) {
            if (!st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            else
                a[i++] = (Integer.parseInt(st.nextToken()) & 1);
        }
    }

    static int solve() {
        int ans = 0, left = 0, odd = 0;
        for (int right = 0; right < N; right++) {
            if (a[right] == 1)
                odd++;
            while (odd > K) {
                if (a[left] == 1)
                    odd--;
                left++;
            }
            ans = Math.max(ans, (right - left + 1) - odd);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input(br);
        System.out.println(solve());
    }
}
