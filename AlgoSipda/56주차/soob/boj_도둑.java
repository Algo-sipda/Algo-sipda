import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] a = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                a[i] = Integer.parseInt(st.nextToken());
            }

            if (M == N) {
                long sum = 0;
                for (int i = 0; i < N; i++) sum += a[i];
                System.out.println(sum < K ? 1 : 0);
                continue;
            }

            long sum = 0;
            for (int i = 0; i < M; i++) sum += a[i];
            int answer = 0;
            for (int i = 0; i < N; i++) {
                if (sum < K) answer++;
                sum += a[(i + M) % N];
                sum -= a[i];
            }
            System.out.println(answer);
        }
    }
}
