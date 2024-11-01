import java.util.*;
import java.io.*;

public class codetree_거울에레이저쏘기 {

    static int N, M, max;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int dir = 1;
        int r = 0;
        int c = -1;
        int cnt = 0;
        while (cnt <= 2 * M + 2 * N - 4) {

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (isOut(nr, nc)) {
                shoot(r, c, (dir + 2) % 4, 1);
                // System.out.println(r + " " + c + " -> " + (dir+2)%4);
                dir = (dir + 1) % 4;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }
            r = nr;
            c = nc;

            shoot(r, c, (dir + 1) % 4, 1);
            // System.out.println(r + " " + c + " -> " + (dir+1)%4);
            cnt++;
        }

        System.out.println(max);
    }

    private static void shoot(int row, int col, int dir, int cnt) {

        char mirror = map[row][col];
        int nextDir = getDir(mirror, dir);
        int nr = row + dr[nextDir];
        int nc = col + dc[nextDir];

        if (isOut(nr, nc)) {
            max = Math.max(max, cnt);
            return;
        }

        shoot(nr, nc, nextDir, cnt + 1);
    }

    private static int getDir(char mirror, int dir) {
        if (mirror == '/') {
            if (dir == 0 || dir == 1) {
                return dir == 0 ? 1 : 0;
            }
            if (dir == 2 || dir == 3) {
                return dir == 2 ? 3 : 2;
            }
        }

        if (mirror == '\\') {
            if (dir == 0 || dir == 3) {
                return dir == 0 ? 3 : 0;
            }
        }
        return dir == 1 ? 2 : 1;
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }

    private static void printMap(char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}


