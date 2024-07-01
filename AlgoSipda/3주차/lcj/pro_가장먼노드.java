import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    int answer = 0;
    
    public int solution(int n, int[][] edge) {
        for(int i=0;i<=n;i++)
            graph.add(new ArrayList<>());
        
        for(int i=0;i<edge.length;i++) {
            int v1 = edge[i][0];
            int v2 = edge[i][1];
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        boolean[] visited = new boolean [n+1];
        bfs(n, visited);
        
        return answer;
    }
    
    public void bfs(int n, boolean[] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {1, 0});
        visited[1] = true;
        
        int maxDepth = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int v = cur[0];
            int depth = cur[1];
            
            if(maxDepth == depth) answer++;
            else if(maxDepth < depth) {
                maxDepth = depth;
                answer = 1;
            }
        
            for(int i=0;i<graph.get(v).size();i++) {
                int w = graph.get(v).get(i);
                if(!visited[w]) {
                    visited[w] = true;
                    q.add(new int[] {w, depth+1});
                }
            }
        }
    }
}