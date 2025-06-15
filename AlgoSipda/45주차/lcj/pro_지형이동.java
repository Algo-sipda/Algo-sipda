import java.util.*;

class Solution {
    class Node {
        int cost, r, c;
        
        public Node(int cost, int r, int c) {
            this.cost = cost;
            this.r = r;
            this.c = c;
        }
    }
    
    int n;
    int[] dir = {1, 0, -1, 0};
    
    public int solution(int[][] land, int height) {
        n = land.length;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->o1.cost - o2.cost);
        boolean[][] visited = new boolean[n][n];
        
        pq.add(new Node(0, 0, 0));
        int total = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(visited[cur.r][cur.c]) continue;
            
            total += cur.cost;
            visited[cur.r][cur.c] = true;
            
            for(int d = 0; d<4 ; d++) {
                int nr = cur.r + dir[d];
                int nc = cur.c + dir[3-d];
                
                if(!isRange(nr, nc)) continue;
                
                int diff = Math.abs(land[cur.r][cur.c] - land[nr][nc]);
                
                if(diff <= height) pq.add(new Node(0, nr, nc));
                else pq.add(new Node(diff, nr, nc));
            }
        }
        return total;
    }
    
    public boolean isRange(int r, int c) {
        return 0<= r && r < n && 0<=c && c<n;
    }
}