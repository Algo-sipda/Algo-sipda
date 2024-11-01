
import java.io.*;
import java.util.*;

public class softeer_연탄의크기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] home = new int[N];
        st = new StringTokenizer(br.readLine());

        int max = 0;
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, home[i]);
        }

        int cnt = 0;
        for (int r = 2; r <= max; r++) {
            cnt = Math.max(cnt, getCnt(r, home));
        }

        System.out.println(cnt);
    }

    private static int getCnt(int num, int[] home) {
        int cnt = 0;
        for (int i = 0; i < home.length; i++) {
            if (home[i] / num > 0 && home[i] % num == 0) {
                cnt++;
            }
        }
        return cnt;
    }
}
