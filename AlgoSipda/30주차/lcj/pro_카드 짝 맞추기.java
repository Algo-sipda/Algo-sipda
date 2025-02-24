import java.util.*;

class Solution {
    
    class Point {
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    int R, C, answer, numCard;
    Point[][] points;
    boolean[] selected, exists;
    int SIZE = 4;
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    
    public int solution(int[][] board, int r, int c) {
        answer = Integer.MAX_VALUE;
        R = r; C = c;
        
        points = new Point[7][2];
        exists = new boolean[7];
        selected = new boolean[7];
        
        setCardinformation(board);
        search(board, 0, 0, new Point(r, c));
        
        return answer;
    }
    
    public void setCardinformation(int[][] board) {
        for(int i=0;i<SIZE;i++) {
            for(int j=0;j<SIZE;j++) {
                int value = board[i][j];
                
                if(value != 0) {
                    if(!exists[value]) {
                        exists[value] = true;
                        numCard++;
                        points[value][0] = new Point(i, j);
                    }
                    else {
                        points[value][1] = new Point(i, j);
                    }
                }
            }
        }
    }
    
    public void search(int[][] board, int value, int depth, Point cur) {
        if(depth == numCard) {
            answer = Math.min(answer, value);
            return;
        }
        
        for(int i=1; i<=numCard;i++) {
            if(!selected[i] && exists[i]) {
                selected[i] = true;
                
                for(int j=0;j<2;j++) {
                    Point p1 = points[i][j];
                    Point p2 = points[i][(j+1)%2];
                    
                    int cost = getMoveCnt(board, cur, p1) + getMoveCnt(board, p1, p2)+2;
                    
                    board[p1.r][p1.c] = 0;
                    board[p1.r][p2.c] = 0;
                    
                    search(board, value+cost, depth+1, p2);
                    
                    board[p1.r][p1.c] = i;
                    board[p2.r][p2.c] = i;
                }
                selected[i] = false;
            }
        }
    }
    
    public int getMoveCnt(int[][] board, Point start, Point end) {
        Queue<Point> q = new ArrayDeque<>();
        
        int[][] visited = new int[SIZE][SIZE];
        q.add(start);
        visited[start.r][start.c] = 1;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            if(cur.r == end.r && cur.c == end.c)
                return visited[cur.r][cur.c] -1;
            
            for(int d = 0; d<4;d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(isRange(nr, nc) && visited[nr][nc] == 0) {
                    visited[nr][nc] = visited[cur.r][cur.c]+1;
                    q.add(new Point(nr,nc));
                }
            }
            
            for(int d=0;d<4;d++) {
                int nr = cur.r;
                int nc = cur.c;
                
                while(isRange(nr, nc)) {
                    nr += dr[d];
                    nc += dc[d];
                    
                    if(!isRange(nr, nc)) {
                        nr -= dr[d];
                        nc -= dc[d];
                        break;
                    }
                    
                    if(board[nr][nc] != 0) break;
                }
                
                if(visited[nr][nc] == 0) {
                    visited[nr][nc] = visited[cur.r][cur.c] + 1;
                    q.add(new Point(nr, nc));
                }
            }
        }
        return visited[end.r][end.c]-1;
    }
    
    public boolean isRange(int r, int c) {
        return 0<=r && r< SIZE && 0<=c && c<SIZE;
    }
}