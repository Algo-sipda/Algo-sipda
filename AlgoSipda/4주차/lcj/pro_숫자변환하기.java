import java.util.*;

class Solution {
    final int NUM = 1000000;
    boolean[] visited = new boolean[NUM+1];
    
    public int solution(int x, int y, int n) {
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        
        q.add(x);
        visited[x] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int i=0;i<size;i++) {
                int cur = q.poll();
                
                if(cur == y) return cnt;
                
                if(cur+n <= y && !visited[cur+n]) {
                    visited[cur+n] = true;
                    q.add(cur+n);
                }
                if(cur*2 <= y && !visited[cur*2]) {
                    visited[cur*2] = true;
                    q.add(cur*2);
                }
                if(cur*3 <= y && !visited[cur*3]) {
                    visited[cur*3] = true;
                    q.add(cur*3);
                }
            }
            cnt++;
        }
        
        return -1;
    }
    
}