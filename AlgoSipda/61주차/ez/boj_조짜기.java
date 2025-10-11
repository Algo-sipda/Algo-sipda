import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_조짜기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] score = new int[N + 1];
        int[] dp = new int[N + 1];
        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            for (int j = i - 1; j >= 1; j--) {
                max = Math.max(max, Math.abs(score[i] - score[j]) + dp[j - 1]);
            }
            dp[i] = max;
        }

        System.out.println(dp[N]);
    }
}
/*
성적 차이가 많이 날수록
나이 차이가 적게 날수록
조의 개수는 상관없음 => DP
조가 잘 짜여진 정도의 최댓값
 */