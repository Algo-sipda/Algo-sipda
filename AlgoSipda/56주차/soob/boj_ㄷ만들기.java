import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, a, b;
    static int[][] ps;
    static int black;
    static String[] g;

    static int rect(int y1, int x1, int y2, int x2) {
        return ps[y2][x2] - ps[y1][x2] - ps[y2][x1] + ps[y1][x1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        g = new String[n];
        ps = new int[n + 1][m + 1];
        for (int y = 0; y < n; y++) {
            g[y] = br.readLine();
            for (int x = 0; x < m; x++) {
                int w = g[y].charAt(x) == '.' ? 1 : 0;
                if (w == 0)
                    black++;
                ps[y + 1][x + 1] = ps[y + 1][x] + ps[y][x + 1] - ps[y][x] + w;
            }
        }

        int maxK = Math.min(n, m) / 3;
        long ans = Long.MAX_VALUE;
        for (int k = 1; k <= maxK; k++) {
            int h = 3 * k, w = 3 * k, cellsD = 7 * k * k;
            for (int y = 0; y + h <= n; y++) {
                for (int x = 0; x + w <= m; x++) {
                    int top = rect(y, x, y + k, x + 3 * k);
                    int midL = rect(y + k, x, y + 2 * k, x + k);
                    int bot = rect(y + 2 * k, x, y + 3 * k, x + 3 * k);
                    int whiteInD = top + midL + bot;
                    long cost = 1L * a * whiteInD + 1L * b * (black + whiteInD - cellsD);
                    if (cost < ans)
                        ans = cost;
                }
            }
        }
        System.out.println(ans);
    }
}
