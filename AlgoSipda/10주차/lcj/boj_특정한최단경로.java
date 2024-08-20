package B01000;

import java.io.*;
import java.util.*;

// 문제 풀이 방식: 다익스트라 + 우선순위 큐

public class Main_1504_특정한최단경로_G4 {
	static class Node implements Comparable<Node> {
		int to, cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int N, E;
	static ArrayList<Node>[] list;
	// Integer.MAX_VALUE로 하면 X
	// -> 간선 개수(E) * 거리(C) = 200,000 * 1,000 = 200_000_000;
	// 한번에 도달하는 것이 아니라 경로를 거쳐서 가는 것이기 때문
	static final int INF = 200_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N];
		for(int i=0;i<N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int n1 = Integer.parseInt(st.nextToken())-1;
			int n2 = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			list[n1].add(new Node(n2, cost));
			list[n2].add(new Node(n1, cost));
		}
		

		st = new StringTokenizer(br.readLine()," ");
		int n1 = Integer.parseInt(st.nextToken())-1;
		int n2 = Integer.parseInt(st.nextToken())-1;
		
		// 1(0) -> n1 -> n2 -> N(N-1)
		int result1 = 0;
		result1 += dijkstra(0, n1);
		result1 += dijkstra(n1, n2);
		result1 += dijkstra(n2, N-1);
		
		// 1(0) -> n2 -> n1 -> N(N-1)
		int result2 = 0;
		result2 += dijkstra(0, n2);
		result2 += dijkstra(n2, n1);
		result2 += dijkstra(n1, N-1);
		
		int answer = (result1 >= INF || result2 >= INF)? -1: Math.min(result1, result2);
		System.out.println(answer);
	}
	
	public static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N];
		for(int i=0;i<N;i++) {
			dist[i] = INF;
		}
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			int curNode = cur.to;
			int curCost = cur.cost;
			
			if(dist[curNode] < curCost) continue;
			
			for(Node n: list[curNode]) {
				int nextNode = n.to;
				int nextCost = n.cost;
				
				int costSum = nextCost + curCost;
				if(costSum < dist[nextNode]) {
					dist[nextNode] = costSum;
					pq.add(new Node(nextNode, costSum));
				}
			}
		}
		
		return dist[end];
	}
}
