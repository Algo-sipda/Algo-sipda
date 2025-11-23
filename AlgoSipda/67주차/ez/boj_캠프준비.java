import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_캠프준비 {

    static int N, L, R, X, ans;
    static int[] problem;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        problem = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            problem[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(problem);
        combi(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println(ans);
    }

    private static void combi(int start, int cnt, int sum, int max, int min) {
        if (cnt >= 2) {
            if (sum >= L && sum <= R && max - min >= X) {
                ans++;
            }
        }

        for (int i = start; i < N; i++) {
            combi(i + 1, cnt + 1, sum + problem[i], Math.max(problem[i], max), Math.min(min, problem[i]));
        }
    }
}
