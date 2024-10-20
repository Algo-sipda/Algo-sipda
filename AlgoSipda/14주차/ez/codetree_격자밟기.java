import java.util.*;
import java.io.*;

public class codetree_격자밟기 {

    static int K, cnt;
    static boolean[][] map = new boolean[5][5];

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = true;
        }

        map[0][0] = true;
        map[4][4] = true;
        dfs(0, 0, 4, 4, true);

        System.out.println(cnt);
    }

    private static void dfs(int ar, int ac, int br, int bc, boolean isATurn) {
        if (isATurn) {
            for (int i = 0; i < 4; i++) {
                int nr = ar + dr[i];
                int nc = ac + dc[i];
                if (isOut(nr, nc) || map[nr][nc]) continue;
                map[nr][nc] = true;
                dfs(nr, nc, br, bc, !isATurn);
                map[nr][nc] = false;
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int nr = br + dr[i];
                int nc = bc + dc[i];
                if (isOut(nr, nc)) continue;
                if (ar == nr && ac == nc) {
                    if (isCorrect()) {
                        cnt++;
                        return;
                    }
                    continue;
                }
                if (map[nr][nc]) continue;
                map[nr][nc] = true;
                dfs(ar, ac, nr, nc, !isATurn);
                map[nr][nc] = false;
            }
        }
    }

    private static boolean isCorrect() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!map[i][j]) return false;
            }
        }
        return true;
    }

    private static boolean isOut(int nr, int nc) {
        return (nr < 0 || nc < 0 || nr >= 5 || nc >= 5);
    }
}