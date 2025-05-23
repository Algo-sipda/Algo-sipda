import java.util.*;

class Solution {
    
    class Node {
        int from, to, cost;
        
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    
    ArrayList<ArrayList<Node>> list;
    
    public int solution(int N, int[][] road, int K) {

        list = new ArrayList<>();
        int[] cost = new int[N+1];
        int cnt = 1;
        
        for(int i=0;i<=N;i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0;i<road.length;i++) {
            list.get(road[i][0]).add(new Node(road[i][0], road[i][1], road[i][2]));
            list.get(road[i][1]).add(new Node(road[i][1], road[i][0], road[i][2]));
        }
        
        bfs(cost);    
        
        for(int i=2; i<=N;i++) {
            if(cost[i] <= K) {
                cnt++;
            }
        }
            
        return cnt;
    }
        
    public void bfs(int[] cost) {
        Queue<Node> q = new LinkedList<>();
        q.addAll(list.get(1));
        
        for(int i=2;i<cost.length;i++) {
            cost[i] = Integer.MAX_VALUE;
        }
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            
            if(cost[cur.to] > cost[cur.from] + cur.cost) {
                q.addAll(list.get(cur.to));
                cost[cur.to] = cost[cur.from] + cur.cost;
            }
        }
    }
}