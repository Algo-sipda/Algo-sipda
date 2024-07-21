import java.util.*;

class Solution {
    class Point {
        int r, c, cnt;
        
        public Point (int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    public char[][] map;
    public boolean[][] visited;
    public Point start, lever;
    public int answer = -1, first = -1;
    public Queue<Point> q = new LinkedList<>();
    
    public int[] dr = {-1, 0, 1, 0};
    public int[] dc = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        map = new char[maps.length][maps[0].length()];
        
        for(int i=0;i<maps.length;i++) {
            for(int j=0;j<maps[i].length();j++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') {
                    start = new Point(i, j, 0);
                }
                if(map[i][j] == 'L') {
                    lever = new Point(i, j, 0);
                }
            }
        }
        
        bfs(start, 'L');
        if(first == -1) {
            return -1;
        }
        bfs(new Point(lever.r, lever.c, first), 'E');
        return answer;
    }
    
    public void bfs(Point p, char total) {
        visited = new boolean[map.length][map[0].length];
        q.add(p);
        visited[p.r][p.c] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            for(int d = 0; d<4;d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!isRange(nr, nc) || map[nr][nc] == 'X' || visited[nr][nc]) {
                    // 범위에 들지 않고, 벽을 만날 경우
                    continue;
                }
                
                if(map[nr][nc] == total) {
                    if(total == 'L') {
                        q.clear();
                        q.add(new Point(nr, nc, cur.cnt+1));
                        first = cur.cnt+1;
                    }
                    else if(total == 'E') {
                        answer = cur.cnt + 1;
                    }
                    return;
                }
                
                visited[nr][nc] = true;
                q.add(new Point(nr, nc , cur.cnt+1));
            }
        }
    }
    
    public boolean isRange(int r, int c) {
        return 0<=r && 0<=c && r<map.length && c < map[0].length;
    }
}