import java.io.*;
import java.util.*;

public class Main_17836_공주님을구해라 {
    
    static class Point {
        int r, c, time;
        boolean hasKnife;
        
        public Point(int r, int c, int time, boolean hasKnife) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.hasKnife = hasKnife;
        }
    }

    static int N, M, T;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = bfs();
        System.out.println(result > T ? "Fail" : result);
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        
        q.add(new Point(0, 0, 0, false));
        visited[0][0][0] = true;
        
        while (!q.isEmpty()) {
            Point cur = q.poll();

            if (cur.r == N - 1 && cur.c == M - 1) {
                return cur.time;
            }
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (!isRange(nr, nc)) continue;
                if (visited[nr][nc][cur.hasKnife ? 1 : 0]) continue;

                if (map[nr][nc] == 1 && !cur.hasKnife) continue;

                boolean nextHasGram = cur.hasKnife || (map[nr][nc] == 2);
                
                visited[nr][nc][nextHasGram ? 1 : 0] = true;
                q.add(new Point(nr, nc, cur.time + 1, nextHasGram));
            }
        }
        
        return T + 1;
    }
    
    public static boolean isRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}