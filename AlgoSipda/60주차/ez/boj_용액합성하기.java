import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_용액합성하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int s = 0;
        int e = N -1;
        int min = Integer.MAX_VALUE;
        while (s < e) {
            int sum = arr[s] + arr[e];
            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
            }
            if (sum == 0) break;
            if (sum > 0) {
                e--;
            } else {
                s++;
            }
        }
        System.out.println(min);
    }
}
