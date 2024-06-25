import java.util.*;
import java.io.*;

public class codetree_블럭의총둘레 {


    static boolean[][] map;
    static boolean[][] visited;
    static final int SIZE = 100;
    static int cnt;

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        visited = new boolean[SIZE + 2][SIZE + 2];
        map = new boolean[SIZE + 2][SIZE + 2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = true;
        }

        dfs(0, 0);

        System.out.println(cnt);
    }

    private static void dfs(int r, int c) {
        if (map[r][c]) {
            cnt++;
            return;
        }

        if (visited[r][c]) return;
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isOut(nr, nc)) continue;
            dfs(nr, nc);
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= SIZE + 2 || nc >= SIZE + 2;
    }

}