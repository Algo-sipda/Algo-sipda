import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_수고르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (M == 0) {
            System.out.println(0);
            System.exit(0);
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        while (left <= right && right < N) {
            if (arr[right] - arr[left] >= M) {
                min = Math.min(min, arr[right] - arr[left]);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(min);
    }
}