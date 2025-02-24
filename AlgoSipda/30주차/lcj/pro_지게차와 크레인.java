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
    boolean[][] empty;
    char[][] st;
    
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        
        st = new char[n][m];
        
        for(int i=0;i<n;i++) {
            st[i] = storage[i].toCharArray();
        }
        
        empty = new boolean[n][m];
        int containerCnt = n*m;
        
        for(String request: requests) {
            char ch = request.charAt(0);
            
            if(request.length() == 1) {
                ArrayList<Point> pulled = new ArrayList<>();
            
                for(int i=0;i<n;i++) {
                    for(int j=0;j<m;j++) {
                        if(!empty[i][j] && st[i][j] == ch) {
                            if(bfs(i, j)) pulled.add(new Point(i, j));
                        }
                    }
                }
                
                for(Point p : pulled) {
                    empty[p.r][p.c] = true;
                    containerCnt--;
                }
            }
            else {
                for(int i=0;i<n;i++) {
                    for(int j=0;j<m;j++) {
                        if(!empty[i][j] && st[i][j] == ch) {
                            empty[i][j] = true;
                            containerCnt--;
                        }
                    }
                }
            }
        }
        
        return containerCnt;
    }
    
    public boolean bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        boolean isPullable = false;
        
        q.add(new Point(r, c));
        
        while(!q.isEmpty()) {
            Point p = q.poll();
            
            for(int d = 0; d<4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                
                if(!isRange(nr, nc)) return true;
                else if(!visited[nr][nc] && empty[nr][nc]) {
                    q.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        return false;
    }
    
    public boolean isRange(int r, int c) {
        return 0<=r && r<n && 0<=c && c<m;
    }
}