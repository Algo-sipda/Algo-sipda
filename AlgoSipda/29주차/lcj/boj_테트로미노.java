import java.io.*;
import java.util.*;

public class Main {
	
	static int max = Integer.MIN_VALUE;
	static int[][] arr;
	static boolean[][] visited;
	static int n, m;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				visited[i][j] = true;
				dfs(i, j, arr[i][j], 1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(max);
	}

	public static void dfs(int r, int c, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isRange(nr, nc)) continue;
			
			if(!visited[nr][nc]) {
				if(cnt == 2) {
					visited[nr][nc] = true;
					dfs(r, c, sum + arr[nr][nc], cnt+1);
					visited[nr][nc] = false;
				}
				
				visited[nr][nc] = true;
				dfs(nr, nc, sum + arr[nr][nc], cnt+1);
				visited[nr][nc] = false;
			}
		}
	}
	
	public static boolean isRange(int r, int c) {
		return 0<=r && r<n && 0<=c && c<m;
	}
}
