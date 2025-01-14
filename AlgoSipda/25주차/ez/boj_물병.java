import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_물병 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N <= K) {
            System.out.println(0);
            return;
        }

       int res = 0;
        while (true) {
            int cnt = 0;
            int num = N;
            while (num != 0) {
                if (num % 2 == 1) {
                    cnt++;
                }
                num /= 2;
            }
            if (cnt <= K) {
                break;
            }
            N += 1;
            res += 1;
        }

        System.out.println(res);

    }
}
