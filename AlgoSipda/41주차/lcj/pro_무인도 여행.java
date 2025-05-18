import java.util.*;

class Solution {
    class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    int[][] map;
    boolean[][] visited;
    ArrayList<Integer> answer = new ArrayList<>();
    int M, N;
    
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    public ArrayList<Integer> solution(String[] maps) {
        M = maps.length;
        N = maps[0].length();
        map = new int[M][N];
        visited = new boolean[M][N];
        
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(maps[i].charAt(j)=='X') {
                    map[i][j] = -1;
                }
                else {
                    map[i][j] = maps[i].charAt(j)-'0';
                }
            }
        }
        
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                if(!visited[i][j] && map[i][j] != -1) {
                    int cnt = bfs(i, j);
                    answer.add(cnt);
                }
            }
        }
        if(answer.size() == 0) answer.add(-1);
        Collections.sort(answer);
        
        return answer;
    }
    
    public int bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;
        
        int cnt = map[r][c];
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            for(int d =0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!isRange(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                
                if(map[nr][nc] != -1) {
                    cnt += map[nr][nc];
                    visited[nr][nc] = true;
                    q.add(new Point(nr,nc));
                }
                
            }
        }
        return cnt;
    }
    
    public boolean isRange(int r, int c) {
        return 0<= r && r<M && 0<=c && c<N;
    }
}