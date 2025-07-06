import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_구간곱구하기 {

    static int N, M, K, size;
    static long[] tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(int) Math.pow(2, size) * 2];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            tree[(int) Math.pow(2, size) + i] = num;
        }

        init(1, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long command = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (command == 1) { // update
                int idx = (int) ((int) Math.pow(2, size) + b - 1);
                update(idx, c);
            } else { // sum
                long start = (long) (Math.pow(2, size) + b - 1);
                long end = (long) (Math.pow(2, size) + c - 1);
                sb.append(getMulti(start, end) + "\n");
            }
        }
        System.out.print(sb);
    }

    private static long getMulti(long start, long end) {
        long multi = 1;
        while (start <= end) {
            if (start % 2 == 1) {
                multi = (multi * tree[(int) start]) % MOD;
            }
            if (end % 2 == 0) {
                multi = (multi * tree[(int) end]) % MOD;
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return multi;
    }

    private static void update(int idx, long c) {
        tree[idx] = c;
        idx /= 2;
        while (idx > 0) {
            tree[idx] = (tree[idx * 2] * tree[idx * 2 + 1]) % MOD;
            idx /= 2;
        }
    }

    private static void init(int start, int end, int node) {
        for (int i = (int) Math.pow(2, size) - 1; i > 0; i--) {
            tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % MOD;
        }
    }
}