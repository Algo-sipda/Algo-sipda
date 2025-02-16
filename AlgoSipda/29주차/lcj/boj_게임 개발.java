import java.io.*;
import java.util.*;

public class Main_1516_게임개발 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] time = new int[N+1];
		int[] indegree = new int[N+1];
		ArrayList<Integer>[] list = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreTokens()) {
				int pre = Integer.parseInt(st.nextToken());
				
				if(pre == -1) break;
				
				indegree[i]++;
				list[pre].add(i);
			}
		}
		
		Queue<Integer> q = new LinkedList<>();
		int[] result = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				result[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			
			for(int i: list[idx]) {
				result[i] = Math.max(result[i], result[idx] + time[i]);
				
				if(--indegree[i] == 0) {
					q.offer(i);
				}
			}
		}
		
		for(int i=1;i<=N;i++) sb.append(result[i]).append("\n");
		
		System.out.print(sb);
	}

}