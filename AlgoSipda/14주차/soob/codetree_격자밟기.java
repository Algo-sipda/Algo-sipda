import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int[][] map = new int[5][5];
        map[0][0] = 1;
        map[4][4] = 1;
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }
        dfs(map, 0, 0, 4, 4);
        System.out.println(answer);
    }

    public static void dfs(int[][] map, int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            if (stepAll(map))
                answer++;
            return;
        }

        for (int d1 = 0; d1 < 4; d1++) {
            int nx1 = x1 + dx[d1];
            int ny1 = y1 + dy[d1];
            if (nx1 < 0 || nx1 >= 5 || ny1 < 0 || ny1 >= 5) continue;
            if (map[ny1][nx1] == 1) continue;
            for (int d2 = 0; d2 < 4; d2++) {
                int nx2 = x2 + dx[d2];
                int ny2 = y2 + dy[d2];
                if (nx2 < 0 || nx2 >= 5 || ny2 < 0 || ny2 >= 5) continue;
                if (map[ny2][nx2] == 1) continue;
                map[ny1][nx1] = 1;
                map[ny2][nx2] = 1;
                dfs(map, nx1, ny1, nx2, ny2);
                map[ny1][nx1] = 0;
                map[ny2][nx2] = 0;
            }
        }
    }

    public static boolean stepAll(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 1) cnt++;
            }
        }
        return cnt == 25;
    }
}