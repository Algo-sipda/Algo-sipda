import java.util.*;
import java.io.*;

public class codetree_알파벳쟁탈전 {

    static int N, K, res;
    static int[][] map;
    static char[] selected;
    static char[] alpha = {'A', 'B', 'C'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command.equals("S")) {
                map[a][b] = 1;
            } else {
                map[a][b] = 2;
            }
        }

        selected = new char[N];

        perm(0);

        System.out.println(res);
    }

    private static void perm(int cnt) {
        if (cnt == N) {
            if (isSuccess()) {
                res++;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            selected[cnt] = alpha[i];
            perm(cnt + 1);
        }
    }

    private static boolean isSuccess() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0) continue;
                if (map[i][j] == 1) {
                    if (selected[i - 1] != selected[j - 1]) {
                        return false;
                    }
                } else {
                    if (selected[i - 1] == selected[j - 1]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
