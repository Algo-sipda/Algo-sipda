import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int L;
    static int R;
    static int X;
    static int[] a;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        a = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (!st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, 0, 0);
        System.out.print(ans);
    }

    static void dfs(int idx, int sum, int mn, int mx, int cnt) {
        if (idx == N) {
            if (cnt >= 2 && sum >= L && sum <= R && mx - mn >= X)
                ans++;
            return;
        }

        dfs(idx + 1, sum, mn, mx, cnt);

        int v = a[idx];
        if (cnt == 0) {
            dfs(idx + 1, sum + v, v, v, 1);
        } else {
            int nmn = mn < v ? mn : v;
            int nmx = mx > v ? mx : v;
            dfs(idx + 1, sum + v, nmn, nmx, cnt + 1);
        }
    }
}
