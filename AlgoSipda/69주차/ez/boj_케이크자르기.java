import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_케이크자르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] cake = new int[M + 1];
        for (int i = 0; i < M; i++) {
            cake[i] = Integer.parseInt(br.readLine());
        }
        cake[M] = L;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            int answer = 0;

            int left = 0;
            int right = cake[M - 1];
            while (left <= right) {
                int mid = (left + right) / 2;

                int cnt = 0;
                int prev = 0;
                for (int j = 0; j <= M; j++) {
                    if (cake[j] - prev >= mid) {
                        cnt++;
                        prev = cake[j];
                    }
                }

                if (cnt > num) {
                    left = mid + 1;
                    answer = Math.max(answer, mid);
                } else {
                    right = mid - 1;
                }
            }

            System.out.println(right);
        }
    }
}
