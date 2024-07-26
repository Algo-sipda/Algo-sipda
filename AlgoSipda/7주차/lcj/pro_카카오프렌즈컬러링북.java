import java.util.*;

class Solution {
    class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<picture.length;i++) {
            for(int j=0;j<picture[i].length;j++) {
                if(!visited[i][j] && picture[i][j] != 0){
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j, picture[i][j], picture, visited));
                    numberOfArea++;
                }
            }
        }
        

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int cr, int cc, int color, int[][] picture, boolean[][] visited) {
        Queue<Point> q = new LinkedList<>();
        int cnt = 1;
        visited[cr][cc] = true;
        q.add(new Point(cr, cc));
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            int r = cur.r;
            int c = cur.c;
            
            for(int d = 0; d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= picture.length || nc < 0 || nc >= picture[0].length) continue;
                
                if(!visited[nr][nc] && picture[nr][nc] == color) {
                    cnt++;
                    q.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        return cnt;
    }
}