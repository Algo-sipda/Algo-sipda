import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		List<int[]>[] list = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			list[p].add(new int[] {q, r});
			list[q].add(new int[] {p, r});
		}
		
		for(int i=0;i<Q;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			boolean[] visit = new boolean[N+1];
			visit[v] = true;
			Queue<Integer> q = new LinkedList<>();
			q.add(v);
			
			int answer = 0;
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int[] a:list[cur]) {
					if(!visit[a[0]] && a[1] >= k) {
						q.add(a[0]);
						visit[a[0]] = true;
						answer++;
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}
}
