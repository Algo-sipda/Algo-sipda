import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_도둑 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            if (N == M) {
                if (sum < K) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else {
                int cnt = 0;
                int s = 0;
                int e = M - 1;
                int num = 0;
                for (int i = 0; i <= e; i++) {
                    num += arr[i];
                }
                while (s < N) {
                    if (num < K) {
                        cnt++;
                    }
                    num -= arr[s++];
                    num += arr[(++e) % N];
                }
                sb.append(cnt).append("\n");
            }
        }

        System.out.println(sb);
    }
}
