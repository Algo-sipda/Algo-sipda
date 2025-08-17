import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] x, y;
    static long sx, sy;
    static double ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            x = new int[N];
            y = new int[N];
            sx = 0;
            sy = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
                sx += x[i];
                sy += y[i];
            }
            ans = Double.MAX_VALUE;
            dfs(0, 0, 0L, 0L, N / 2);
            sb.append(String.format("%.12f\n", ans));
        }
        System.out.print(sb.toString());
    }

    static void dfs(int i, int c, long ax, long ay, int need) {
        if (c == need) {
            long rx = sx - 2 * ax;
            long ry = sy - 2 * ay;
            double d = Math.hypot(rx, ry);
            if (d < ans) ans = d;
            return;
        }
        if (i == N) return;
        if (N - i < need - c) return;
        dfs(i + 1, c + 1, ax + x[i], ay + y[i], need);
        dfs(i + 1, c, ax, ay, need);
    }
}
