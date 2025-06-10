import java.io.*;
import java.util.*;

public class Main_15661_링크와스타트 {

	static int N, answer;
	static int[][] abilities;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		abilities = new int[N][N];
		visited = new boolean[N];
		
		answer = Integer.MAX_VALUE;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				abilities[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N; i++) {
			combi(0, 0, i);
		}
		System.out.println(answer);
	}

	public static void combi(int idx, int cnt, int limit) {
		if(cnt == limit) {
			int start = 0;
			int link = 0;
			
			for(int i=0;i<N-1;i++) {
				for(int j=i+1;j<N;j++) {
					if(visited[i] && visited[j]) {
						start += (abilities[i][j]+abilities[j][i]);
					}
					else if(!visited[i] && !visited[j]) {
						link += (abilities[i][j]+abilities[j][i]);
					}
				}
			}
			
			int value = Math.abs(start-link);
			
			answer = Math.min(answer, value);
			
			return;
		}
		
		for(int i=idx;i<N;i++) {
			if(!visited[i]) {
				visited[i] = true;
				combi(i+1, cnt+1, limit);
				visited[i] = false;
			}
		}
	}
}