// [BOJ] 운전병의 딜레마 https://www.acmicpc.net/problem/27281
// 이분탐색 + 데이크스트라 

// Long 처리 : Long 자료형, 이분탐색에서 mid값구할 때 계산식 
// 간선을 지날 때, 무조건 시간 + 불편도 감소 비용 더해서 감소

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T; // 구역 개수, 도로 개수, 도달해야 하는 시간
	static Map<Integer, List<Edge>> adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // N개의 구역
		M = Integer.parseInt(st.nextToken()); // M개의 도로
		T = Integer.parseInt(st.nextToken()); // 총합이 T이하여야 한다
		// 걸리는 시간 +x , 현재도로 불편함 -x
		adj = new HashMap<>();

		int maxHard = 0;
		// 불편도 ?일 떄, T시간 안에 갈 수 있는지 확인
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken()); // 시간
			int s = Integer.parseInt(st.nextToken()); // 불편도

			adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new Edge(v, t, s));
			adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new Edge(u, t, s));
			maxHard = Math.max(maxHard, s);
		}

		int answer = Integer.MAX_VALUE;
		int left = 0, right = maxHard;
		while (left <= right) {
			int minHard = (right - left) / 2 + left;
			if (isPossible(minHard)) {
				answer = Math.min(answer, minHard);
				right = minHard - 1;
			} else {
				left = minHard + 1;
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	static boolean isPossible(int MAX_HARD) {
		// pq에서 빼낸 Edge의 t : 현재까지의 누적합, s : 최대 불편도
		long[] dist = new long[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0, 0)); // 1번 시작
		dist[1] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (now.t > dist[now.to])
				continue;
			for (Edge next : adj.getOrDefault(now.to, new ArrayList<>())) {

				// 간선을 지날 때, 무조건 시간 + 불편도 감소 비용 더해서 감소
				long costTime = next.t + Math.max(0, next.s - MAX_HARD);

				if (dist[next.to] > dist[now.to] + costTime) {
					dist[next.to] = dist[now.to] + costTime;
					pq.add(new Edge(next.to, dist[next.to], next.s));
				}
			}
		}

		return dist[N] <= T;
	}

	static class Edge implements Comparable<Edge> {
		int to, s; // 시간, 불편도
		long t;

		Edge(int to, long t, int s) {
			this.to = to;
			this.t = t;
			this.s = s;
		}

		@Override
		public int compareTo(Edge e) {
			return Long.compare(this.t, e.t);
		}
	}

}