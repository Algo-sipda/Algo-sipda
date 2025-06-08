import java.util.*;

class Solution {
    
    long answer = 0;
    ArrayList<Integer>[] graph;
    boolean[] visited;
    long[] tmp;
    
    public long solution(int[] a, int[][] edges) {
        tmp = new long[a.length];
        graph = new ArrayList[a.length];
        visited = new boolean[a.length];
        
        // 가중치의 합이 0이 안되는경우
        for(int i=0;i<a.length;i++) {
            tmp[i] = a[i];
            answer += tmp[i];
        }
        
        if(answer != 0) return -1;
        
        // 되는 경우
        for(int i=0;i<a.length;i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge:edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        dfs(0);
        
        return answer;
    }
    
    public long dfs(int cur) {
        visited[cur] = true;
        
        for(int i=0;i<graph[cur].size();i++) {
            int next = graph[cur].get(i);
            
            if(visited[next]) continue;
            
            tmp[cur] += dfs(next);
        }
        
        answer += Math.abs(tmp[cur]);
        return tmp[cur];
    }
}