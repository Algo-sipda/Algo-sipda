import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_캠프준비 {
    static int N, L, R, X, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        combi(0, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.print(answer);
    }

    public static void combi(int idx, int cnt, int sum, int max, int min) {
        if (cnt >= 2) {
            if (sum >= L && sum <= R && max - min >= X) {
                answer += 1;
            }
        }

        for (int i = idx; i < N; i++) {
            combi(i + 1, cnt + 1, sum + arr[i], Math.max(max, arr[i]), Math.min(min, arr[i]));
        }
    }
}
