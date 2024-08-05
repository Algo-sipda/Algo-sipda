// 프림 알고리즘 이용
import java.util.*;

class Solution {
    class Point implements Comparable<Point> {
        int to, cost;
        
        public Point(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Point p) {
            return this.cost - p.cost;
        }
    }
    
    ArrayList<Point>[] list;
    int answer = 0;
    
    public int solution(int n, int[][] costs) {
        list = new ArrayList[n];
        
        for(int i=0;i<n;i++) {
            list[i] = new ArrayList<>();
        }
        
        // cost 비용 list에 넣기
        for(int i=0;i<costs.length;i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            list[from].add(new Point(to, cost));
            list[to].add(new Point(from, cost));
        }
        
        PrimeAlgo(n);
        
        return answer;
    }
    
    public void PrimeAlgo(int n) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(0, 0));
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            
            if(visited[cur.to]) continue;
            
            visited[cur.to] = true;
            answer += cur.cost;
            
            int size = list[cur.to].size();
            for(int i=0;i<size;i++) {
                int to = list[cur.to].get(i).to;
                int cost = list[cur.to].get(i).cost;
                
                if(visited[to]) continue;
                q.add(new Point(to, cost));
            }
        }
    }
}