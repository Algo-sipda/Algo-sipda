import java.util.*;

class Solution {
    class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    int n, m;
    int[] oilAmount;
    boolean[][] visited;
    
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        
        oilAmount = new int[m];
        visited = new boolean[n][m];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, land);
                }
            }
        }
        
        int answer = 0;
        for(int i=0;i<m;i++) {
            answer = Math.max(answer, oilAmount[i]);
        }
        return answer;
    }
    
    public void bfs(int r, int c, int[][] land) {
        Queue<Point> q = new LinkedList<>();
        
        visited[r][c] = true;
        q.add(new Point(r, c));
        
        int oilCnt = 1;
        boolean[] oilColumn = new boolean[m];
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            oilColumn[cur.c] = true;
            
            for(int d = 0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!isRange(nr, nc)) continue;
                if(land[nr][nc] == 1 && !visited[nr][nc]) {
                    q.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                    oilCnt++;
                }
            }
        }
        
        for(int i=0;i<m;i++) {
            if(oilColumn[i]) oilAmount[i] += oilCnt;
        }
    }
    
    public boolean isRange(int r, int c) {
        return 0<=r && r<n && 0<= c && c<m;
    }
}