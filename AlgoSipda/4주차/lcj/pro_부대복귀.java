import java.util.*;
class Solution {
    public static List<List<Integer>> graph;
    public static int[] distance;
    public static final int MAX = 1000000000;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList<>();
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] road: roads){
            int s = road[0];
            int e = road[1];
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        
        distance = new int[n+1];
        Arrays.fill(distance, MAX);
        dijkstra(destination);
        
        int[] answer = new int[sources.length];
        for(int i=0;i<sources.length;i++){
            answer[i] = (distance[sources[i]] < MAX)? distance[sources[i]]: -1;
        }
        return answer;
    }
    
    private static void dijkstra(int destination){
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        distance[destination] = 0;
        
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0;i<graph.get(cur).size();i++){
                int nn = graph.get(cur).get(i);
                if(distance[nn] > distance[cur]+1){
                    distance[nn] = distance[cur]+1;
                    q.add(nn);
                }
            }
        }
    }
}