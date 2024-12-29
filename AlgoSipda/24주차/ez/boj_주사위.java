import java.io.*;
import java.util.*;

public class boj_주사위 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long N = Integer.parseInt(br.readLine());
        long[] num = new long[6];
        long res = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        long one = Long.MAX_VALUE;
        long two = Long.MAX_VALUE;
        long three = 0;
        long max = 0;
        long sum = 0;

        for (int i = 0; i < 6; i++) {
            one = Math.min(one, num[i]);
            max = Math.max(max, num[i]);
            sum += num[i];
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i + j != 5 && i != j)
                    two = Math.min(two, num[i] + num[j]);
            }
        }

        for (int i = 0; i < 3; i++) {
            three += Math.min(num[i], num[5 - i]);
        }

        if (N == 1) {
            res = sum - max;
        } else {
            res = three * 4 + two * (4 * (N - 1) + 4 * (N - 2)) + one * ((N - 2) * (N - 1) * 4 + (N - 2) * (N - 2));
        }
        System.out.println(res);
    }
}