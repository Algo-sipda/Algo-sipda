import java.io.*;
import java.util.*;

public class Main {

	static class Node implements Comparable<Node> {
		int to, w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}

		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	static final int MAX = Integer.MAX_VALUE;
	static int n, m;
	static int[] dp;
	static boolean[][] exRoute;
	static List<Node>[] list;
	static List<Integer>[] removeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n == 0 && m == 0) break;

			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			list = new ArrayList[n];
			removeList = new ArrayList[n];
			dp = new int[n];
			
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
				removeList[i] = new ArrayList<>();
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list[u].add(new Node(v, cost));
			}

			exRoute = new boolean[n][n];
			
			dijkstra(s);
			removeMinRouteVertex(s, d);
			dijkstra(s);
			
			sb.append(dp[d] == MAX ? -1 : dp[d]).append("\n");
		}
		
		System.out.println(sb.toString());

	}

	static void removeMinRouteVertex(int s, int d) {
		if (s == d) return;
		
		for (int nxt : removeList[d]) {
			if (!exRoute[nxt][d]) {
				exRoute[nxt][d] = true;
				removeMinRouteVertex(s, nxt);
			}
		}
	}

	static void dijkstra(int s) {
		Queue<Node> q = new PriorityQueue<>();
		
		Arrays.fill(dp, MAX);
		
		dp[s] = 0;
		q.add(new Node(s, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			int cur = node.to;
			
			if (node.w > dp[cur]) continue;
			
			for (Node nxt : list[cur]) {
				if (exRoute[cur][nxt.to]) continue;
				
				if (dp[nxt.to] > dp[cur] + nxt.w) {
					dp[nxt.to] = dp[cur] + nxt.w;
					removeList[nxt.to] = new ArrayList<>();
					removeList[nxt.to].add(cur);
					q.add(new Node(nxt.to, dp[nxt.to]));
				} else if (dp[nxt.to] == dp[cur] + nxt.w) {
					removeList[nxt.to].add(cur);
				}
			}
		}
	}
}