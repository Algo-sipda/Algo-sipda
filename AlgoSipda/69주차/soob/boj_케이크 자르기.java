import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int L;
    static int[] cuts;
    static int[] queries;

    static boolean canMake(int len, int cutCount) {
        int pieces = 0;
        int last = 0;
        for (int i = 0; i < M; i++) {
            if (cuts[i] - last >= len) {
                pieces++;
                last = cuts[i];
            }
        }
        if (L - last >= len)
            pieces++;
        return pieces >= cutCount + 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        cuts = new int[M];
        for (int i = 0; i < M; i++) {
            cuts[i] = Integer.parseInt(br.readLine());
        }

        queries = new int[N];
        for (int i = 0; i < N; i++) {
            queries[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int q = queries[i];
            int left = 1;
            int right = L;
            int answer = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canMake(mid, q)) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }
}
