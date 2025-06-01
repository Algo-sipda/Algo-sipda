// [PRO] 배달 https://school.programmers.co.kr/learn/courses/30/lessons/12978

import java.io.*;
import java.util.*;
class Solution {
    static int INF = 1_000_000_000 ;
    static List<Node> adj[];
    public int solution(int N, int[][] road, int K) { // 최대 K
        int answer = 0;
        // 1 에서부터 ?까지 K이하로 걸리는 
        adj = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }
        
        // 1 에서부터 1 - N까지의 거리 다익스트라로 구하기 
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll(); // 가장 빠른 곳 찾기
            if(now.weight > dist[now.to])continue; // 이미 최단 거리이므로 갈 필요없음
            for(Node next : adj[now.to]) {
                if(dist[next.to] > dist[now.to] + next.weight) {
                    dist[next.to] = dist[now.to] + next.weight;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        for(int i = 1; i <= N; i++){
            answer += dist[i] <= K ? 1 : 0;
            
        }
        
        return answer;
    }
    
    static class Node implements Comparable<Node>{
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return  this.weight - o.weight;
        }
    }
}