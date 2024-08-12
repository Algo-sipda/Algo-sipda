import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int to, cost;
		
		public Point(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}
	}
	
	static List<Point>[] list;
	static int V, E;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[v1].add(new Point(v2, cost));
			list[v2].add(new Point(v1, cost));
		}

		Prime();
		System.out.println(answer);
	}

	public static void Prime() {
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(1, 0));
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if(visited[cur.to]) continue;
			
			visited[cur.to] = true;
			answer += cur.cost;
			
			int size = list[cur.to].size();
			for(int i=0;i<size;i++) {
				int to = list[cur.to].get(i).to;
				int cost = list[cur.to].get(i).cost;
				pq.add(new Point(to, cost));
			}
		}
	}
}
