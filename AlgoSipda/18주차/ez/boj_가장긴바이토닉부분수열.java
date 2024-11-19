import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_가장긴바이토닉부분수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        int[] dpH = new int[N+1];
        int[] dpL = new int[N+1];
        int max = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(dpH, 1);
        Arrays.fill(dpL, 1);

        for (int i = 1; i < N + 1; i++) {
            if (arr[i] == 1) continue;
            for (int j = i-1; j > 0; j--) {
                if (arr[i] > arr[j]) {
                    dpH[i] = Math.max(dpH[i], dpH[j] + 1);
                }
            }
        }

        for (int i = N; i > 0; i--) {
            if (arr[i] == 1) continue;
            for (int j = i+1; j < N + 1; j++) {
                if (arr[i] > arr[j]) {
                    dpL[i] = Math.max(dpL[i], dpL[j] + 1);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, dpH[i] + dpL[i] - 1);
        }

        System.out.println(max);
    }
}
