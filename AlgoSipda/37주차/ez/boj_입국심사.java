import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class boj_입국심사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        long M = Integer.parseInt(str[1]);

        int[] arr = new int[N];
        long max = 0;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        Arrays.sort(arr);

        long left = 0;
        long right = M * max;
        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int item : arr) {
                long cnt = mid / item;
                if (total >= M || item > mid) {
                    break;
                }
                total += cnt;
            }

            if (total >= M) {
                right = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}