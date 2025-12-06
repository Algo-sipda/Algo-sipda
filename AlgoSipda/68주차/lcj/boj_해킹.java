import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Computer implements Comparable<Computer>{
	int depend;
	int time;
	
	public Computer(int depend, int time) {
		super();
		this.depend = depend;
		this.time = time;
	}
	@Override
	public int compareTo(Computer o) {
		return this.time - o.time;
	}
}

public class Main {
	final static int INF = 99999999;
	
	static ArrayList<Computer>[] list;
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
            
			list = new ArrayList[n+1];
			dist = new int[n+1];
			visited = new boolean[n+1];
			
			for(int i=1;i<n+1;i++) {
				dist[i] = INF;
				list[i] = new ArrayList<>();
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Computer(a, s));
			}
            
			dijkstra(c);
			
			int infection = 0;
			int total= 0;
            
			for(int i=1;i<n+1;i++) {
				if(dist[i] != INF) {
					infection++;
					total = Math.max(total,  dist[i]);
				}
			}
			sb.append(infection).append(" ").append(total).append("\n");
		}
        
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		
		dist[start] = 0;
		pq.offer(new Computer(start, 0));
		
		while(!pq.isEmpty()) {
			int cur = pq.poll().depend;
			
			if(!visited[cur]) {
				visited[cur] = true;
                
				for(Computer next: list[cur]) {
					if(dist[next.depend]>dist[cur] + next.time) {
						dist[next.depend] = dist[cur] + next.time;
						pq.add(new Computer(next.depend, dist[next.depend]));
					}
				}
			}
		}
	}
}
