
// [PRO] 지형 이동
// https://school.programmers.co.kr/learn/courses/30/lessons/62050
// 1) bfs로 그룹3핑 
// 2) 그룹에 대해 다익스트라 -> 간선 

import java.util.*;
class Solution {
    static int N;
    static int[][] box;
    static int[][] groups;
    static int[][] way = {{1,0},{-1,0},{0,1},{0,-1}};
    static int MAX_HEIGHT;
    public int solution(int[][] land, int height) {
        // BFS 로 섬들을 그룹핑 
        // 높이 3이하만 움직일 수 있음
        MAX_HEIGHT= height;
        N = land.length;
        box = land;
        groups = new int[N][N];
        int maxGroup = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                if(groups[i][j] == 0) {
                    grouping(i, j, ++maxGroup);
                }
            }
        }
        
        // 그룹의 경계에 있는 곳 정보 가져오기 
        // 그룹 A <-> B , 차이 M
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int from = groups[i][j];
                for(int w = 0 ; w < 4; w++) {
                    int nx = i + way[w][0];
                    int ny = j + way[w][1];
                    if(inRange(nx,ny) && from != groups[nx][ny]) {
                        pq.add(new Edge(from, groups[nx][ny], Math.abs(box[i][j] - box[nx][ny])));
                    }
                }   
            }
        }
        // MST로 섬들 간의 관계 중 최소 구하기 
        // 1 - maxGroup
        int answer = 0;
        int[] parents = new int[maxGroup + 1];
        for(int i = 1; i <= maxGroup; i++){
            parents[i] = i;
        }
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(!isSameParent(parents, edge.from, edge.to)) {
                answer += edge.dist;
                union(parents, edge.from, edge.to);
            }
        }
        return answer;
    }
    
   static int find(int[] parents, int x) {
        if (parents[x] != x) {
            parents[x] = find(parents, parents[x]);
        }
        return parents[x];
    }

    static boolean isSameParent(int[] parents, int from, int to) {
        return find(parents, from) == find(parents, to);
    }
    
    static void union(int[] parents, int from, int to) {
        from = find(parents, from);
        to = find(parents, to);
        if(from < to) parents[to] = from;
        else parents[from] = to;
    }
    
    static class Edge implements Comparable<Edge>{
        int from, to, dist;
        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }
    static void grouping(int x, int y, int groupId) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        groups[x][y] = groupId;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int w = 0; w < 4; w++) {
                int nx = now[0] + way[w][0];
                int ny = now[1] + way[w][1];
                
                if(!inRange(nx, ny) || groups[nx][ny] != 0) {
                    continue;
                }
                if(Math.abs(box[now[0]][now[1]] - box[nx][ny]) <= MAX_HEIGHT) { // 높이차 3이하 
                    groups[nx][ny] = groupId;
                    queue.add(new int[]{nx, ny});
                    
                }
            }
        }
    }
    static boolean inRange(int x, int y) {
        return x >=0 && x < N && y >= 0 && y < N;
    }
}