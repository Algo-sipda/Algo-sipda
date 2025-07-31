import java.io.*;
import java.util.*;

/**
 * bfs로 해야 최단 경로 보장
 * @author lcj52
 *
 */

public class Main_13913_숨바꼭질4 {
	
	static int N, K;
	static int[] dist, prev;
	static final int MAX = 100_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[MAX];
		prev = new int[MAX];
		
		bfs();
		
		sb.append(dist[K]+"\n");
		
		Deque<Integer> path = new ArrayDeque<>();
		for(int i=K;i != N;i = prev[i]) {
			path.addFirst(i);
		}
		
		sb.append(N+" ");
		for(int p:path) {
			sb.append(p+" ");
		}
		System.out.println(sb);
	}

	public static void bfs() {
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[MAX];
		
		q.add(N);
		visited[N] = true;
		dist[N] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : new int[] {cur - 1, cur + 1, cur *2}) {
				if(next >= 0 && next < MAX && !visited[next]) {
					q.add(next);
					visited[next] = true;
					dist[next] = dist[cur]+1;
					prev[next] = cur;
				}
			}
		}
	}
}