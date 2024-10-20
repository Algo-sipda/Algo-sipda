import java.util.*;
import java.io.*;

public class boj_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] money = new int[N];
        st = new StringTokenizer(br.readLine());
        int right = 0;
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, money[i]);
        }
        int M = Integer.parseInt(br.readLine());

        int res = 0;
        int left = 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            int sum = 0;
            for (int i = 0; i < money.length; i++) {
                if (money[i] >= mid) {
                    sum += mid;
                } else {
                    sum += money[i];
                }
            }

            if (sum <= M) {
                res = Math.max(res, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(res);
    }
}