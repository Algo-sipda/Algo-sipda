import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_계란으로계란치기 {

    static int N, ans;
    static Ege[] eges;

    static class Ege {
        int du, we;
        public Ege (int du, int we) {
            this.du = du;
            this.we = we;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eges = new Ege[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int du = Integer.parseInt(st.nextToken());
            int we = Integer.parseInt(st.nextToken());
            eges[i] = new Ege(du, we);
        }

        backtracking(0, 0);

        System.out.println(ans);
    }

    private static void backtracking(int cur, int cnt) {
        if (cur == N) {
            ans = Math.max(ans, cnt);
            return;
        }

        if (eges[cur].du <= 0 || cnt == N - 1) {
            backtracking(cur + 1, cnt);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (cur == i || eges[i].du <= 0) continue;
            eges[cur].du -= eges[i].we;
            eges[i].du -= eges[cur].we;
            int broken = 0;
            if (eges[cur].du <= 0) {
                broken++;
            }
            if (eges[i].du <= 0) {
                broken++;
            }
            backtracking(cur + 1, cnt + broken);

            eges[cur].du += eges[i].we;
            eges[i].du += eges[cur].we;
        }
    }
}
