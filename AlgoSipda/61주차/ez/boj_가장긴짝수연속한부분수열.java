import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_가장긴짝수연속한부분수열 {

    static int N, K;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        int delCnt = 0;
        int max = 0;

        while (e < num.length) {
            if (delCnt < K) {
                if (num[e] % 2 != 0) {
                    delCnt++;
                }
                e++;
                max = Math.max(max, e - s - delCnt);
            } else if (num[e] % 2 == 0) {
                e++;
                max = Math.max(max, e - s - delCnt);
            } else {
                if (num[s] % 2 != 0) {
                    delCnt--;
                }
                s++;
            }
        }

        System.out.println(max);
    }
}
