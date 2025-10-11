import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_흙길보수하기 {

    static int N, L;
    static int[][] dump;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        dump = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            dump[i][0] = s;
            dump[i][1] = e;
        }

        Arrays.sort(dump, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int cnt = 0;
        int cur = 0;
        for (int i = 0; i < N; i++) {
            if (dump[i][0] > cur) {
                cur = dump[i][0];
            }
            if (cur < dump[i][1]) {
                while (cur < dump[i][1]) {
                    cur += L;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
