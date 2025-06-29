import java.util.*;

class Car {
	int r, c, dir, cost;

	Car(int r, int c, int dir, int cost) {
		this.r = r;
        this.c = c;
        this.dir = dir;
        this.cost = cost;
	}
}

class Solution {
    int[][] visited;
    int len;
    
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        len = board.length;
        visited = new int[len][len];
        return search(board);
    }
    
    public int search(int[][] board) {
        Queue<Car> q = new ArrayDeque<>();
        
        q.add(new Car(0, 0, 1, 100));// 아래
        q.add(new Car(0, 0, 3, 100));// 오른쪽
        
        visited[0][0] = 100;
        
        int answer = Integer.MAX_VALUE;
        
        while(!q.isEmpty()) {
            Car cur = q.poll();
            
            if(cur.r == len-1 && cur.c == len-1) {
                answer = Math.min(answer, cur.cost);
                continue;
            }
            
            for(int d = 0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(!isRange(nr, nc) || board[nr][nc] != 0) continue;
                
                int weight = cur.dir == d ? 100: 600;
                
                if(visited[nr][nc] == 0) {
                    visited[nr][nc] = cur.cost + weight;
                    q.add(new Car(nr, nc, d, cur.cost+weight));
                }
                else if(cur.cost + weight < visited[nr][nc]+500) {
                    visited[nr][nc] = cur.cost + weight;
                    q.add(new Car(nr, nc, d, cur.cost + weight));
                }
            }
        }
        return answer - 100;
    }
    
    public boolean isRange(int r, int c) {
        return 0<= r && r<len && 0<=c && c<len;
    }
}