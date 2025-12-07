import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] x;
    static int[] y;
    static double[][] dist;
    static double[][] dp;
    static final double INF = 1e18;

    static double dfs(int mask, int cur) {
        if (mask == (1 << N) - 1)
            return dist[cur][0];

        double cached = dp[mask][cur];
        if (cached >= 0)
            return cached;

        double ret = INF;
        for (int next = 0; next < N; next++) {
            if ((mask & (1 << next)) != 0)
                continue;
            double cost = dist[cur][next] + dfs(mask | (1 << next), next);
            if (cost < ret)
                ret = cost;
        }
        dp[mask][cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        x = new int[N];
        y = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        dist = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long dx = x[i] - x[j];
                long dy = y[i] - y[j];
                double d = Math.sqrt((double) dx * dx + (double) dy * dy);
                dist[i][j] = d;
                dist[j][i] = d;
            }
        }

        int size = 1 << N;
        dp = new double[size][N];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], -1.0);
        }

        double ans = dfs(1, 0);
        System.out.println(ans);
    }
}
