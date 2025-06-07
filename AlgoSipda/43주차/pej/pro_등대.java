// [PRO] 등대 https://school.programmers.co.kr/learn/courses/30/lessons/133500
// dfs인데 인접 dfs : 상위누군가는 켜야 함 혹은 안켜도 됨을 리턴값으로 알려주면 됨


import java.util.*;
class Solution {
    static int answer = 0;
    static List<Integer> adj[];
    public int solution(int n, int[][] lighthouse) {
        
        adj = new ArrayList[n+1];
        for(int i = 0 ; i < n+1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < n - 1; i++) {
            adj[lighthouse[i][0]].add(lighthouse[i][1]);
            adj[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        dfs(1, 0);
        
        return answer;
    }
    int dfs(int node, int prev) {
        // 리프노드 -> 안켜야 함,  리프노드 : adj에 부모만 있음. 그리고 adj에 있는 그 노드가 이전값
        if(adj[node].size() == 1 && adj[node].get(0) == prev) {
            return 1; // 상위 누군가는 켜야 함
        }
        int net = 0;
        for(int next : adj[node])  {
            if(next == prev) {continue;} // 자식 <-> 부모 무한 루프 제거 
            net += dfs(next, node);
        }
        if(net == 0) { // 안켜도 됨
            return 1;  // 상위 누군가는 켜야함
        }
        answer += 1; // 본인 켜야 함 
        return 0;  // 상위는 안켜도 됨 
        
    }
    
}