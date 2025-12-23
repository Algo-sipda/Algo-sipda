import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_외판원순회3 {

    static int N;
    static Point[] arr;
    static double[][] map;
    static double[][] dp;

    static class Point {
        int x, y;
        double z;

        public Point (int x, int y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new double[N][N];
        arr = new Point[N];
        dp = new double[N][1 << N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Point(x, y, 0);
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Double.MAX_VALUE);
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                map[i][j] = Math.sqrt(Math.pow((double) arr[i].x - (double) arr[j].x, 2) + Math.pow((double) arr[i].y - (double) arr[j].y, 2));
            }
        }

        double ans = Double.MAX_VALUE;
        Queue<Point> queue = new ArrayDeque<>();
        dp[0][1] = 0;
        queue.add(new Point(0, 1, 0));
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.y == ((1 << N) - 1)) {
                ans = Math.min(ans, cur.z + map[cur.x][0]);
                continue;
            }
            for (int i = 0; i < N; i++) {
                if (((1 << i) & cur.y) == 1 << i) continue;
                double dist = cur.z + map[cur.x][i];
                int mask = cur.y + (1 << i);
                if (dist > dp[i][mask]) continue;
                dp[i][mask] = dist;
                queue.add(new Point(i, mask, dist));
            }
        }
        System.out.println(ans);
    }

}
