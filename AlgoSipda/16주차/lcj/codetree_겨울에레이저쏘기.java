import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dir;

        public Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int N, M, answer;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][4];
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                if(str.charAt(j) == '/') {
                    map[i][j] = 1;
                }else {
                    map[i][j] = 2;
                }
            }
        }
        findLaser();
        System.out.println(answer);
    }

    public static void findLaser() {
        for (int i = 0; i < M; i++) {
            shootLaser(0, i, 2);  // 위쪽에서 아래로
            shootLaser(N - 1, i, 0);  // 아래쪽에서 위로
        }

        for (int i = 0; i < N; i++) {
            shootLaser(i, 0, 1);  // 왼쪽에서 오른쪽으로
            shootLaser(i, M - 1, 3);  // 오른쪽에서 왼쪽으로
        }
    }

    public static void shootLaser(int r, int c, int dir) {
        int cnt = 0;
        Point cur = new Point(r, c, dir);

        while(isRange(cur.r, cur.c) && !visited[cur.r][cur.c][cur.dir]) {
            visited[r][c][dir] = true;
            cnt++;

            cur = turnLaser(cur);
        }
        answer = Math.max(answer, cnt);
    }
    
    public static Point turnLaser(Point cur) {
        int dir = cur.dir;

        if(map[cur.r][cur.c] == 1) {
            if(dir == 0 || dir == 1) dir = dir==0? 1: 0;
            else dir = dir == 2? 3 : 2;
        } else {
            if(dir == 0 || dir == 3) dir = dir==0? 3: 0;
            else dir = dir == 1? 2 : 1;
        }
        
        return new Point(cur.r + dr[dir], cur.c + dc[dir], dir);
    }

    public static boolean isRange(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }
}