import java.io.*;
import java.util.*;

public class Main_27281_운전병의딜레마 {
	
	static class Pair implements Comparable<Pair> {
		long d;
		int v, discomfort;
		
		public Pair (int v, long d, int discomfort) {
			this.v = v;
			this.d = d;
			this.discomfort = discomfort;
		}

		@Override
		public int compareTo(Pair o) {
			return Long.compare(this.d, o.d);
		}
	}
	
	static int N, M, T;
	static ArrayList<Pair>[] graph;
	static Long[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		dist = new Long[N+1];
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<Pair>();
		}
		
		int discomfortMax = Integer.MIN_VALUE;
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Pair(v, t, s));
			graph[v].add(new Pair(u, t, s));
			discomfortMax = Math.max(discomfortMax, s);
		}
		
		System.out.println(getDiscomfort());
	}
	
	public static int getDiscomfort() {
		int left = 0;
		int right = 1_000_000_000;
		
		int answer = right+1;
		
		while(left <= right) {
			int mid = left + (right-left)/2;
			
			if(solve(mid)) {
				answer = mid;
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		
		return answer > 1_000_000_000?-1:answer;
	}
	
	public static boolean solve(int discomfort) {
		Arrays.fill(dist, Long.MAX_VALUE);
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(1, 0L, 0));
		dist[1] = 0L;
		
		while(!pq.isEmpty()) {
			Pair cur = pq.poll();
			
			if(cur.v == N) return dist[N] <= T;
			
			if(dist[cur.v] < cur.d) continue;
			
			for(Pair next:graph[cur.v]) {
				Long nextD = cur.d + next.d + Math.max(0, next.discomfort - discomfort);
				
				if(nextD <= T && dist[next.v]>nextD) {
					dist[next.v] = nextD;
					pq.add(new Pair(next.v, nextD, next.discomfort));
				}
			}
		}
		
		return false;
	}

}
