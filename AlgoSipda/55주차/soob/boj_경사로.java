import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    static int nextInt() throws IOException { return Integer.parseInt(next()); }

    static int N, L;
    static int[][] a;

    static boolean ok(int[] v) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int d = v[i + 1] - v[i];
            if (d == 0) continue;
            if (Math.abs(d) > 1) return false;
            if (d == 1) {
                for (int k = 0; k < L; k++) {
                    int idx = i - k;
                    if (idx < 0) return false;
                    if (v[idx] != v[i]) return false;
                    if (used[idx]) return false;
                    used[idx] = true;
                }
            } else {
                for (int k = 1; k <= L; k++) {
                    int idx = i + k;
                    if (idx >= N) return false;
                    if (v[idx] != v[i + 1]) return false;
                    if (used[idx]) return false;
                    used[idx] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = nextInt();
        L = nextInt();
        a = new int[N][N];
        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                a[x][y] = nextInt();

        int ans = 0;
        int[] v = new int[N];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) v[y] = a[x][y];
            if (ok(v)) ans++;
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) v[x] = a[x][y];
            if (ok(v)) ans++;
        }

        System.out.println(ans);
    }
}