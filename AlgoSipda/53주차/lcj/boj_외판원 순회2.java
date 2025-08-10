import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;//도시의 수
	static int[][] W;//드는 비용
	static boolean[] visited;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		visited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			visited[i] = true;
			dfs(i, i, 0, 0);
		}
		System.out.println(answer);
	}

	private static void dfs(int start, int now, int cost, int cnt) {
		if(cnt==(N-1)) {
			if(W[now][start] != 0) {
				cost += W[now][start];
				answer = Math.min(answer, cost);
			}
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i] || W[now][i] == 0) continue;
			
			visited[i] = true;
			dfs(start, i, cost + W[now][i], cnt+1);
			visited[i] = false;
		}
	}
}