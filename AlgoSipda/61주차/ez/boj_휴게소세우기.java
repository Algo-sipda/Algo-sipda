import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_휴게소세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] hu = new int[N + 2];
        hu[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            hu[i] = Integer.parseInt(st.nextToken());
        }
        hu[hu.length - 1] = L;

        Arrays.sort(hu);

        int left = 1;
        int right = L - 1;
        while (left <= right) {
            int len = (left + right) / 2;
            int sum = 0;

            for (int i = 1; i < hu.length; i++) {
                sum += (hu[i] - hu[i - 1] - 1) / len;
            }
            if (sum > M) {
                left = len + 1;
            } else {
                right = len - 1;
            }
        }
        System.out.println(left);
    }
}
