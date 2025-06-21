// [BOJ] 허들 넘기
// https://www.acmicpc.net/problem/23286
// 다익스트라 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static int[][] minHeights;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		minHeights = new int[N + 1][N + 1];
		floyd();

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int result = minHeights[s][e];
			System.out.println(result == Integer.MAX_VALUE ? -1 : result);

		}
	}

	// 방법2 : 플로이드 워셜 s,e가 많으므로
	static void floyd() throws Exception {

		// 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					minHeights[i][j] = 0;
				else
					minHeights[i][j] = Integer.MAX_VALUE;
			}
		}

		// 간선 입력
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			minHeights[u][v] = Math.min(minHeights[u][v], h); // 중복 간선 처리
		}

		// 핵심 플로이드 워셜 로직 (Max-min 경로)
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (minHeights[i][k] != Integer.MAX_VALUE && minHeights[k][j] != Integer.MAX_VALUE) {
						minHeights[i][j] = Math.min(minHeights[i][j], Math.max(minHeights[i][k], minHeights[k][j]));
					}
				}
			}
		}
	}
	// 다른 방법으로 Binary Search + BFS/DFS 로 이 높이 이하로 목적지에 도달할 수 있는지 확인

	// 방법 1 : dijkstra 변형 : 경로 누적 합이 아니고, 경로 중 최대 허들 높이 갱신 -> 시간 초과남

	static List<Edge> adj[];

	static int dijkstra(int s, int e) throws Exception {
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			// u -> v 간선
			// 높이 h인 허들이 간선 중간에 놓여 있음
			adj[u].add(new Edge(v, h));
		}

		// 최단 경로로 가야 함
		int[] heights = new int[N + 1];
		Arrays.fill(heights, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(s, 0));
		heights[s] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll(); // 누적비용
			if (now.to == e) {
				return heights[now.to];
			}
			for (Edge next : adj[now.to]) {
				int maxHurdle = Math.max(now.height, next.height); // 경로 중 최대 허들 높이

				if (heights[next.to] > maxHurdle) {
					heights[next.to] = maxHurdle;
					pq.add(new Edge(next.to, heights[next.to]));
				}
			}
		}
		return -1;

	}

	static class Edge implements Comparable<Edge> {
		int to, height;

		Edge(int to, int height) {
			this.to = to;
			this.height = height;
		}

		@Override
		public int compareTo(Edge e) {
			return this.height - e.height;
		}
	}

}