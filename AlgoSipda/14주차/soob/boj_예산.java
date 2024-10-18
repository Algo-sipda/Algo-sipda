import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 지방의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N]; // 각 지방의 예산 요청
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine()); // 총 예산

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, A[i]);
        }

        int left = 0;
        int right = max;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += Math.min(A[i], mid);
            }

            if (sum <= M) {
                result = Math.max(result, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}