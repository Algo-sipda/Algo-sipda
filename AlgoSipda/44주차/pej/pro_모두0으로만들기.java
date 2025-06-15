import java.util.*;

class Solution {
    static int N;
    static int[] nodeWeights;
    static List<Integer> adj[];
    static boolean[] visited;
    static long totalCost = 0;
    // 자식부터 0으로 만들기 -> 부모에서 모아서 정리 
    
    public long solution(int[] a, int[][] edges) { // 0 으로 만들 수 있는 최소 횟수 
        nodeWeights = a;
        long sum = 0;
        for(int i = 0; i <a.length; i++) {
            sum += a[i]; 
        }
        if(sum != 0){
            return -1;
        }
        N = a.length;
        adj = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        visited = new boolean[N];
        dfs(0);
        return totalCost;
    }
    
    static long dfs(int now) {
        visited[now] = true;
        long sum = nodeWeights[now]; // 현재 노드의 가중치
        
        for(int i = 0; i < adj[now].size(); i++) {
            int next = adj[now].get(i);
            if(!visited[next]){
                long child = dfs(next); // 자식 노드에서 가중치 받아옴
                totalCost += Math.abs(child); // 옮긴 횟수만큼 더함 
                sum += child;
            }
        }
        
        return sum;
    }
}