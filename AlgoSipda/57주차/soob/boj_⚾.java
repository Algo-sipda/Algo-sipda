import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] A;
    static boolean[] used = new boolean[9];
    static int[] order = new int[9];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        used[0] = true;
        order[3] = 0;
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int idx) {
        if (idx == 9) {
            if (order[3] != 0) return;
            answer = Math.max(answer, simulate());
            return;
        }
        if (idx == 3) {
            dfs(idx + 1);
            return;
        }
        for (int p = 1; p < 9; p++) {
            if (used[p]) continue;
            used[p] = true;
            order[idx] = p;
            dfs(idx + 1);
            used[p] = false;
        }
    }

    static int simulate() {
        int score = 0;
        int batter = 0;
        for (int inning = 0; inning < N; inning++) {
            int outs = 0;
            int b1 = 0, b2 = 0, b3 = 0;
            while (outs < 3) {
                int player = order[batter];
                int res = A[inning][player];
                if (res == 0) outs++;
                else if (res == 1) {
                    score += b3;
                    b3 = b2;
                    b2 = b1;
                    b1 = 1;
                } else if (res == 2) {
                    score += b3 + b2;
                    b3 = b1;
                    b2 = 1;
                    b1 = 0;
                } else if (res == 3) {
                    score += b3 + b2 + b1;
                    b3 = 1;
                    b2 = 0;
                    b1 = 0;
                } else {
                    score += b3 + b2 + b1 + 1;
                    b3 = 0;
                    b2 = 0;
                    b1 = 0;
                }
                batter++;
                if (batter == 9) batter = 0;
            }
        }
        return score;
    }
}
