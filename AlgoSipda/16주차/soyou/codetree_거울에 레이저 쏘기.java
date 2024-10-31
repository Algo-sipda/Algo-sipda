import java.io.*;
import java.util.*;

public class Main {

    static char[][] map;

    static int n, m;
    static int maxCount = 0;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][];

        for(int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < n; i++) {
            dfs(i, 0, 3, 0);
            dfs(i, m - 1, 2, 0);
        }

        for(int i = 0; i < m; i++) {
            dfs(0, i, 1, 0);
            dfs(n - 1, i, 0, 0);
        }

        System.out.println(maxCount);
    }

    public static void dfs(int x, int y, int direct, int count) {
        if(x < 0 || x >= n || y < 0 || y >= m) {
            maxCount = Math.max(count, maxCount);
            return;
        }

        int[] result = nextMove(x, y, direct);
        dfs(result[0], result[1], result[2], count + 1);
    }

    public static int[] nextMove(int x, int y, int direct) {

        int nextX = x;
        int nextY = y;
        int nextD = direct;

        char mirror = map[x][y];
        if(mirror == '/') {
            if(direct == 1) {
                // 하(1) -> 좌(2)
                nextX += dx[2];
                nextY += dy[2];
                nextD = 2;
            } else if(direct == 2) {
                // 좌(2) -> 하(1)
                nextX += dx[1];
                nextY += dy[1];
                nextD = 1;
            } else if(direct == 0) {
                // 상(0) -> 우(3)
                nextX += dx[3];
                nextY += dy[3];
                nextD = 3;
            } else {
                // 우(3) -> 상(0)
                nextX += dx[0];
                nextY += dy[0];
                nextD = 0;
            }
        } else {
            if(direct == 1) {
                // 하(1) -> 우(3)
                nextX += dx[3];
                nextY += dy[3];
                nextD = 3;
            } else if(direct == 3) {
                // 우(3) -> 하(1)
                nextX += dx[1];
                nextY += dy[1];
                nextD = 1;
            } else if(direct == 2) {
                // 좌(2) -> 상(0)
                nextX += dx[0];
                nextY += dy[0];
                nextD = 0;
            } else {
                // 상(0) -> 좌(2)
                nextX += dx[2];
                nextY += dy[2];
                nextD = 2;
            }
        }
        return new int[]{nextX, nextY, nextD};
    }
}