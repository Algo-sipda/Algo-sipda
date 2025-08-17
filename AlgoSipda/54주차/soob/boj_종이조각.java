import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] a;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int y = 0; y < N; y++) {
            String s = br.readLine();
            for (int x = 0; x < M; x++) {
                a[y][x] = s.charAt(x) - '0';
            }
        }
        System.out.println(solve());
    }

    static int solve() {
        int limit = 1 << (N * M);
        int best = 0;
        for (int mask = 0; mask < limit; mask++) {
            int sum = 0;
            for (int y = 0; y < N; y++) {
                int cur = 0;
                for (int x = 0; x < M; x++) {
                    int idx = y * M + x;
                    if ((mask & (1 << idx)) != 0) {
                        cur = cur * 10 + a[y][x];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            for (int x = 0; x < M; x++) {
                int cur = 0;
                for (int y = 0; y < N; y++) {
                    int idx = y * M + x;
                    if ((mask & (1 << idx)) == 0) {
                        cur = cur * 10 + a[y][x];
                    } else {
                        sum += cur;
                        cur = 0;
                    }
                }
                sum += cur;
            }
            if (sum > best) best = sum;
        }
        return best;
    }
}
