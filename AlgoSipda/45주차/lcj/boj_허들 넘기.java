import java.io.*;
import java.util.*;

public class Main_23286_허들넘기 {
	
	static class Node implements Comparable<Node> {
		int v, h;
		Node link;
		
		public Node(int v, int h, Node link) {
			this.v = v;
			this.h = h;
			this.link = link;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.h - o.h;
		}
	}
	
	static int N, M, T;
	static Node[] graph;
	static int[][] minHeight, route;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		graph = new Node[N+1];
		minHeight = new int[N+1][N+1];
		route = new int[N+1][N+1];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			graph[u] = new Node(v, h, graph[u]);
		}
		
		dijkstra();
		
		for(int i=0;i<T;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			int result = findMin(s, e);
			
			if(result == Integer.MAX_VALUE) result = -1;
			
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	public static int findMin(int start, int end) {
		int result = 0;
		
		for(int i=1;i<=N;i++) {
			while(end != 0) {
				result = Math.max(result, minHeight[start][end]);
				end = route[start][end];
			}
		}
		return result;
	}

	public static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			pq.clear();
			Arrays.fill(minHeight[i], Integer.MAX_VALUE);
			boolean[] visited = new boolean[N+1];
			
			pq.add(new Node(i, 0, graph[i]));
			minHeight[i][i] = 0;
			route[i][i] = 0;
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if(visited[cur.v]) continue;
				
				visited[cur.v] = true;
				
				for(Node node = graph[cur.v];node != null;node = node.link) {
					if(!visited[node.v] && minHeight[i][node.v]>node.h) {
						pq.add(new Node(node.v, node.h, null));
						
						minHeight[i][node.v] = Math.min(minHeight[i][node.v], node.h);
						route[i][node.v] = cur.v;
					}
				}
			}
		}
	}
}
