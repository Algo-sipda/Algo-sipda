import java.util.*;
import java.io.*;

public class Main {

    public static int n, m, count = 0;
    public static int[][] map;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move(startX, startY, startD);

        System.out.println(count);
    }

    public static void move(int x, int y, int direct) {
        if (map[x][y] == 0) {
            map[x][y] = 2;
            count++;
        }

        boolean isClean = true;
        int origin = direct;
        for (int i = 0; i < 4; i++) {
            int nextD = (direct + 3) % 4;
            int nextX = x + dx[nextD];
            int nextY = y + dy[nextD];

            if (nextX > 0 && nextY > 0 && nextX < n && nextY < m) {
                if (map[nextX][nextY] == 0) {
                    move(nextX, nextY, nextD);
                    isClean = false;
                    break;
                }
            }
            direct = (direct + 3) % 4;
        }

        if (isClean) {
            int nextD = (origin + 2) % 4;
            int nextX = x + dx[nextD];
            int nextY = y + dy[nextD];

            if (nextX > 0 && nextY > 0 && nextX < n && nextY < m) {
                if (map[nextX][nextY] != 1) {
                    move(nextX, nextY, origin);
                }
            }
        }
    }
}
