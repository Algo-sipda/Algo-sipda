import java.util.*;

class Solution {
    
    Set<Integer> set = new HashSet<>();
    Map<Integer, List<Integer>> graph;
    int cnt = 0, rcnt = 0;
    
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0, 0};
        
        graph = new HashMap<>();
        for(int node: nodes) {
            graph.put(node, new ArrayList<>());
        }
        
        for(int[] edge:edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        for(int node: nodes) {
            if(set.contains(node)) continue;
            
            cnt = 0;
            rcnt = 0;
            bfs(node);
            
            if(cnt == 1) answer[1]++;
            if(rcnt == 1) answer[0]++;
        }
        
        return answer;
    }
    
    void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        
        set.add(start);
        q.add(start);
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            int curCnt = graph.get(cur).size() - 1;
            
            if(cur % 2 == 0) {
                if(curCnt % 2 == 0) cnt++;
                else rcnt++;
            }
            else {
                if(curCnt % 2 == 1) cnt++;
                else rcnt++;
            }
            
            List<Integer> child = graph.get(cur);
            
            for(int c:child) {
                if(set.contains(c)) continue;
                
                set.add(c);
                q.add(c);
            }
        }
    }
}