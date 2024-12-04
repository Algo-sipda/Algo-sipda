import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
    static class Location{
        int r;
        int c;
        int cnt;
        boolean destroyed;

        public Location(int r, int c, int cnt, boolean d) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.destroyed = d;
        }
    }
    
    static int n, m;
    static char[][] map;
    static Queue<Location> q = new LinkedList<>();
    static boolean[][][] visited;
    
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);

        map = new char[n][m];
        visited = new boolean[n][m][2];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        q.add(new Location(0, 0, 1, false));
        bfs();
    }
    
    public static void bfs() {
    	while (!q.isEmpty()) {
            Location cur = q.poll();

            if (cur.r == n - 1 && cur.c == m - 1) {
                System.out.println(cur.cnt);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(nr<0 || nr>=n || nc<0 || nc>=m) continue;

                int nextCnt = cur.cnt+1;

                // 벽이 아니면
                if(map[nr][nc]=='0'){
                    if(!cur.destroyed && !visited[nr][nc][0]) {
                        q.add(new Location(nr, nc, nextCnt, false));
                        visited[nr][nc][0] = true;
                    }else if(cur.destroyed && !visited[nr][nc][1]){
                        q.add(new Location(nr, nc, nextCnt, true));
                        visited[nr][nc][1] = true;
                    }
                }
                // 벽이면
                else if(map[nr][nc]=='1'){

                    if(!cur.destroyed){
                        q.add(new Location(nr, nc, nextCnt, true));
                        visited[nr][nc][1] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}