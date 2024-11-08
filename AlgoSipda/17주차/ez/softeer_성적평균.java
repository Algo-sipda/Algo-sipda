import java.io.*;
import java.util.*;

public class softeer_성적평균 {

    static int N, K;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int score = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + score;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            double res = (double) (sum[end] - sum[start - 1]) / (end - start + 1);
            System.out.println(String.format("%.2f", res));
        }
    }
}
