// [BOJ] 백도어 https://www.acmicpc.net/problem/17396
// 다익스트라, 최단 경로 
// 0 -> N-1
// 나머지 1... N-2 : 중간 거점 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 분기점의 수, 분기점들을 잇는 길의 수
	static int[] isHide;
	static int HIDE = 0, SEE = 1;// 0 : HIDE, 1 : SEE
	static List<Node> adj[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isHide = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			isHide[n] = Integer.parseInt(st.nextToken());
		}
		isHide[0] = HIDE;
		isHide[N - 1] = SEE;
		// N-1 분기점 : 시야에 보이고 유일하게 상대 시야에 보이면서 갈 수 있는 곳

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a번째 분기점
			int b = Integer.parseInt(st.nextToken()); // b번째 분기점
			int t = Integer.parseInt(st.nextToken()); // t시간이 걸림
			if ((isHide[a] == SEE && a != N - 1) || (isHide[b] == SEE && b != N - 1))
				continue;
			adj[a].add(new Node(b, t)); // 연결 양방향
			adj[b].add(new Node(a, t));
		}

		// 0 부터 거리
		long[] dist = new long[N]; // Long으로 설정 : 비용 100_000 * 간선수 300_000 = 3 * 10 ^ 10  : 최단거리 최댓값 
		Arrays.fill(dist, Long.MAX_VALUE);

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(0, 0));
		dist[0] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if (dist[now.to] < now.cost)
				continue; // 현재 방식이 의미가 없는 경우
			for (Node next : adj[now.to]) {
				if (dist[next.to] > dist[now.to] + next.cost) { // ?? -> 현재정점 dist[now.to] + 현재 정점 -> 인접정점 까지의 가중치
					dist[next.to] = dist[now.to] + next.cost;// next.cost
					queue.add(new Node(next.to, dist[next.to]));
				}
			}
		}

		System.out.println(dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1]);

	}

	static class Node implements Comparable<Node> {
		int to;
		long cost;

		Node(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node node) {
			return Long.compare(this.cost, node.cost);
		}
	}
}
