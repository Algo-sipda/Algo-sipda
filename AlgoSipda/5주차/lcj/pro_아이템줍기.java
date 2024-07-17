import java.util.*;

class Solution {
    class Point {
        int r, c, point;
        
        public Point(int r, int c, int point) {
            this.r = r;
            this.c = c;
            this.point = point;
        }
    }
    
    int[][] map = new int[101][101];
    int answer = 0;
    
    int[] dr = {1, 0, -1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int i=0;i<rectangle.length;i++) {
            int c1 = rectangle[i][0];
            int r1 = rectangle[i][1];
            int c2 = rectangle[i][2];
            int r2 = rectangle[i][3];
            draw(r1*2, c1*2, r2*2, c2*2);
        }
        bfs(characterY*2, characterX*2, itemY*2, itemX*2);
        return answer;
    }
    
    public void bfs(int r, int c, int goalR, int goalC) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        q.add(new Point(r, c, 0));
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            int curR = cur.r;
            int curC = cur.c;
            int curPoint = cur.point;
            
            if(curR == goalR && curC == goalC) {
                answer = curPoint/2;
                return;
            }
            
            for(int d = 0; d<4;d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];
                
                if(!isRange(nr, nc)) continue;
                if(!visited[nr][nc] && map[nr][nc] == 2) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc, curPoint+1));
                }
            }
        }
    }
    
    public boolean isRange(int r, int c) {
        return (0<=r && r<101 && 0<=c && c<101);
    }
    
    public void draw(int r1, int c1, int r2, int c2) {
        for(int i = r1; i<=r2;i++) {
            for(int j=c1; j<=c2;j++) {
                if(map[i][j] == 1) continue;
                map[i][j] = 1;
                if(i == r1 || i == r2 || j == c1 || j == c2) {
                    map[i][j] = 2;
                }
            }
        }
    }
    
    public void print() {
        for(int i = 0; i<25;i++) {
            for(int j=0; j<25;j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    
}