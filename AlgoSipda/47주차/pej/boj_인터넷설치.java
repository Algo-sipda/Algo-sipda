// [boj] 인터넷 설치 https://www.acmicpc.net/problem/1800
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, P, K;
	static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
		P = Integer.parseInt(st.nextToken()); // 케이블 수
		K = Integer.parseInt(st.nextToken()); // 무료 케이블 수

		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		int maxCost = 0;

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[u].add(new int[] { v, cost });
			adj[v].add(new int[] { u, cost });
			maxCost = Math.max(maxCost, cost);
		}

		int left = 0;
		int right = maxCost;
		int answer = -1;

		while (left <= right) { // 원장 선생님이 내게 되는 최소의 돈 출력
			int mid = (left + right) / 2;

			if (canConnect(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		System.out.println(answer);
	}

	// mid보다 비싼 간선을 몇 개 써야 1 -> N 도달 가능한지 체크
	static boolean canConnect(int mid) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		int[] visited = new int[N + 1];
		Arrays.fill(visited, Integer.MAX_VALUE);

		pq.offer(new int[] { 1, 0 }); // {노드 번호, 비싼 간선 개수}
		visited[1] = 0;

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int expensive = now[1];

			if (node == N)
				return expensive <= K;

			for (int[] next : adj[node]) {
				int nextNode = next[0];
				int cost = next[1];

				int nextExpensive = expensive + (cost > mid ? 1 : 0);
				if (nextExpensive < visited[nextNode]) {
					visited[nextNode] = nextExpensive;
					pq.offer(new int[] { nextNode, nextExpensive });
				}
			}
		}

		return false;
	}
}
