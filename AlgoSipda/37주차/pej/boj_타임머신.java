// 11657 타임머신 - 벨만 포드 알고리즘 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // N개의 도시, M개 버스
    static long[] dist;
    static long INF = Long.MAX_VALUE;
    static List<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시

        edgeList = new ArrayList<>(); // 간선 리스트

        M = Integer.parseInt(st.nextToken()); // 노선 = 간선
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(a, b, c));
        }

        dist = new long[N + 1]; // N개의 도시
        Arrays.fill(dist, INF); // 모두 무한대로 초기화 
        dist[1] = 0; // 시작 도시 1번의 거리는 0으로 초기화 
        boolean isCycle = false; // 음의 사이클 여부 확인

        // 벨만 - 포드 알고리즘 시작
        // N-1번 반복하면 모든 간선에 대해 relaxation 수행
        for (int i = 1; i <= N; i++) { //정점의 수만큼 반복
            for (int j = 0; j < edgeList.size(); j++) {// 모든 간선을 돌면서
                int from = edgeList.get(j).start; // 간선 출발 노드
                int to = edgeList.get(j).end; // 간선 도착 노드 
                int cost = edgeList.get(j).cost; // 간선의 가중치 

                if (dist[from] == INF) { // 출발 노드가 아직 방문되지 않았다면 스킵
                    continue;
                }

                // 현재 간선을 통해 더 짧은 거리를 찾은 경우
                if (dist[to] > dist[from] + cost) {
                    dist[to] = dist[from] + cost;
                    if (i == N) { // V - 1개의 간선보다 더 많은 간선을 통해 최단경로를 구할 수 있다면 음의 사이클이 존재한다고 판단
                        isCycle = true;
                    }
                }
            }
        }
        if (isCycle) { // 사이클이 있다면, -1 출력
            System.out.println(-1);
        } else {
            // 2번 도시부터 N번 도시까지의 최단 거리 출력
            // 도달할 수 없는 경우 -1 출력
            for (int i = 2; i <= N; i++) { // 가장 빠른 시간 순서대로 출력
                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        }

    }

    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
