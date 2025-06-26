// [BOJ] 거의 최단경로 https://www.acmicpc.net/problem/5719
// dijkstra 로 최단 경로 dist 배열 업데이트 
// 역추적(bfs)으로 최단 경로에 포함된 모든 경로 방문하지 못하도록 처리 
// 역추적으로 최단경로를 찾아내는 방법 중에 parents 배열 사용하는 것도 있지만, 이는 여러 최단 경로 중 한 경로 밖에 못찾음
// 따라서, bfs를 활용하여 끝점에서 시작점으로의 최단 경로인 모든 경로를 찾음 
// 최단 경로임을 어떻게 아는 가 ? to -> from으로 경로가 이미 정해져있음 from -> to 경로 길이랑 역방향으로 탐색 
// 주의해야할 점 =>  여러 최단 경로에 있는 동일한 경로를 여러번 방문할 수 있으니 중복 체크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int S, D; // 시작점, 도착점
	static Map<Integer, List<Edge>> adj;
	static Set<List<Integer>> isUsed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			adj = new HashMap<>();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				// U -> V : P 단방향
				adj.computeIfAbsent(U, k -> new ArrayList<>()).add(new Edge(V, P));
			}

			isUsed = new HashSet<>();

			int[] dist = findDists(); // S -> D 최단 경로 일 때 dist[] 구하기

			removeShortestPaths(dist);// bfs역추적 + 모든 최단 경로 제거

			int[] nextMinDist = findDists(); // find 다음 최단 경로
			System.out.println(nextMinDist[D] == Integer.MAX_VALUE ? -1 : nextMinDist[D]);

		}

	}

	// bfs 역추적 + 모든 최단 경로 제거
	static void removeShortestPaths(int[] dist) {
		// dist[i] = S에서 i까지 가는 최소 비용
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(D);
		boolean[][] visited = new boolean[N][N]; // 최단 경로에 있는 동일한 간선을 중복해서 여러 번 탐색하지 않도록 막기 위해
		while (!queue.isEmpty()) {
			int to = queue.poll();

			for (Map.Entry<Integer, List<Edge>> entry : adj.entrySet()) {
				int from = entry.getKey();
				for (Edge e : entry.getValue()) {
					if (e.to == to && dist[to] == dist[from] + e.cost) {
						if (visited[from][to])
							continue;
						visited[from][to] = true;
						isUsed.add(List.of(from, to));
						queue.add(from); // 역방향으로 탐색
					}
				}
			}
		}
	}

	static int[] findDists() {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(S, 0));
		dist[S] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (dist[now.to] < now.cost)
				continue;
			for (Edge next : adj.getOrDefault(now.to, new ArrayList<>())) {
				if (isUsed.contains(List.of(now.to, next.to)))
					continue;
				if (dist[next.to] > dist[now.to] + next.cost) {
					dist[next.to] = dist[now.to] + next.cost;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}
		return dist;
	}

	static class Edge implements Comparable<Edge> {
		int to, cost;

		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}

}
