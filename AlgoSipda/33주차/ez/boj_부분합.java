import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_부분합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int len = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int total = 0;
        while (left <= N && right <= N) {
            if (total >= S) {
                len = Math.min(len, right - left);
                total -= arr[left++];
            } else {
                total += arr[right++];
            }
        }

        if (len == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(len);
        }
    }
}