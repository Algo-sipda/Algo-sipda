import java.io.*;
import java.util.*;

public class Main_1800_인터넷설치 {
	
	static class Node implements Comparable<Node> {
		int idx, cost;
		
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int N, P, K;
	static ArrayList<Node>[] map;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		dist = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			map[i] = new ArrayList<>();
		}
		
		int max = 0;
		
		for(int i=0;i<P;i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map[start].add(new Node(end, cost));
			map[end].add(new Node(start, cost));
			
			max = Math.max(max, cost);
		}
		
		int start = 0;
		int answer = Integer.MIN_VALUE;

		while(start <= max) {
			int mid = (start+max)/2;
			
			if(dijkstra(mid)) {
				answer = mid;
				max = mid-1;
			}
			else { 
				start = mid+1;
			}
		}
		
		System.out.println(answer == Integer.MIN_VALUE? -1 : answer);
	}
	
	public static boolean dijkstra(int a) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=1;i<=N;i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[1] = 0;
		pq.add(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.idx]<cur.cost) continue;
			
			for(Node n : map[cur.idx]) {
				int nIdx = n.idx;
				int nCost = cur.cost;

				if(n.cost > a) nCost += 1;
				
				if(nCost < dist[nIdx]) {
					dist[nIdx] = nCost;
					pq.add(new Node(nIdx, nCost));
				}
			}
		}
		return dist[N] <= K;
	}

}